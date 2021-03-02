<template>
  <div>
    <div class="app-container">
      <el-form :model="permissionSearchForm" ref="queryForm" :inline="true" v-show="showSearch">
        <el-form-item label="菜单名称" prop="name">
          <el-input
              v-model="permissionSearchForm.name"
              placeholder="请输入菜单名称"
              clearable
              size="small"
              @keyup.enter.native="clickSearchHandler"
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="permissionSearchForm.status" placeholder="菜单状态" clearable size="small">
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

      <el-table
          :data="permissionTree"
          row-key="id"
          :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      >
        <el-table-column prop="name" label="菜单名称" :show-overflow-tooltip="true" width="160"></el-table-column>
        <!--<el-table-column prop="icon" label="图标" align="center" width="100">
          <template slot-scope="scope">
            <svg-icon :icon-class="scope.row.icon"/>
          </template>
        </el-table-column>-->
        <el-table-column prop="orderNum" label="排序" width="60"></el-table-column>
        <el-table-column prop="perms" label="权限标识" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="url" label="组件路径" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="status" label="状态">
          <template slot-scope="scope">
            <el-button type="success" size="mini" disabled v-if="scope.row.status == 1">正常</el-button>
            <el-button type="warning" disabled v-else size="mini">禁用</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="clickEdit(scope.row)" size="mini">
              编辑
            </el-button>
            <el-button @click="clickDelete(scope.row)" type="primary" size="mini">
              删除
            </el-button>
            <el-button type="primary" @click="clickAdd(scope.row)" size="mini">
              新增
            </el-button>
          </template>
        </el-table-column>
      </el-table>


      <!-- 添加或修改菜单对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
        <el-form ref="form" :model="form" label-width="80px">
          <el-row>
            <el-col :span="24">
              <el-form-item label="上级菜单">
                <!--<treeselect
                    v-model="form.pid"
                    :options="menuOptions"
                    :normalizer="normalizer"
                    placeholder="选择上级菜单"
                />-->
                <el-select
                    v-model="form.pid"
                    clearable
                    placeholder="请选择"
                    ref="selectUpResId"
                >
                <el-option style="height: auto" key="upResId" :value="form.pid" :label="form.pid">
                  <el-tree
                      :data="menuOptions"
                      :props="{children: 'children',label: 'name'}"
                      :expand-on-click-node="false"
                      :check-on-click-node="true"
                      @node-click="handleNodeClick"
                  >
                  </el-tree>
                </el-option>
                </el-select>

              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="菜单类型" prop="type">
                <el-radio-group v-model="form.type">
                  <el-radio :label=1>目录</el-radio>
                  <el-radio :label=2>菜单</el-radio>
                  <el-radio :label=3>按钮</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单名称" prop="name">
                <el-input v-model="form.name" placeholder="请输入菜单名称" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="显示排序" prop="orderNum">
                <el-input-number v-model="form.orderNum" controls-position="right" :min="0" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="路由地址" prop="url">
                <el-input v-model="form.url" placeholder="请输入路由地址" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item v-if="form.type != '1'" label="请求方式" prop="method">
                <el-input v-model="form.method" placeholder="请输入请求方式" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item v-if="form.type != 1" label="权限标识">
                <el-input v-model="form.perms" placeholder="请权限标识" maxlength="50" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item v-if="form.type != 3" label="菜单状态">
                <el-radio-group v-model="form.status">
                  <el-radio
                      v-for="dict in statusOptions"
                      :key="dict.dictValue"
                      :label="dict.dictValue"
                  >{{dict.dictLabel}}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确定</el-button>
          <el-button @click="cancel" >取消</el-button>
        </div>
      </el-dialog>

    </div>
  </div>
</template>

<script>
import CommonAside from "@/components/CommonAside/CommonAside";
import CommonHeader from "@/components/CommonHeader/CommonHeader";
import Treeselect from "@riophae/vue-treeselect";
import {
  addPermissionApi, deletePermissionApi,
  editPermissionApi,
  getAllPermissionListApi,
  getPermissionInfoApi,
  getPermissionTreeInBtn
} from "@/api/getData";

export default {
  name: 'PermissionManager',
  components: {
    CommonAside,
    CommonHeader,
    Treeselect

  },
  data() {
    return {
      //权限树列表
      permissionTree: [],
      //搜索表单对象
      permissionSearchForm: {
        name: "",
        status: ""
      },
      statusOptions: [{
        dictLabel: "启用",
        dictValue: 1
      },
        {
          dictLabel: "禁用",
          dictValue: 0
        }
      ],
      showSearch: true,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //表单回显信息
      form: {},
      //表单下拉列表数据
      menuOptions: [],
      //全部权限列表
      permissionList: [],
      //父级菜单名称
      parentName: '1'
    }
  },
  methods: {
    async getPermissionTree() {
      const token = this.$store.state.accessToken;
      const result = await getPermissionTreeInBtn(token);
      if (result.data.code == 0) {
        this.permissionTree = result.data.data;
      } else {
        this.$message.error(result.data.msg);
      }
    },
    clickSearchHandler() {
      console.log(this.permissionSearchForm.name + " : " + this.permissionSearchForm.status)
    },
    resetQuery() {
      this.permissionSearchForm.name = "";
      this.permissionSearchForm.status = "";
    },
    async getAllPermission(){
      const result = await getAllPermissionListApi(this.$store.state.accessToken);
      if (result.data.code == 0){
        this.permissionList = result.data.data;
      }
    },
    clickEdit(item) {
      this.getAllPermission();
      this.getPermissionInfo(item.id);
      this.getTreeSelect();
      this.getParentName(item.pid);
      console.log("click edit:" + item.id)
      // console.log(parent);
      // this.parentName = parent.name;
      this.open = true;
      this.title = '编辑';
      // console.log(item);
    },
    //点击添加按钮
    clickAdd(item){
      this.resetForm();
      if(item.id != null && item.id != undefined ){
        this.form.pid = item.id;
      }
      this.open = true;
    },
    //点击删除按钮
    clickDelete(item){
      this.$confirm('是否确认删除', '删除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const result = await deletePermissionApi(this.$store.state.accessToken,item.id);
        if (result.data.code == 0){
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }else{
          this.$message({
            type: 'error',
            message: result.data.msg
          });
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });



    },
    //重置表单
    resetForm(){
      this.form = {
        id: undefined,
        name: undefined,
        code: undefined,
        perms: undefined,
        url: undefined,
        method: undefined,
        type: 0,
        orderNum: 1,
        pid: "0",
        status: 1
      }
    },
    //获取权限信息
    async getPermissionInfo(id) {
      console.log("获取权限的id：" + id)
      const result = await getPermissionInfoApi(this.$store.state.accessToken,id);
      if (result.data.code == 0) {
        this.form = result.data.data;
        console.log("form" + JSON.stringify(this.form))
      }
    },
    //获取菜单下拉列表
    async getTreeSelect() {
      const result = await getPermissionTreeInBtn(this.$store.state.accessToken);
      if (result.data.code == 0) {
        this.menuOptions = result.data.data;
      }
    },
    /** 转换菜单数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },
    handleNodeClick(node){
      this.form.pid = node.id;
      console.log(node)
    },
    //取消操作
    cancel(){
      this.open = false;
    },
    //确认提交表单
    async submitForm(){
      console.log("form：" + this.form.id)
      const token = this.$store.state.accessToken;
      //判断是否存在id
      if (this.form.id != null && this.form.id != undefined){
        //存在id，为编辑
        const result = await editPermissionApi(token,this.form);
        if (result.data.code == 0){
          this.$message.success("操作成功");
          await this.getPermissionTree();
          this.open = false;
        }else{
          this.$message.error(result.data.msg);
        }
      }else {
        console.log("新增权限：" + JSON.stringify(this.form))
        //不存在id，新增
        const result = await addPermissionApi(token,this.form);

        if (result.data.code == 0){
          this.$message.success("操作成功");
          await this.getPermissionTree();
          this.open = false;
        }else{
          this.$message.error(result.data.msg);
        }
      }
    },
    //获取父级菜单名称
    getParentName(pid){
      console.log("getparent")
      for (let i = 0; i < this.permissionList.length; i++) {
        console.log("for i")
        console.log("test:" + this.permissionList[i])
        if (this.permissionList[i].id == pid){
          console.log("parent:" + this.permissionList[i]);
          this.parentName = this.permissionList[i].name;
        }
      }
    }
  },
  mounted() {
    this.getPermissionTree();
  }
}
</script>
<style lang="scss" scoped>
.main {
  margin: 0;
  padding: 0;
  height: 100vh;
}

el-container {
  padding: 0;
  margin: 0;
}
</style>
