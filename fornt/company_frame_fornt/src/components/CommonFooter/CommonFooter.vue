<template>
  <div class="content">
<!--    <a href="#" @click="">蜀ICP备20009612号-1</a>
    <router-link :to="beian.miit.gov.cn">11111</router-link>-->
    <a class="a-style" @click="linkDownload('http://beian.miit.gov.cn')">蜀ICP备20009612号-1</a>
  </div>
</template>

<script>
export default {
  name: "CommonFooter",
  methods: {
    linkDownload(url){
      window.open(url,"_blank");
    }
  }
}
</script>

<style scoped>
.content{
  text-align: center;
}
a{
  text-decoration: none;
}
.a-style {
  color: #584028;
  cursor: pointer;
  text-decoration: none;
}
</style>
