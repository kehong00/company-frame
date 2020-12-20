<template>
  <div>
    <el-menu style="width: 200px"
        default-active="/index"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        @select="menuSelect"
    >
      <el-menu-item :index="item.url" :key=item.id @click="clickMenu(item)" v-for="(item,index) in noChildren">
        <i class="el-icon-menu"></i>
        <span slot="title">{{item.name}}</span>
      </el-menu-item>

      <el-submenu :index="item.url != null ? item.url : item.id" :key=item.id @click="clickMenu(item)" v-for="(item,index) in hasChildren">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>{{item.name}}</span>
        </template>
          <el-menu-item :index="subItem.url ? subItem.url : subItem.id" :key=subItem.id @click="clickMenu(subItem)"  v-for="(subItem,subIndex) in item.children">{{ subItem.name }}</el-menu-item>
      </el-submenu>

      <!--<el-submenu :index="item.url != null ? item.url : index" :key="index" v-for="(item,index) in hasChildren">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>{{item.name}}</span>
        </template>
        <el-menu-item-group>
          <el-menu-item :index="subItem.url" :key="index" v-for="(subItem,index) in item">
            <i class="el-icon-menu"></i>
            <span slot="title">{{subItem.name}}</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>-->
    </el-menu>
  </div>

</template>

<script>
import {getUserMenuTreeApi} from "@/api/getData";
import {mapState} from "vuex";

export default {
  name: "CommonAside",
  data(){
    return {
    }
  },
  computed: {
    noChildren(){
      return this.menuList.filter(item => item.children.length == 0)
    },
    hasChildren(){
      return this.menuList.filter(item => item.children.length !== 0)
    },
    ...mapState({
      menuList: state => state.menuList,
    })
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    menuSelect(key,keyPath){
      this.$store.dispatch("setSelectMenuKey",keyPath);
    },
    test(){
      console.log("nochildren:" + this.noChildren[0].id)
      console.log("haschildren:" + this.hasChildren[0])
    },
    clickMenu(item){
      this.$router.push({name: item.name})
      this.$store.commit("selectMenu",item);
    },
    getMenuList(){
      console.log("llll",this.hasChildren)
    }
  },
  mounted() {
    this.getMenuList()
  }
}
</script>

<style scoped>
.el-menu{
  height: 100%;
}

</style>
