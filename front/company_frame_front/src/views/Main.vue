<template>
  <el-container style="height: 100%">
    <el-aside width="auto"><common-aside></common-aside></el-aside>
    <el-container>
      <el-header style="height: 100px">
        <common-header></common-header>
        <common-tab></common-tab>
      </el-header>

      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import CommonAside from "@/components/CommonAside/CommonAside";
import CommonHeader from "@/components/CommonHeader/CommonHeader";
import CommonTab from "@/components/CommonTab/CommonTab";
import {getUserMenuTreeApi} from "@/api/getData";
export default {
  name: "Main",
  components: {
    CommonHeader,
    CommonAside,
    CommonTab
  },
  methods: {
    async getMenuList(){
      const token = this.$store.state.accessToken;
      const result = await getUserMenuTreeApi(token);
      if (result.data.code == 0){
        await this.$store.dispatch("setMenuList", result.data.data.menus);
        await this.$store.dispatch("setUserInfo", result.data.data.userInfo);
        console.log("resultUserinfo:" ,result.data.data.userInfo)
        console.log(this.menuList);
      }else{
        this.$message.error(result.data.msg)
      }
    }
  },
  mounted() {
    this.getMenuList();
  }
}
</script>

<style lang="scss" scoped>
.el-header {
  background-color: #2db3a4;
  height: 120px;
}
.el-main {
  padding-top: 0;
}
</style>
