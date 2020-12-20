<template>
  <header>
    <div class="l-content">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="current.url" v-if="current != null ">
          {{current.name}}
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="r-content">
      <div v-if="userinfo.username">
        <el-dropdown trigger="click" size="mini">
          <span class="el-dropdown-link">{{ userinfo.username }}</span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="clickPersonal">个人中心</el-dropdown-item>
            <el-dropdown-item @click.native="loginOut">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>

      <div v-else class="login-tips">
        <router-link to="/login">
          <i class="el-icon-user-solid">登录</i>
        </router-link>
      </div>
    </div>
  </header>
</template>

<script>
import jwtDecode from 'jwt-decode'
import {mapState} from 'vuex'
import {loginOutApi} from "@/api/getData";
export default {
  name: "CommonHeader",
  data() {
    return {
      // userinfo: {}
    }
  },
  computed: {
    ...mapState({
      current: state => state.tab.currentMenu,
      userinfo: state => state.userInfo
    })
  },
  methods: {
    getUserInfo() {
      this.userinfo = this.$store.state.userInfo;
      console.log("userinfo:",this.userinfo)
    },
    loginOut() {
      console.log("loginOut")
      loginOutApi(this.$store.state.accessToken,this.$store.state.refreshToken);
      localStorage.removeItem("accessToken");
      localStorage.removeItem("refreshToken")
      this.$store.dispatch("clearToken");
      this.$store.dispatch("clearRefreshToken")
      this.$router.push("/login")
    },
    clickPersonal(){
      this.$router.push("/personal_center")
    }
  },
  mounted() {
    // this.getUserInfo();
    // this.getUserInfo();
  },
}
</script>



<style lang="scss" scoped>
header {
  display: flex;
  height: 65%;
  align-items: center;
  justify-content: space-between;
}
.l-content {
  display: flex;
  align-items: center;
  .el-button {
    margin-right: 20px;
  }
}
.r-content {
  .user {
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }
}
</style>

<style lang="scss">
.el-breadcrumb__item {
  .el-breadcrumb__inner {
    color: #fff;
    font-weight: normal;
  }
  &:last-child {
    .el-breadcrumb__inner {
      color: #666;
    }
  }
}
</style>
