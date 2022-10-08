<template>
  <div class="main" :style="'height:'+fullHeight">
  <div class="bg" >
    <div class="site-wrapper site-page--login">
        <div class="login-main" >
          <img class="logo" src="~@/assets/img/logo.png">
          <div><h3 class="login-title">抽奖平台DEMO</h3></div>
          <div style="width: 100%">
            <el-form  :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon
                      class="validate-input">
              <el-form-item prop="userName">
                <el-input v-model="dataForm.account" placeholder="请输入帐号" size="medium" prefix-icon="account-icon"></el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input v-model="dataForm.password" type="password" placeholder="请输入密码" @focus="isShowVerify()" prefix-icon="pwd-icon"></el-input>
              </el-form-item>
              <el-form-item prop="captcha" v-if="dataForm.showVerify">
                <el-row :gutter="20">
                  <el-col :span="14">
                    <el-input v-model="dataForm.captcha" placeholder="验证码">
                    </el-input>
                  </el-col>
                  <el-col :span="10" class="login-captcha">
                    <img :src="captchaPath" @click="getCaptcha()" alt="">
                  </el-col>
                </el-row>
              </el-form-item>
              <el-form-item>
                <el-button class="login-btn-submit" type="primary"  style="background-color: #0a5efe;" @click="dataFormSubmit()">登录</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>

    </div>
  </div>
  </div>
</template>

<script>
import { getCodeImg, getCodeNeed } from "@/api/login";

export default {
  name: "Login",
  data () {
    var validateCaptcha = (rule, value, callback) => {
      if (!this.dataForm.showVerify) {
        callback()
        return
      }
      if (value === '') {
        callback(new Error('请输入验证码'))
        return
      }
      if (value.length !== 4) {
        callback(new Error('验证码格式错误'))
      }
      callback()
    }
    return {
      fullHeight:  window.innerHeight + 'px',
      li: ['logo.png'],
      i:'',
      loading: false,
      dataForm: {
        account: '',
        password: '',
        captcha: '',
        showVerify: false
      },
      dataRule: {
        account: [
          { required: true, message: '帐号不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        captcha: [
          { validator: validateCaptcha, trigger: 'blur' }
        ]
      },
      captchaPath: '',
      forgetVisible: false
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    let code = this.$route.query.code
    if(code){
      this.$store
        .dispatch("sso", code)
        .then((res) => {
          this.$router.push({ path: this.redirect || "/" });
        })
        .catch(() => {
          this.loading = false;
          this.isShowVerify();
        })
    }
  },
  methods: {
    // 获取验证码
    getCaptcha () {
      getCodeImg(this.dataForm.account).then (res =>{
        this.captchaPath = "data:image/gif;base64," + res.data
      })
      console.log(this.captchaPath)
    },
    isShowVerify () {
      getCodeNeed(this.dataForm.account).then(res => {
        this.dataForm.showVerify = res.data
        if (this.dataForm.showVerify) {
          this.getCaptcha()
        }
      })
    },
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.loading = true;
          this.$store
            .dispatch("Login", this.dataForm)
            .then((res) => {
              this.$router.push({ path: this.redirect || "/" });
            })
            .catch(() => {
              this.loading = false;
              this.isShowVerify();
            });
        }
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
  .bg {
    /*position: fixed;*/
    /*top: -10%;*/
    /*left: 0;*/
    /*z-index: -1;*/
    width: 100%;
    height: 100%;
    content: "";
    background-size: cover;
    background-image: url(~@/assets/img/draw/hz1.png);
    /*opacity: .9;*/
  }
  .site-wrapper.site-page--login {
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    input {
      height: 52px;
      line-height: 36px;
      line-height: 1.2;
      display: block;
      overflow: visible;
      outline: none;
      border: none;
      width: 100%;
      height: 52px;
      background: transparent;
      padding: 0 45px;
      color: black;
      font-size: 20px;
      border-bottom: 2px solid #0a5efe;
    }
    input :after {
      background: transparent;
      padding: 0 5px;
      border-bottom: 2px solid #0a5efe;
    }
    ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
      color:    #0a5efe;
    }
    :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
      color:    #0a5efe;
    }
    ::-moz-placeholder { /* Mozilla Firefox 19+ */
      color:    #0a5efe;
    }
    :-ms-input-placeholder { /* Internet Explorer 10-11 */
      color:    #0a5efe;
    }

    .login-main {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 30%;
      height: 100%;
      background-color:  rgba(255,255,255,0.9);
      padding: 10px 15px 0px 15px;
      box-shadow:0 0 2px #e0e0e0;
      flex-direction: column;
    }
    .login-main .logo {
      text-align: center;
      width: 128px;
    }
    .validate-input{
      width: 90%;
      margin-left: 5%;
    }
    .login-title {
      font-size: 35px;
      text-align: center;
      color: #222222;
    }
    .login-captcha {
      overflow: hidden;
      > img {
        width: 100%;
        height: 52px;
        cursor: pointer;
      }
    }
    .login-btn-submit {
      width: 100%;
      margin-top: 15px;
      height: 52px;
      font-size: 20px;
    }
  }
  .el-form-item__error {
    color: #dd6161;
    font-size: 14px;
    line-height: 1;
    padding-top: 4px;
    position: absolute;
    top: 100%;
    left: 0;
  }
  .account-icon{
    font-family: element-icons!important;
    speak: none;
    font-style: normal;
    font-weight: 400;
    font-variant: normal;
    text-transform: none;
    line-height: 1;
    vertical-align: baseline;
    display: inline-block;
    margin-top: 10px;
    width: 35px;
    height: 35px;
    background: url(~@/assets/img/account.png) no-repeat;
  }
  .pwd-icon{
    font-family: element-icons!important;
    speak: none;
    font-style: normal;
    font-weight: 400;
    font-variant: normal;
    text-transform: none;
    line-height: 1;
    vertical-align: baseline;
    display: inline-block;
    margin-top: 10px;
    width: 35px;
    height: 35px;
    background: url(~@/assets/img/pwd.png) no-repeat;
  }
</style>
