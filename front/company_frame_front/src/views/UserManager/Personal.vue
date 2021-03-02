<template>
  <div class="content">

  </div>
</template>

<script>
import {userInfoApi} from "@/api/getData";

export default {
  name: "Personal",
  data(){
    return{
      userinfo: {}
    }
  },
  methods: {
    async getUserInfo(){
      const result = await userInfoApi(this.$store.state.accessToken);
      if (result.data.code == 0){
        this.userinfo = result.data.data;
      }else{
        this.$message.error(result.data.msg);
      }
    }
  },
  mounted() {
    this.getUserInfo();
  }
}
</script>

<style scoped>

</style>
