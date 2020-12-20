<template>
  <div>
    <i class="el-icon-loading"></i>
  </div>
</template>

<script>
import {} from "@/api/getData";

export default {
  name: "Token",
  methods: {
    async saveToken() {
      const token = this.$route.query.token;
      console.log(token)
      if (token != null && token != "" && token != undefined){
        localStorage.setItem("token",token);
        this.$store.commit("SET_TOKEN",token);
      }
      await this.$router.push("/");
    }
  },
  mounted() {
    this.saveToken();
  }
}
</script>

<style scoped>

</style>