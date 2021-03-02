<template>
  <div>
    <div class="app-container">
      <el-form :model="deptSearchForm" ref="queryForm" :inline="true" v-show="showSearch">
        <el-form-item label="部门名称" prop="name">
          <el-input
              v-model="deptSearchForm.name"
              placeholder="请输入部门名称"
              clearable
              size="small"
              @keyup.enter.native="clickSearchHandler"
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="deptSearchForm.status" placeholder="部门状态" clearable size="small">
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
          :data="deptTree"
          row-key="id"
          :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      >
        <el-table-column prop="id" label="部门ID" :show-overflow-tooltip="true" width="160"></el-table-column>
        <el-table-column prop="name" label="菜单名称" :show-overflow-tooltip="true" width="160"></el-table-column>
        <el-table-column prop="deptNo" label="部门编码" :show-overflow-tooltip="true" width="160"></el-table-column>
        <el-table-column prop="parentName" label="上级部门名称" :show-overflow-tooltip="true" width="160"></el-table-column>
        <el-table-column prop="relationCode" label="层级关系码" :show-overflow-tooltip="true" width="160"></el-table-column>
        <el-table-column prop="managername" label="负责人" :show-overflow-tooltip="true" width="160"></el-table-column>
        <el-table-column prop="phone" label="联系电话" :show-overflow-tooltip="true" width="160"></el-table-column>
        <!--<el-table-column prop="icon" label="图标" align="center" width="100">
          <template slot-scope="scope">
            <svg-icon :icon-class="scope.row.icon"/>
          </template>
        </el-table-column>-->
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
              <el-form-item label="所属部门">
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
                    ref="selectParentId"
                >
                  <el-option style="height: auto" key="upResId" :value="form.pid" :label="form.pid">
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
              <el-form-item label="部门名称" prop="name">
                <el-input v-model="form.name" placeholder="请输入菜单名称" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="负责人" prop="managerName">
                <el-input v-model="form.managerName" placeholder="负责人名称" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="联系方式" prop="phone">
                <el-input v-model="form.phone" placeholder="联系方式" />
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="菜单状态">
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
import {
  deptAddApi,
  deptEditApi,
  deptInfoApi,
  deptTreeApi

} from "@/api/getData";

export default {
  name: 'DeptManager',
  components: {
  },
  data() {
    return {
      //权限树列表
      deptTree: [],
      //搜索表单对象
      deptSearchForm: {
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
      deptOptions: [],
      //全部权限列表
      deptList: [],
      //父级菜单名称
      parentName: '1'
    }
  },
  methods: {
    //获取部门树形结构数据
    async getDeptTree(){
      const result = await deptTreeApi(this.$store.state.accessToken);
      if (result.data.code == 0){
        this.deptTree = result.data.data;
      }else{
        this.$message.error(result.data.msg);
      }
    },
    //点击搜索按钮
    clickSearchHandler(){
      this.deptSearchForm.name = '';
      this.deptSearchForm = ''
    },
    //重置搜索条件
    resetQuery(){
      this.s
    },
    //点击编辑按钮
    async clickEdit(item){
      //获取部门回显信息
      await this.getDeptInfo(item.id);
      //获取部门树形列表
      this.getDeptOptions();

      console.log("部门信息：" + JSON.stringify(this.form))
      console.log("deptOptions:" + JSON.stringify(this.deptOptions) )

      this.open = true;

    },
    //点击新增按钮
    async clickAdd(){
      this.reset();
      this.getDeptOptions();
      this.title = "新增部门";
      this.open = true;
    },
    //获取表单回显数据
    async getDeptInfo(id){
      const result = await deptInfoApi(this.$store.state.accessToken,id);
      if (result.data.code == 0){
        this.form = result.data.data;
      }else {
        this.$message.error(result.data.msg);
      }
    },
    getDeptOptions(){
      const dept = [{id: "0",parentName: "顶级部门",children: {}}]
      dept[0].children = this.deptTree;
      this.deptOptions.push(dept)
    },
    //点击树形节点
    handleNodeClick(item){
      this.form.pid = item.parentId;
    },
    //重置表单
    reset(){
      this.form = {
        id: undefined,
        name: undefined,
        pid: undefined,
        managerName: undefined,
        phone: undefined,
        status: 1
      }
    },
    //提交表单
    async submitForm(){
      //是否存在id
      if (this.form.id != null && this.form.id != undefined){
        //存在id，编辑部门
        const result = await deptEditApi(this.$store.state.accessToken,this.form);
        if (result.data.code == 0){
          this.$message.success("操作成功");
          this.open = false;
          this.getDeptTree();
        }else {
          this.$message.error(result.data.msg);
        }
      }else {
        //不存在id，为新增部门
        const result = await deptAddApi(this.$store.state.accessToken,this.form);
        if (result.data.code == 0){
          this.open = false;
        }else {
          this.$message.error(result.data.msg);
        }
      }
    },
    //取消操作
    cancel(){
      this.open = false;
      this.form = {}
    }
  },
  mounted() {
    this.getDeptTree();
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
