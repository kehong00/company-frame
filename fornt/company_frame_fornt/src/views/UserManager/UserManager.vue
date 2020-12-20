<template>
  <div class="user-content">

    <el-row style="margin-top: 10px">
      <el-col :span="24">
        <el-form :model="userPageInfo" ref="queryForm" :inline="true" v-show="true">
          <el-form-item label="用户id" prop="id">
            <el-input
                v-model="userPageInfo.userId"
                placeholder="请输入用户id"
                clearable
                size="small"/>
<!--                @keyup.enter.native="clickSearchHandler"-->

          </el-form-item>
          <el-form-item label="账号" prop="username">
            <el-input
                v-model="userPageInfo.username"
                placeholder="请输入账号"
                clearable
                size="small"
            />
          </el-form-item>
          <el-form-item label="昵称" prop="nickName">
            <el-input
                v-model="userPageInfo.nickName"
                placeholder="请输入昵称"
                clearable
                size="small"
            />
          </el-form-item>
          <el-form-item label="创建时间">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="userPageInfo.startTime" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="userPageInfo.endTime" style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="userPageInfo.status" placeholder="部门状态" clearable size="small">
              <el-option
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
              />
            </el-select>
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


    <!-- 添加或修改菜单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="userForm" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="所属部门">
              <!--<treeselect
                  v-model="form.pid"
                  :options="menuOptions"
                  :normalizer="normalizer"
                  placeholder="选择上级菜单"
              />-->
              <el-select
                  v-model="userForm.deptId"
                  clearable
                  placeholder="请选择"
                  ref="selectParentId"
              >
                <el-option style="height: auto" key="upResId" :value="userForm.deptId" :label="userForm.deptId">
                  <el-tree
                      :data="deptOptions"
                      :props="{children: 'children',label: 'parentName'}"
                      :expand-on-click-node="false"
                      :check-on-click-node="true"
                      @node-click="handleNodeClick"
                  >
                  </el-tree>
                </el-option>
              </el-select>

            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="用户名称" prop="username">
              <el-input v-model="userForm.username" placeholder="请输入用户名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="密码" prop="password">
              <el-input v-model="userForm.password" placeholder="请输入密码"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系方式" prop="phone">
              <el-input v-model="userForm.phone" placeholder="联系方式"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="真实姓名" prop="realName">
              <el-input v-model="userForm.realName" placeholder="真实姓名"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="昵称" prop="nickName">
              <el-input v-model="userForm.nickName" placeholder="昵称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="userForm.email" placeholder="邮箱"/>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="状态">
              <el-radio-group v-model="userForm.status">
                <el-radio
                    v-for="dict in statusOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue"
                >{{ dict.dictLabel }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别">
              <el-radio-group v-model="userForm.sex">
                <el-radio
                    v-for="dict in sexOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue"
                >{{ dict.dictLabel }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确定</el-button>
        <el-button @click="cancel">取消</el-button>
      </div>
    </el-dialog>

<!--    选择权限对话框-->
    <el-dialog title="选择权限" :visible.sync="transferOpen" width="700px" append-to-body>
<!--      <tree-transfer :title="['1','2']" :from_data='roleList' :to_data='userRoleList'
                     :defaultProps="{label:'name',children: 'children'}"
                     @add-btn='add' @remove-btn='remove' height='540px' arrayToTree>
      </tree-transfer>-->
      <el-transfer v-model="userRoleList" :data="roleList" :props="{key: 'id',label: 'name'}"></el-transfer>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRole">确定</el-button>
        <el-button @click="cancelRole">取消</el-button>
      </div>

    </el-dialog>


    <el-table
        ref="multipleTable"
        :data="userList"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="username"
          label="用户名">
      </el-table-column>
      <el-table-column
          prop="phone"
          label="手机号"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="realName"
          label="真实名称"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="nickName"
          label="昵称"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="email"
          label="邮箱"
          show-overflow-tooltip>
      </el-table-column>

      <el-table-column
          label="性别"
          width="80px">
        <template slot-scope="scope">{{ scope.row.sex == 1 ? "男" : "女" }}</template>
      </el-table-column>
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          <el-button type="success" size="mini" disabled v-if="scope.row.status == 1">正常</el-button>
          <el-button type="warning" disabled v-else size="mini">禁用</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="roleManage(scope.row)" size="mini">
            管理角色
          </el-button>
        </template>
      </el-table-column>

    </el-table>
    <el-row type="flex" justify="center">
      <el-col>
        <div class="block" style="text-align: center">


          <el-pagination
              :current-page="userListPage.pageNum"
              :page-sizes="page_sizes"
              :page-size="10"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              layout="total, sizes, prev, pager, next, jumper"
              :total="userListPage.totalRows">
          </el-pagination>
        </div>
      </el-col>
    </el-row>
    <!--<div style="margin-top: 20px">
      <el-button @click="toggleSelection([tableData[1], tableData[2]])">切换第二、第三行的选中状态</el-button>
      <el-button @click="toggleSelection()">取消选择</el-button>
    </div>-->
  </div>
</template>

<script>
import {
  deletePermissionApi,
  deptTreeApi,
  userAddApi,
  userDeleteApi,
  userListAllApi,
  userOwnRoleApi, userOwnRoleEditApi
} from "@/api/getData";

import treeTransfer from 'el-tree-transfer'

export default {
  name: "UserManager",
  components: {
    treeTransfer
  },
  data() {
    return {
      userListPage: {},
      userList: [],
      //分页查询用户
      userPageInfo: {
        pageNum: 1,
        pageSize: 10,
        username: null,
        userId: null,
        status: null,
        startTime: null,
        endTime: null,
        nickName: null
      },
      //页面分页信息
      page_sizes: [10, 20, 30, 40],
      multipleSelection: [],
      statusOptions: [{
        dictLabel: "启用",
        dictValue: 1
      },
        {
          dictLabel: "禁用",
          dictValue: 0
        }
      ],
      sexOptions: [{
        dictLabel: "男",
        dictValue: 1
      },
        {
          dictLabel: "女",
          dictValue: 2
        }
      ],
      title: "新增用户",
      open: false,
      userForm: {
        username: undefined,
        password: undefined,
        phone: undefined,
        deptId: undefined,
        realName: undefined,
        nickName: undefined,
        email: undefined,
        sex: 1,
        status: 1,
        createWhere: 1
      },
      deptTree: [],
      deptOptions: [],
      //赋予角色相关数据
      transferOpen: false,
      //用户已有角色列表
      userRoleList: [],
      //可选择角色列表
      roleList: [],
      //赋予角色的用户id
      userId: undefined
    }
  },
  methods: {
    async getUserList() {

      const result = await userListAllApi(this.$store.state.accessToken,this.userPageInfo);
      if (result.data.code == 0) {
        this.userListPage = result.data.data;
        this.userList = result.data.data.list;
      } else {
        this.$message.error(result.data.msg);
      }
    },

    async getDeptTree() {
      const result = await deptTreeApi(this.$store.state.accessToken);
      if (result.data.code == 0) {
        this.deptTree = result.data.data;
        console.log("deptTree:" + JSON.stringify(this.deptTree))
      } else {
        this.$message.error(result.data.msg);
      }
    },

    async getDeptOptions() {
      await this.getDeptTree();
      this.deptOptions = this.deptTree;
    },
    clickAdd() {
      this.getDeptOptions();
      this.open = true;
    },
    //点击删除按钮
    clickDelete() {
      this.$confirm('是否确认删除', '删除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const ids = [];
        this.multipleSelection.forEach(value => {
          ids.push(value.id);
        });

        const result = await userDeleteApi(this.$store.state.accessToken, ids);
        if (result.data.code == 0) {
          this.$message.success("删除成功");
          this.getUserList();
        } else {
          this.$message.error(result.data.msg);
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleNodeClick(item) {
      this.userForm.deptId = item.parentId;
    },
    reset() {
      this.userForm = {
        username: undefined,
        password: undefined,
        phone: undefined,
        deptId: undefined,
        realName: undefined,
        nickName: undefined,
        email: undefined,
        sex: 1,
        status: 1,
        createWhere: 1
      }
    },
    cancel() {
      this.open = false;
    },
    async submitForm() {
      const result = await userAddApi(this.$store.state.accessToken, this.userForm);
      if (result.data.code == 0) {
        this.getUserList();
        this.open = false;
      } else {
        this.$message.error(result.data.msg);
      }
    },
    //修改每页显示数量
    handleSizeChange(val) {
      this.userPageInfo.pageSize = val;
      this.getUserList();
      console.log(`每页 ${val} 条`);
    },
    //修改当前页
    handleCurrentChange(val) {
      this.userPageInfo.pageNum = val;
      this.getUserList();
      console.log(`当前页: ${val}`);
    },
    //清空查询条件
    resetQuery(){
      this.userPageInfo = {
        pageNum: 1,
        pageSize: 10,
        username: null,
        userId: null,
        status: null,
        startTime: null,
        endTime: null,
        nickName: null
      }
    },
    //点击搜索按钮
    clickSearchHandler(){
      console.log(this.userPageInfo)
      this.getUserList();
    },
    async roleManage(item){

      console.log("item:",item);
      this.userId = item.id;
      //打开对话框
      this.transferOpen = true;
      const result = await userOwnRoleApi(this.$store.state.accessToken,this.userId);
      if (result.data.code == 0){
        //清空用户拥有的角色记录
        this.userRoleList = [];
        this.roleList = result.data.data.roleList;
        // this.userRoleList = result.data.data.userRoleList;
        console.log("userRoleList:",result.data.data.userRoleList);
        result.data.data.userRoleList.forEach(item => this.userRoleList.push(item.id));
        console.log("roleList",this.roleList);
        console.log("userRoleList",this.userRoleList);
      }else {
        this.$message.error(result.data.msg);
      }
    },
    async submitRole(){
      console.log("user:",this.userId)
      console.log("roleList",this.userRoleList);
      const result = await userOwnRoleEditApi(this.$store.state.accessToken,this.userId,this.userRoleList);
      if (result.data.code == 0){
        this.$message.success("操作成功");
        this.transferOpen = false;
      }else {
        this.$message.error(result.data.msg);
      }
    },
    cancelRole(){
      this.transferOpen = false;
    }

  },
  mounted() {
    this.getUserList();
  }
}
</script>

<style scoped>

</style>
