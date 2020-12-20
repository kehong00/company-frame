<template>
  <div class="log-content">
    <el-row style="margin-top: 10px">
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
                placeholder="请输入用户名"
                clearable
                size="small"
            />
          </el-form-item>
          <el-form-item label="创建时间">
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
        <el-button type="primary" size="mini" @click="clickAdd">添加</el-button>
        <el-button type="danger" size="mini" @click="clickDelete">删除</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
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
      }
    }
  }
}
</script>

<style scoped>

</style>
