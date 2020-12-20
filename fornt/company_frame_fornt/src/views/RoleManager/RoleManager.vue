<template>
  <div class="user-content">

    <el-row style="margin-top: 10px">
      <el-col :span="24">
        <el-form :model="rolePageInfo" ref="queryForm" :inline="true" v-show="true">
          <el-form-item label="角色id" prop="id">
            <el-input
                v-model="rolePageInfo.roleId"
                placeholder="请输入角色id"
                clearable
                size="small"/>
            <!--                @keyup.enter.native="clickSearchHandler"-->

          </el-form-item>
          <el-form-item label="角色名" prop="roleName">
            <el-input
                v-model="rolePageInfo.roleName"
                placeholder="请输入角色名"
                clearable
                size="small"
            />
          </el-form-item>
          <el-form-item label="创建时间">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="rolePageInfo.startTime"
                              style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="rolePageInfo.endTime"
                              style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="rolePageInfo.status" placeholder="部门状态" clearable size="small">
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
      <el-form ref="form" :model="roleForm" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="角色权限">
              <!--<treeselect
                  v-model="form.pid"
                  :options="menuOptions"
                  :normalizer="normalizer"
                  placeholder="选择上级菜单"
              />-->
              <!--<el-input @click="selectionPermission"
                        v-model="roleForm.deptId"
                        clearable
                        placeholder="请选择"/>-->
              <el-button @click="selectionPermission">选择权限</el-button>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="角色名称" prop="roleName">
              <el-input v-model="roleForm.roleName" placeholder="请输入用户名称"/>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="状态">
              <el-radio-group v-model="roleForm.status">
                <el-radio
                    v-for="dict in statusOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue"
                >{{ dict.dictLabel }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="角色描述" prop="description">
              <el-input v-model="roleForm.description" placeholder="角色描述"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确定</el-button>
        <el-button @click="cancel">取消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="选择权限" :visible.sync="transferOpen" width="600px" append-to-body>
      <tree-transfer :title="['1','2']" :from_data='permissionTree' :to_data='rolePermissionTree'
                     :defaultProps="{label:'name',children: 'children'}"
                     @add-btn='add' @remove-btn='remove' height='540px' arrayToTree>
      </tree-transfer>
    </el-dialog>

    <!--    用户表格展示-->
    <el-table
        ref="multipleTable"
        :data="roleList"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="id"
          label="角色ID">
      </el-table-column>
      <el-table-column
          prop="name"
          label="角色名"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          prop="description"
          label="角色描述"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          <el-button type="success" size="mini" disabled v-if="scope.row.status == 1">正常</el-button>
          <el-button type="warning" disabled v-else size="mini">禁用</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="clickEdit(scope.row)" size="mini">
            编辑
          </el-button>
        </template>
      </el-table-column>

    </el-table>
    <el-row type="flex" justify="center">
      <el-col>
        <div class="block" style="text-align: center">


          <el-pagination
              :current-page="roleListPage.pageNum"
              :page-sizes="page_sizes"
              :page-size="10"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              layout="total, sizes, prev, pager, next, jumper"
              :total="roleListPage.totalRows">
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
  getAllPermissionListApi,
  getPermissionTree,
  getPermissionTreeInBtn,
  roleAddApi,
  roleEditApi,
  roleInfoApi,
  roleListAllApi,
  roleListPageApi,
  rolePermissionListApi,
} from "@/api/getData";
import treeTransfer from 'el-tree-transfer'

export default {
  name: "RoleManager",
  components: {
    treeTransfer
  },
  data() {
    return {
      roleListPage: {},
      roleList: [],
      rolePageInfo: {
        pageNum: 1,
        pageSize: 10,
        roleId: null,
        roleName: null,
        status: null,
        startTime: null,
        endTime: null
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
      title: "新增角色",
      open: false,
      roleForm: {
        roleId: undefined,
        roleName: undefined,
        description: undefined,
        status: 1,
        permissionIds: []
      },
      //全部权限树形列表
      permissionTree: [],
      //全部权限列表，未封装tree
      permissionList: [],
      transferOpen: false,
      deptTree: [],
      deptOptions: [],
      //角色拥有权限树形列表
      rolePermissionTree: [],
      //角色所拥有的权限，未封装tree
      rolePermissionList: []
    }
  },
  methods: {
    async getRolePage() {
      const result = await roleListPageApi(this.$store.state.accessToken, this.rolePageInfo);
      if (result.data.code == 0) {
        this.roleListPage = result.data.data;
        this.roleList = result.data.data.list;
      } else {
        this.$message.error(result.data.msg);
      }
    },

    //获取全部权限,前端封装tree
    async getPermissionAllTree() {
      const result = await getAllPermissionListApi(this.$store.state.accessToken);
      if (result.data.code == 0) {
        this.permissionList = result.data.data;
        this.permissionTree = this.getPermissionTree(this.permissionList);
        console.log("permissionTree:" + JSON.stringify(this.permissionTree))
      } else {
        this.$message.error(result.data.msg);
      }
    },
    //获取角色拥有的权限
    async getRolePermissionList() {
      const roleId = this.roleForm.roleId;
      // const roleId = '2d831a6a-fef5-40a7-825e-4167da636a15';
      //判断是否有回显id
      if (roleId != undefined && roleId != "" && roleId != null) {
        //有回显id
        const result = await rolePermissionListApi(this.$store.state.accessToken, roleId);
        if (result.data.code == 0) {
          this.rolePermissionList = result.data.data;
          this.rolePermissionTree = this.getPermissionTree(this.rolePermissionList);
        } else {
          this.$message.error(result.data.msg);
        }
      }
    },
    //点击添加按钮
    clickAdd() {
      this.reset();
      this.open = true;
    },

    //点击新增
    async clickEdit(item){
      const roleId = item.id;
      console.log("roleId",roleId)
      //获取回显数据
      const result = await roleInfoApi(this.$store.state.accessToken,roleId);
      if (result.data.code == 0) {
        this.roleForm.roleId = result.data.data.id;
        this.roleForm.roleName = result.data.data.name;
        this.roleForm.status = result.data.data.status;
        this.roleForm.description = result.data.data.description;
        console.log("roleForm:",this.roleForm)
      }
      this.title = "编辑角色";
      this.open = true;
    },


    // 监听穿梭框组件添加
    async add(fromData, toData, obj) {
      // 树形穿梭框模式transfer时，返回参数为左侧树移动后数据、右侧树移动后数据、移动的{keys,nodes,halfKeys,halfNodes}对象
      // 通讯录模式addressList时，返回参数为右侧收件人列表、右侧抄送人列表、右侧密送人列表
      //获取选中节点的父节点
      obj.halfNodes.forEach(item => {
        const index = this.permissionList.findIndex(p => p.id == item.id);
        this.permissionList.splice(index,1);
        const has = this.rolePermissionList.find(p => p.id == item.id);
        console.log("是否存在",item,has)
        if (!has){
          this.rolePermissionList.push(item);
        }
      });
      obj.nodes.forEach(item => {
        const index = this.permissionList.findIndex(p => p.id == item.id);
        this.permissionList.splice(index,1);
        const has = this.rolePermissionList.find(p => p.id == item.id);
        console.log("是否存在",item,"++++++",has)
        if (!has){
          this.rolePermissionList.push(item);
        }
      });
      console.log("rolePermissionList:" , this.rolePermissionList)
      this.rolePermissionTree = await this.getPermissionTree(this.rolePermissionList);
      console.log("rolePermissionTree:" , this.rolePermissionTree)
      console.log("fromData:", fromData);
      console.log("toData:", toData);
      console.log("obj:", obj);
    },
    // 监听穿梭框组件移除
    remove(fromData, toData, obj) {
      // 树形穿梭框模式transfer时，返回参数为左侧树移动后数据、右侧树移动后数据、移动的{keys,nodes,halfKeys,halfNodes}对象
      // 通讯录模式addressList时，返回参数为右侧收件人列表、右侧抄送人列表、右侧密送人列表
      obj.halfNodes.forEach(item => {
        const index = this.rolePermissionList.findIndex(p => p.id == item.id);
        this.rolePermissionList.splice(index,1);
        const has = this.permissionList.find(p => p.id == item.id);
        console.log("是否存在",item,has)
        if (!has){
          this.permissionList.push(item);
        }
      });
      obj.nodes.forEach(item => {
        const index = this.rolePermissionList.findIndex(p => p.id == item.id);
        this.rolePermissionList.splice(index,1);
        const has = this.permissionList.find(p => p.id == item.id);
        console.log("是否存在",item,"++++++",has)
        if (!has){
          this.permissionList.push(item);
        }
      });
      console.log("fromData:", fromData);
      console.log("toData:", toData);
      console.log("obj:", obj);
    },

    //封装权限树
    getPermissionTree(list){
      let tree = [];
      console.log("list:" , list)
      for (let i = 0; i < list.length; i++) {
        if (list[i].pid == '0'){
          console.log("pid==0")
          const permission = {};
          permission.id = list[i].id;
          permission.name = list[i].name;
          permission.pid = list[i].pid;
          permission.children = this.getTree(list,list[i].id);
          tree.push(permission);
        }
      }
      return tree;
    },

    getTree(list,id){
      let tree = []
      for (let i = 0; i < list.length; i++) {
        console.log("getTreevalue:" +  JSON.stringify(list[i]));
        if (list[i].pid == id){
          console.log("pid==0")
          const permission = {};
          permission.id = list[i].id;
          permission.name = list[i].name;
          permission.pid = list[i].pid;
          permission.children = this.getTree(list,list[i].id);
          tree.push(permission);
        }
      }
      return tree;
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
          this.getRolePage();
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
      this.roleForm.deptId = item.parentId;
    },
    reset() {
      this.roleForm = {
        roleId: undefined,
        roleName: undefined,
        description: undefined,
        status: 1,
        permissionIds: []
      }
    },
    cancel() {
      this.open = false;
    },
    //提交表单
    async submitForm() {
      this.rolePermissionList.forEach(item => this.roleForm.permissionIds.push(item.id));
      console.log("roleInfo:", this.roleForm)
      const roleId = this.roleForm.roleId;
      if (roleId != null && roleId != undefined && roleId != ''){
        //编辑角色
          const result = await roleEditApi(this.$store.state.accessToken,this.roleForm);
        if (result.data.code == 0) {
          this.$message.success("操作成功");
          await this.getRolePage();
          this.open = false;
        } else {
          this.$message.error(result.data.msg);
        }
      }else{
        //新增角色
        const result = await roleAddApi(this.$store.state.accessToken, this.roleForm);
        if (result.data.code == 0) {
          this.$message.success("操作成功");
          await this.getRolePage();
          this.open = false;
        } else {
          this.$message.error(result.data.msg);
        }
      }


    },
    //修改每页显示数量
    handleSizeChange(val) {
      this.rolePageInfo.pageSize = val;
      this.getRolePage();
      console.log(`每页 ${val} 条`);
    },
    //修改当前页
    handleCurrentChange(val) {
      this.rolePageInfo.pageNum = val;
      this.getRolePage();
      console.log(`当前页: ${val}`);
    },
    //清空查询条件
    resetQuery() {
      this.rolePageInfo = {
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
    clickSearchHandler() {
      console.log(this.rolePageInfo)
      this.getRolePage();
    },
    //选择权限
    selectionPermission() {
      this.transferOpen = true;
      this.getPermissionAllTree();
      this.getRolePermissionList();
    }

  },
  mounted() {
    this.getRolePage();
  }
}
</script>

<style scoped>

</style>
