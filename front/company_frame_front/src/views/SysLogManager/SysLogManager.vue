<template>
  <div class="log-content">
    <el-row class="query" style="margin-top: 10px">
      <el-col :span="24">
        <el-form :model="logPageInfo" ref="queryForm" :inline="true" v-show="true">
          <el-form-item label="用户id" prop="userId">
            <el-input
                v-model="logPageInfo.userId"
                placeholder="请输入用户id"
                clearable
                size="small"/>
            <!--                @keyup.enter.native="clickSearchHandler"-->

          </el-form-item>
          <el-form-item label="用户名" prop="username">
            <el-input
                v-model="logPageInfo.username"
                placeholder="请输入用户名"
                clearable
                size="small"
            />
          </el-form-item>
          <el-form-item label="操作" prop="action">
            <el-input
                v-model="logPageInfo.action"
                placeholder="请输入操作"
                clearable
                size="small"
            />
          </el-form-item>
          <el-form-item label="开始时间">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="logPageInfo.startTime"
                              style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="logPageInfo.endTime"
                              style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="clickSearchHandler">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="24">
        <el-button @click="batchDelete">删除</el-button>
      </el-col>
    </el-row>

<!--    展示表格和分页插件-->

    <el-table
        ref="multipleTable"
        :data="logList.list"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="userId"
          label="用户ID">
      </el-table-column>
      <el-table-column
          prop="username"
          label="用户名">
      </el-table-column>
      <el-table-column
          prop="method"
          label="请求方式"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="operation"
          label="操作"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="params"
          label="参数"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="time"
          label="耗时"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="clickDelete(scope.row.id)" size="mini">
            删除
          </el-button>
        </template>
      </el-table-column>

    </el-table>
    <el-row type="flex" justify="center">
      <el-col>
        <div class="block" style="text-align: center">


          <el-pagination
              :current-page="logPageInfo.pageNum"
              :page-sizes="page_sizes"
              :page-size="10"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              layout="total, sizes, prev, pager, next, jumper"
              :total="logList.totalRows">
          </el-pagination>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import {deletePermissionApi, sysLogBatchDeleteApi, sysLogDeleteApi, sysLogPageApi} from "@/api/getData";

export default {
  name: "SysLogManager",
  data(){
    return{
      logPageInfo: {
        pageNum: 1,
        pageSize: 10,
        userId: undefined,
        username: undefined,
        action: undefined,
        startTime: undefined,
        endTime: undefined
      },
      logList: [],
      page_sizes: [10,20,30,40],
      multipleSelection: [],
    }
  },
  methods: {
    async clickSearchHandler(){
      console.log(this.logPageInfo);

      const result = await sysLogPageApi(this.$store.state.accessToken,this.logPageInfo);
      if (result.data.code == 0){
        this.logList = result.data.data;
        console.log("loglist",this.logList)
      }else {
        this.$message.error(result.data.msg);
      }
    },
    async getLogPage(){
      console.log(this.logPageInfo);
      const result = await sysLogPageApi(this.$store.state.accessToken,this.logPageInfo);
      if (result.data.code == 0){
        this.logList = result.data.data;
        console.log("loglist",this.logList)
      }else {
        this.$message.error(result.data.msg);
      }
    },
    resetQuery(){
      this.logPageInfo = {
            pageNum: 1,
            pageSize: 10,
            userId: undefined,
            username: undefined,
            action: undefined,
            startTime: undefined,
            endTime: undefined
      }
    },
    //修改每页显示数量
    handleSizeChange(val) {
      this.logPageInfo.pageSize = val;
      this.getLogPage();
      console.log(`每页 ${val} 条`);
    },
    //修改当前页
    handleCurrentChange(val) {
      this.logPageInfo.pageNum = val;
      this.getLogPage();
      console.log(`当前页: ${val}`);
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //删除单条日志记录
    async clickDelete(id){
      this.$confirm('是否确认删除', '删除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const result = await sysLogDeleteApi(this.$store.state.accessToken,id);
        if (result.data.code == 0){
          this.$message("删除成功")
          this.getLogPage();
        }else {
          this.$message.error(result.data.msg);
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    batchDelete(){
      this.$confirm('是否确认删除', '删除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const ids = [];
        this.multipleSelection.forEach(item => ids.push(item.id));
        const result = await sysLogBatchDeleteApi(this.$store.state.accessToken,ids);
        if (result.data.code == 0){
          this.$message("删除成功")
          this.getLogPage();
        }else {
          this.$message.error(result.data.msg);
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },
  mounted() {
    this.getLogPage();
  }
}
</script>

<style scoped>

</style>
