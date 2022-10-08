<template>
  <div class="lock-bg" >

    <div class="site-wrapper site-page--lock">
      <div class="site-content">
        <div class="lock-main">
          <img src="~@/assets/img/logo.png" class="lock-logo">
          <p class="lock-title">{{userName}}</p>
          <el-form  :model="dataForm" :rules="dataRule" ref="dataForm"  status-icon class="lock-validate-input"  @submit.native.prevent="dataFormSubmit">
            <el-form-item prop="password">
              <el-input v-model="dataForm.password" type="password" placeholder="请输入您的密码继续登录"  suffix-icon="pwd-icon"></el-input>
            </el-form-item>
            <el-form-item>
              <el-link  @click="toLogin()" style="color: rgb(228, 228, 228);">或者使用其他账号登录</el-link>
            </el-form-item>
          </el-form>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
  import screenfull from 'screenfull'
  export default {
    data () {
      return {
        bg: 'bg.jpg',
        dataForm: {
          account: '',
          password: '',
          showVerify: false
        },
        dataRule: {
          account: [
            { required: true, message: '帐号不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ]
        },
        forgetVisible: false
      }
    },
    computed: {
      userName () {
        return this.$store.state.user.name
      }
    },
    created () {
      if (!screenfull.enabled) {
        this.$message({
          message: 'you browser can not work',
          type: 'warning'
        })
        return false
      }
      screenfull.toggle()
    },
    components: {
    },
    methods: {
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            debugger
            this.dataForm.account = this.$store.state.user.user.loginName
            console.log(this.dataForm)
            this.$store
              .dispatch("Login", this.dataForm)
              .then((res) => {
                screenfull.exit()
                this.$router.push({ path: this.redirect || "/" });
              })
              .catch(() => {
              })
          }
        })
      },
      toLogin () {
        screenfull.exit()
        this.$router.push({ path: 'login' , redirect: '/index'})
      },
      change() {
        this.isFullscreen = screenfull.isFullscreen
      },
      init() {
        if (screenfull.enabled) {
          screenfull.on('change', this.change)
        }
      },
      destroy() {
        if (screenfull.enabled) {
          screenfull.off('change', this.change)
        }
      }
    }
  }
</script>

<style lang="scss">
  .lock-bg {
    width: 100%;
    height: 100%;
    content: "";
    background-size: cover;
    /*background-image: url(~@/assets/img/bg.gif);*/
    /*opacity: .9;*/
  }
  .lock-logo{
    position: absolute;
    left:45%;
    top: 20%;
    height: 90px;
    width: 90px;
    border-radius: 45px;
    background-color: white;
    text-align: center;
    line-height: normal;
    text-align: center;
    line-height: normal;
  }
  .lock-title{
    font-family: "微软雅黑 Regular", 微软雅黑;
    font-weight: 400;
    font-style: normal;
    font-size: 18px;
    color: rgb(255, 255, 255);
    position: absolute;
    left:45%;
    top: 31%;
  }

  .site-wrapper.site-page--lock {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    /*background-color: rgba(38, 50, 56, .6);*/
    overflow: hidden;

  .site-content {
    min-height: 100%;
    padding: 30px 500px 30px 30px;
  }

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
    border-radius: 1px;
    background-color: #f0f7ff;
    padding: 0 10px;
    color: black;
    font-size: 14px;
    border: 2px solid #f0f7ff;
  }

  input :after {
    background-color: #f0f7ff;
    padding: 0 5px;
    border: 2px solid #f0f7ff;
  }

  ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
    color: #999999;
  }

  :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
    color: #999999;
  }

  ::-moz-placeholder { /* Mozilla Firefox 19+ */
    color: #999999;
  }

  :-ms-input-placeholder { /* Internet Explorer 10-11 */
    color: #999999;
  }

  .lock-main {
    position: fixed;
    top: 50%;
    left: 50%;
    -webkit-transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
    width: 100%;
    height: 100%;
    z-index: 100;
    background-color: rgba(0, 0, 0, 0.4);
    /*min-height: 100%;*/
    /*background-color: #fff;*/
  }

  .lock-validate-input {
    position: absolute;
    left: 33%;
    top: 38%;
    text-align: center;
    width: 20%;
    margin-left: 5%;
  }
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
