<template>
  <div class="wall">
    <div class="gift-title">
      <h1 v-for="item in gifts" style="font-size:45px"><span style="font-style: italic;margin-right: 20px;font-size:45px">{{item.name}}</span>抽奖活动
      </h1>
    </div>

    <div id="main1">
      <canvas id="myCanvas">
      </canvas>

    </div>
    <div id="result" class="result">
    </div>

    <div class="cssBox" @click="toggle">
      <div class="borderRadius">
        <div class="lfRaidus">
          <!--          <div class="ctRaidus" style=""></div>-->
        </div>
        <div class="rtRaidus">
          <div class="ctRaidus"></div>
          <div class="hfPoint"></div>
          <div class="rtPoint"></div>
        </div>
      </div>
      <div class="imageRadius">
        <img :src="running? chouUrl1:chouUrl1" width="100%" alt="">
      </div>
      <div class="waveRaidus">
        <img :src="running? chouUrl1:chouUrl1" width="100%" alt="">
      </div>
      <div class="pointRadius">
        <img :src="running? chouUrl3:chouUrl2" width="100%" alt="">
      </div>
    </div>


    <el-dialog
      :visible.sync="dialogVisible"
      :fullscreen="fullscreen"
      width="100%" style="text-align: center" :close-on-click-modal="false">
      <div class="dialog-close" @click="close">X</div>
      <div class="show">
        <h3 style="font-size: 50px;">恭喜您！中奖了</h3>
        <div class="gift">{{giftName}}</div>
        <div class="res-main">
          <div v-for="item in lucks" :key="this" style="width: 100%;margin-top: 50px;display: block">
            <div class="card cardLeft">
              <h1>{{item.company}}</h1>
              <div class="title">
                <h2>{{item.name}}</h2>
              </div>
              <div class="name">
                <h2>{{item.mobile}}</h2>
              </div>

            </div>
            <div class="card cardRight">
              <div class="eye"></div>
              <div class="number">
                <h3>奖</h3>
<!--                <span>__</span>-->
              </div>
              <div class="barcode"></div>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>

    <el-dialog
      :visible.sync="dialogEnd"
       style="height: 250px;width:100%;text-align: center;position:fixed;top:25%;border-radius: 10px;" :close-on-click-modal="false">
      <div class="show" style="margin-top: 25px">
        <p style="font-size: 50px;color:white;font-weight: bold">本次活动已结束</p>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import '@/utils/draw/tagcanvas'
  import chou1 from '@/assets/img/draw/1.png'
  import chou2 from '@/assets/img/draw/2.png'
  import chou3 from '@/assets/img/draw/3.png'
  import '@/utils/draw/zepto'
  import screenfull from 'screenfull'
  import {getInfo, getResult, updateResult} from '@/api/draw/lottery'

  export default {
    name: "",
    data() {
      return {
        fullscreen: false,
        member: [],
        dialogEnd: false,
        chouUrl1: chou1,
        chouUrl2: chou2,
        chouUrl3: chou3,
        color: [
          '#b0c4de',
          '#CCCCFF',
          '#FFFFCC',
          '#CCFFFF',
          '#FFCCCC',
          '#99CCCC',
          '#FFFFFF',
          '#3399CC',
          '#CCFFCC',
          '#99CCCC',
          '#FFFFCC',
          '#CCFF99',
          '#FFFFFF',
          '#99CCFF',
          '#99CCCC',
          '#FFFFFF',
          '#CCFF99',
          '#CCFFCC',
          '#FFFFFF',
          '#66CCCC',
        ],
        giftName: undefined,
        dialogVisible: false,
        results: undefined,
        running: false,
        canvas: undefined,
        gifts: [],
        params: {},
        lucks: []
      }
    },
    created() {
      if (!screenfull.enabled) {
        this.$message({
          message: 'you browser can not work',
          type: 'warning'
        })
        return false
      }
      screenfull.toggle()
      let data = JSON.parse(localStorage.getItem("giftCustomer"))
      this.gifts = data.gifts
      this.member = data.users
    },
    mounted() {
      this.params = {
        themeId: this.$route.query.themeId,
        giftId: this.$route.query.giftId
      }
      this.init()
      getResult(this.params).then((res) => {
        this.lucks = res.data.users
        this.giftName = res.data.gifts[0].name
        if (this.lucks.length === 0) {
         this.dialogEnd = true
          return
        } else if(this.lucks.length>2){
          this.fullscreen = true
        }
      }).catch((e)=>{
        this.dialogEnd = true
      })
    },
    methods: {
      getInfo() {
        getInfo(this.params).then((res) => {
          debugger
          if(res.data == null){
            this.dialogEnd = true
            return
          }
          this.gifts = res.data.gifts
          this.member = res.data.users
          this.init()
        })
      },
      init() {
        this.canvas = document.getElementById("myCanvas")
        this.canvas.width = document.body.offsetWidth;
        this.canvas.height = document.body.offsetHeight;
        this.canvas.innerHTML = this.createHTML();
        console.log('==', new Date().getTime())
        TagCanvas.Start('myCanvas', '', {
          textColour: null,
          initial: this.speed(),
          dragControl: 1,
          textHeight: 14
        });
        console.log('==', new Date().getTime())
      },
      close() {
        this.dialogVisible = false
        this.dialogEnd = true
      },
      speed() {
        let res = [0.1 * Math.random() + 0.01, -(0.1 * Math.random() + 0.01)]
        return res
      },
      createHTML() {
        while(this.member.length < 300){
          this.member.push(...this.member)
        }
        let html = ['<ul>'];
        this.member.forEach((item) => {
          html.push('<li>' +
            '<a href="#" style="color: ' + this.getColor() + ';">' + item.name + '</a>' +
            '</li>');
        });
        html.push('</ul>');
        return html.join('');
      },
      getColor() {
        let i = Math.floor(Math.random() * this.color.length)
        return this.color[i]
      },
      toggle() {
        if (this.running) {
          if (this.lucks.length === 0) {
            this.dialogEnd = true
            return
          }
          updateResult(this.params)
          this.dialogVisible = true
           TagCanvas.SetSpeed('myCanvas', this.speed());
        //  TagCanvas.Reload('myCanvas');
          setTimeout(() => {
            $('#main1').addClass('mask');
          }, 300);
        } else {
          this.dialogVisible = false
          $('#main1').removeClass('mask');
          TagCanvas.SetSpeed('myCanvas', [5, 1]);
        }
        this.running = !this.running;
      }
    }
  }
</script>

<style scoped>
  @import '~@/assets/styles/css/reset.css';
  @import '~@/assets/styles/css/wall.css';

  .result {
    position: absolute;
    height: 320px;
    width: 100%;
    left: 0;
    top: 50%;
    margin-top: -160px;
    text-align: center;
    padding: 10px;
    display: none;
  }

  .result span {
    display: inline-block;
    font-size: 25px;
    width: 150px;
    background: #fff;
    line-height: 30px;
    color: #000;
    margin: 5px;
    border-radius: 10px;
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.8);
    padding: 10px 0;
  }

  button, input, optgroup, select, textarea {
    color: inherit;
    font: inherit;
    margin: 0;
    border: none;
  }

  button {
    overflow: visible;
  }

  button, select {
    text-transform: none;
  }

  button, html input[type=button], input[type=reset], input[type=submit] {
    -webkit-appearance: button;
    cursor: pointer;
  }




  .mask {
    -webkit-filter: blur(5px);
    filter: blur(5px);
  }

  @keyframes ani {
    to {
      max-width: 100%;
    }
  }

  @keyframes ani2 {
    to {
      transform: translate(50vw, -50%);
    }
  }

  ::v-deep .el-dialog__header {
    display: none;
    /* padding: 20px; */
    /* padding-bottom: 10px; */
  }
  ::v-deep .el-dialog{
    width: 100% !important;
    box-shadow: 0 1px 3px rgba(0,0,0,0) !important;
  }
  ::v-deep .el-dialog__wrapper {
    background-image: url("~@/assets/img/draw/bg2.gif");
    background-attachment: scroll;
    background-size:100% 100%;
    background-repeat:no-repeat;
    overflow: hidden;
  }

  ::v-deep .el-dialog__body {
    /* padding: 30px 20px; */
    color: #606266;
    font-size: 14px;
    word-break: break-all;
    height: 50vh;
    display: flex;
    justify-content: center;
    border-radius: 10px;
  }

  ::v-deep .el-dialog {
    position: relative;
    margin: 0 auto 50px;
    background: rgba(0, 0, 0, 0.0);
    border-radius: 2px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
    box-sizing: border-box;
    width: 50%;
  }

  .dialog-close {
    width: 30px;
    height: 30px;
    color: white;
    border: 2px solid #DB3304;
    border-radius: 45%;
    padding-top: 3px;
    position: absolute;
    font-weight: bolder;
    top: -30px;
  }

  .show h3 {
    font-size: 30px;
    color: white;
    font-family: Arial, Helvetica, sans-serif;
  }

  .show .gift {
    font-size: 40px;
    color: white;
    font-family: Arial, Helvetica, sans-serif;
    margin-top: 35px;
    font-weight: bolder;
  }

  .res-main {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
  }

  .card {
    background: linear-gradient(to bottom, #e84c3d 0%, #e84c3d 26%, #ecedef 26%, #ecedef 100%);
    height: 35em;
    position: relative;
    padding: 1em;
  }

  .cardLeft {
    border-top-left-radius: 8px;
    border-bottom-left-radius: 8px;
    width: 50em;
    float: left;
  }

  .cardRight {
    width: 20.5em;
    border-left: .18em dashed #fff;
    border-top-right-radius: 8px;
    border-bottom-right-radius: 8px;
    float: left;
  }

  .cardRight:before, .cardRight:after {
    content: "";
    position: absolute;
    display: block;
    width: .9em;
    height: .9em;
    background: #fff;
    border-radius: 50%;
    left: -.5em;
  }

  .cardRight:before {
    top: -.4em;
  }

  .cardRight:after {
    bottom: -.4em;
  }

  h1 {
    font-size: 3em;
    margin-top: 35px;
    color: white;
  }

  h1 span {
    font-weight: normal;
  }

  .title, .name {
    text-transform: uppercase;
    font-weight: normal;
  }

  .title h2, .name h2, .seat h2, .time h2 {
    font-size: 5em;
    font-weight: bold;
    color: #525252;
    margin: 0;
  }

  .title span, .name span, .seat span, .time span {
    font-size: 1em;
    color: #a2aeae;
  }

  .title {
    margin: 12em 0 0 0;
  }

  .name {
    margin: 6.7em 0 0 0;
  }


  .eye {
    position: relative;
    width: 12em;
    height: 6.5em;
    background: #fff;
    margin: 0px auto;
    border-radius: 4em/4.6em;
    z-index: 1;
  }

  .eye:before, .eye:after {
    content: "";
    display: block;
    position: absolute;
    border-radius: 50%;
  }

  .eye:before {
    width: 5em;
    height: 5em;
    background: #e84c3d;
    z-index: 2;
    left: 50px;
    top: 10px;
  }

  .eye:after {
    width: 3em;
    height: 3em;
    background: #fff;
    z-index: 3;
    left: 63px;
    top: 24px;
  }

  .number {
    text-align: center;
    text-transform: uppercase;
  }

  .number h3 {
    color: #e84c3d;
    margin: .9em 0 0 0;
    font-size: 9.5em;
  }

  .number span {
    display: block;
    color: #a2aeae;
  }

  .barcode {
    height: 5em;
    width: 1px;
    margin: 7.2em 0 0 1em;
    box-shadow: 1px 0 0 1px #343434, 5px 0 0 1px #343434, 10px 0 0 1px #343434, 11px 0 0 1px #343434, 15px 0 0 1px #343434,
    18px 0 0 1px #343434, 22px 0 0 1px #343434, 23px 0 0 1px #343434, 26px 0 0 1px #343434,
    30px 0 0 1px #343434, 35px 0 0 1px #343434, 37px 0 0 1px #343434, 41px 0 0 1px #343434,
    44px 0 0 1px #343434, 47px 0 0 1px #343434, 51px 0 0 1px #343434, 106px 0 0 1px #343434,
    59px 0 0 1px #343434, 64px 0 0 1px #343434, 68px 0 0 1px #343434, 72px 0 0 1px #343434,
    74px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    85px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    98px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    108px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    102px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    118px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    128px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    131px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    130px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    128px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    135px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    139px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    /*140px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,*/
    142px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    148px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    150px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    92px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    /*153px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,*/
    155px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    158px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    166px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    170px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    174px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    113px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    183px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    186px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    198px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    211px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    193px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    203px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    217px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    223px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434,
    162px 0 0 1px #343434, 77px 0 0 1px #343434, 81px 0 0 1px #343434;
  }
  /*按钮*/
  .cssBox {width: 360px;height: 360px;position: fixed; bottom:0;right: 0 }
  .cssBox .borderRadius {width: 276px;height: 276px;
    position: absolute;left: 50%;top: 50%;margin: -138px 0 0 -138px;
    -webkit-animation: rotating 2s ease 0s infinite;
    animation: rotating 2s ease 0s infinite;
  }
  @-webkit-keyframes rotating {
    0% {
      -webkit-transform: rotate(0deg);
      transform: rotate(0deg);
      opacity: 1;
    }
    65% {
      -webkit-transform: rotate(90deg);
      transform: rotate(90deg);
      opacity: 1;
    }
    100% {
      -webkit-transform: rotate(90deg);
      transform: rotate(90deg);
      opacity: 0;
    }
  }
  .cssBox .borderRadius .lfRaidus {width: 276px;height: 138px;border-radius: 138px 138px 0 0;position: relative;
    -webkit-animation: lfRaidus 2s ease 0s infinite;
    animation: lfRaidus 2s ease 0s infinite;
  }
  @-webkit-keyframes lfRaidus {
    0% {
      background: -moz-linear-gradient(right,red,black);background: -webkit-linear-gradient(right,#0F956F,#202A38);background: -o-linear-gradient(right,red,black);
    }
    65% {
      background: -moz-linear-gradient(right,red,black);background: -webkit-linear-gradient(right,#0F956F,#202A38);background: -o-linear-gradient(right,red,black);
    }
    100% {
      background: -moz-linear-gradient(right,red,black);background: -webkit-linear-gradient(right,#0F956F,#202A38);background: -o-linear-gradient(right,red,black);
    }
  }
  .cssBox .borderRadius .lfRaidus .ctRaidus {width: 262px;height: 131px;background-color: #202A38;border-radius: 131px 131px 0 0;
    position: absolute;left: 50%;bottom: 0;-webkit-transform: translate(-50%, 0);transform: translate(-50%, 0);
  }
  .cssBox .borderRadius .rtRaidus {width: 276px;height: 138px;border-radius: 0 0 138px 138px;position: relative;
    -webkit-animation: rtRaidus 2s ease 0s infinite;
    animation: rtRaidus 2s ease 0s infinite;
  }
  @-webkit-keyframes rtRaidus {
    0% {
      background: -moz-linear-gradient(right,red,black);background: -webkit-linear-gradient(right,#0F956F,#00FFA8);background: -o-linear-gradient(right,red,black);
    }
    65% {
      background: -moz-linear-gradient(right,red,black);background: -webkit-linear-gradient(right,#0F956F,#00FFA8);background: -o-linear-gradient(right,red,black);
    }
    100% {
      background: -moz-linear-gradient(right,red,black);background: -webkit-linear-gradient(right,#0F956F,#00FFA8);background: -o-linear-gradient(right,red,black);
    }
  }
  .cssBox .borderRadius .rtRaidus .ctRaidus {width: 262px;height: 131px;background-color: #202A38;border-radius: 0 0 131px 131px;
    position: absolute;left: 50%;top: 0;-webkit-transform: translate(-50%, 0);transform: translate(-50%, 0);
  }
  .cssBox .borderRadius .rtRaidus .hfPoint {width: 7px;height: 5px;position: absolute;left: 0px;top: -5px;border-radius: 5px 5px 0 0;background-color: #00FFA8;}
  .cssBox .borderRadius .rtRaidus .rtPoint {width: 5px;height: 5px;position: absolute;left: 10px;top: -5px;border-radius: 5px;background-color: #00FFA8;}
  .cssBox .imageRadius {margin-top: 4px;opacity: 0;width: 100%;height: 100%;
    position: absolute;left: 50%;top: 50%;-webkit-transform: translate(-50%, -50%);transform: translate(-50%, -50%);
    -webkit-animation: imageRadius 2s ease 0s infinite;
    animation: imageRadius 2s ease 0s infinite;
  }
  @-webkit-keyframes imageRadius {
    0% {
      opacity: 0;
    }
    65% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
  .cssBox .waveRaidus {margin-top: 4px;opacity: 0;
    position: absolute;left: 50%;top: 50%;-webkit-transform: translate(-50%, -50%);transform: translate(-50%, -50%);
    -webkit-animation: waveRaidus 2s ease-out 0s infinite;
    animation: waveRaidus 2s ease-out 0s infinite;
  }
  @-webkit-keyframes waveRaidus {
    0% {
      width: 100%;height: 100%;
      opacity: 0;
    }
    65% {
      width: 100%;height: 100%;
      opacity: 0;
    }
    66% {
      width: 100%;height: 100%;
      opacity: 0.8;
    }
    100% {
      width: 130%;height: 130%;
      opacity: 0;
    }
  }
  .cssBox .pointRadius {width: 10px;height: 10px;border-radius: 360px;background-color: #FFF;border: 1px solid transparent;
    position: absolute;left: 50%;top: 50%;-webkit-transform: translate(-50%, -50%);transform: translate(-50%, -50%);
    -webkit-animation: pointRadius 2s ease 0s infinite;
    animation: pointRadius 2s ease 0s infinite;
  }
  @-webkit-keyframes pointRadius {
    0% {width: 10px;height: 10px;border: 0;box-shadow: 0 0 0px 3px rgba(255,255,255,0.3), 0 0 0px 6px rgba(255,255,255,0.2), 0 0 0px 9px rgba(255,255,255,0.1);}
    5% {width: 10px;height: 10px;border: 0;box-shadow: 0 0 0px 3px rgba(255,255,255,0.3), 0 0 0px 6px rgba(255,255,255,0.2), 0 0 0px 9px rgba(255,255,255,0.1);}
    10% {width: 12px;height: 12px;border: 0;box-shadow: 0 0 0px 2px rgba(255,255,255,0.3), 0 0 0px 4px rgba(255,255,255,0.2), 0 0 0px 6px rgba(255,255,255,0.1);}
    15% {width: 16px;height: 16px;border: 0;box-shadow: 0 0 0px 1px rgba(255,255,255,0.3), 0 0 0px 2px rgba(255,255,255,0.2), 0 0 0px 3px rgba(255,255,255,0.1);}
    20% {width: 26px;height: 26px;border: 0;box-shadow: none;}
    65% {width: 205px;height: 205px;border: 2px solid #CCC;}
    100% {width: 205px;height: 205px;border: 2px solid #CCC;}
  }
  .gift-title {
    font-weight: bolder;
    font-size: 40px;
    margin-top: 30px;
    text-align: center;

  }
</style>
