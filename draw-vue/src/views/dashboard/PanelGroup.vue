<template>
  <div>
  <el-row :gutter="40" class="panel-group">
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
        <div class="card-panel-icon-wrapper icon-people">
          <svg-icon icon-class="peoples" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            在线人数
          </div>
          <count-to :start-val="0" :end-val="dataList.userCount" :duration="2600" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('messages')">
        <div class="card-panel-icon-wrapper icon-message">
          <svg-icon icon-class="message" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            数量
          </div>
          <count-to :start-val="0" :end-val="dataList.issueCount" :duration="3000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('purchases')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            数量
          </div>
          <count-to :start-val="0" :end-val="dataList.totalMoney" :duration="3200" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('shoppings')">
        <div class="card-panel-icon-wrapper icon-shopping">
          <svg-icon icon-class="bug " class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            数量
          </div>
          <count-to :start-val="0" :end-val="dataList.orderCount" :duration="3600" class="card-panel-num" />
        </div>
      </div>
    </el-col>
  </el-row>

  <el-card  style="margin-bottom: 20px;">
    <div slot="header" class="clearfix">
      <span>快捷访问</span>
    </div>
    <el-row :gutter="12">
      <el-col :span="3">
        <div  @click="togo('order/add')"   v-hasPermi="['order:info:add']">
        <el-card shadow="always" style="width: 120px;height: 120px;text-align: center">
          <i class="el-icon-plus" style="font-size: 36px;"></i>
          <p>
            菜单
          </p>
        </el-card>
      </div>
      </el-col>

      <el-col :span="3">
        <div  @click="togo('customer/info')"   v-hasPermi="['customer:info:list']">
        <el-card shadow="always" style="width: 120px;height: 120px;text-align: center" >
          <i class="el-icon-user-solid" style="font-size: 36px;"></i>
          <p>
            菜单
          </p>
        </el-card>
        </div>
      </el-col>

      <el-col :span="3">
        <div   @click="togo('bom/info')"   v-hasPermi="['bom:info:list']">
        <el-card shadow="always" style="width: 120px;height: 120px;text-align: center">
          <i class="el-icon-s-grid" style="font-size: 36px;"></i>
          <p>
            菜单
          </p>
        </el-card>
        </div>
      </el-col>

      <el-col :span="3">
        <div   @click="togo('factory/storage/info')"   v-hasPermi="['storage:info:list']">
        <el-card shadow="always" style="width: 120px;height: 120px;text-align: center" >
          <i class="el-icon-takeaway-box" style="font-size: 36px;"></i>
          <p>
            菜单
          </p>
        </el-card>
        </div>
      </el-col>

      <el-col :span="3">
        <el-card shadow="always" style="width: 120px;height: 120px;text-align: center"   v-hasPermi="['issue:info:list']">
          <div @click="togo('order/issue/info')">
          <i class="el-icon-chat-dot-round" style="font-size: 36px;"></i>
          <p>
            菜单
          </p>
          </div>
        </el-card>
      </el-col>

      <el-col :span="3">
        <div @click="togo('factory/allocation')" v-hasPermi="['storage:allocation:list']">
        <el-card shadow="always" style="width: 120px;height: 120px;text-align: center">
          <i class="el-icon-truck" style="font-size: 36px;" ></i>
          <p>
            菜单
          </p>
        </el-card>
        </div>
      </el-col>

      <el-col :span="3">
        <div  @click="togo('order/solution')"  v-hasPermi="['order:solution:list']">
        <el-card shadow="always" style="width: 120px;height: 120px;text-align: center" >
          <i class="el-icon-truck" style="font-size: 36px;"></i>
          <p>
            菜单
          </p>
        </el-card>
        </div>
      </el-col>
    </el-row>
  </el-card>
  </div>
</template>

<script>
import CountTo from 'vue-count-to'
// import {selectList as dataList} from '@/api/home/home'

export default {
  components: {
    CountTo
  },
  created() {
    // dataList().then(res=>{
    //  this.dataList = res.data
    // })
  },
  methods: {
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },
    togo(path) {
      debugger
      this.$router.push({path: path})
    }
  },
  data () {
    return {
      dataList:{
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.panel-group {
  .card-panel-col {
    margin-bottom: 20px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
