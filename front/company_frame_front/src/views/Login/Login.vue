<template>
  <div class="login-div">
    <el-form :label-position="labelPosition" label-width="80px" :model="loginModel">
      <el-form-item label="用户名">
        <el-input v-model="loginModel.name" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="loginModel.pwd" type="password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-button-group class="login-but">
        <el-button type="primary" style="width: 100%" @click="login">登录</el-button>
      </el-button-group>
    </el-form>
    <el-row class="other-login">
      <el-col :span="24">
        其他方式登录
      </el-col>
      <el-col :span="12">
        <el-button circle @click="wechatLoginVisible = true">
          <img src="http://oss.codewoo.cn/img/微信.png"/>
        </el-button>


        <el-dialog title="微信登录" :visible.sync="wechatLoginVisible">
          微信登录二维码
        </el-dialog>


      </el-col>
      <el-col :span="12">
        <el-button circle @click="toAlipayLogin">
          <img src="http://oss.codewoo.cn/img/支付宝.png"/>
        </el-button>
      </el-col>
    </el-row>
    <common-footer class="common-footer"></common-footer>
  </div>
</template>

<script>
import {getAlipayLoginUrl, usernamePasswordLoginApi} from "@/api/getData";
import CommonFooter from "@/components/CommonFooter/CommonFooter";
export default {
  name: "Login",
  components: {
    CommonFooter
  },
  data() {
    return {
      labelPosition: "top",
      loginModel: {
        name: '',
        pwd: ''
      },


      wechatLoginVisible: false,
      alipayLoginVisible: false,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      formLabelWidth: '120px'


    }
  },
  methods: {
    async login(){
      console.info(this.loginModel.name + " : " + this.loginModel.pwd)
      usernamePasswordLoginApi(this.loginModel.name,this.loginModel.pwd).then(async (resp) => {
        console.info(resp.data);
        if (resp.data.code == 0){
          localStorage.setItem("accessToken",resp.data.data.accessToken);
          localStorage.setItem("refreshToken",resp.data.data.refreshToken)
          await this.$store.dispatch("setToken", resp.data.data.accessToken);
          await this.$store.dispatch("setRefreshToken", resp.data.data.refreshToken);
          await this.$router.push({path: '/'})
        }else {
          this.$message(resp.data.msg);
        }
      })
    },

    async toAlipayLogin(){
      const loginUrl = await getAlipayLoginUrl();
      window.location.href = loginUrl.data.data;
    }

  }
}
</script>

<style lang="scss" scoped>
.login-div{
  width: 500px;
  height: 500px;
  margin: 0 auto;
}
.login-but{
  width: 100%;
}
.other-login{
  margin-top: 20px;
  font-size: 18px;
  line-height: 34px;
  img{
    width: 34px;
    height: 34px;
  }
}
.common-footer{
  text-align: center;
  position: absolute;
  bottom: 0;
}
</style>
