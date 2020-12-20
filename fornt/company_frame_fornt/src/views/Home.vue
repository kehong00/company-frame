<template>
  <div class="home">
    <el-container>
      <el-aside width="200px"><common-aside></common-aside></el-aside>
      <el-container>
        <el-header><common-header></common-header></el-header>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import CommonAside from "@/components/CommonAside/CommonAside";
import CommonHeader from "@/components/CommonHeader/CommonHeader";
export default {
  name: 'Home',
  components: {
    CommonAside,
    CommonHeader
  }
}
</script>
<style lang="scss" scoped>
  .home{
    margin: 0;
    padding: 0;
    height: 100vh;
  }
  el-container{
    padding: 0;
    margin: 0;
  }
</style>
