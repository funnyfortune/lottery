<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="88px">
      <el-form-item label="活动主题" prop="name">
        <el-input
          v-model="queryParams.themeName"
          placeholder="请输入活动主题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户名称" prop="name">
        <el-input
          v-model="queryParams.customerCompany"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户公司" prop="name">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入客户公司"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户手机号" prop="name">
        <el-input
          v-model="queryParams.mobile"
          placeholder="请输入客户手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['draw:result:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="resultList">
      <el-table-column label="活动主题" align="center" prop="themeName" />
      <el-table-column label="奖项名称" align="center" prop="giftName" />
      <el-table-column label="客户公司" align="center" prop="customerCompany" />
      <el-table-column label="客户名称" align="center" prop="customerName" />
      <el-table-column label="客户手机号" align="center" prop="mobile" />
      <el-table-column label="中奖时间" align="center" prop="createTime" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listResult } from "@/api/draw/result"

export default {
  name: "Result",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 抽奖中奖表格数据
      resultList: [],
      // 弹出层标题
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        themeId: undefined,
        giftId: undefined,
        themeName: undefined,
        customerCompany:undefined,
        customerName: undefined,
        mobile: undefined
      }
    }
  },
  created() {
    if(this.$route.query.themeId){
      this.queryParams.themeId = this.$route.query.themeId
    }
    if(this.$route.query.giftId){
      this.queryParams.giftId = this.$route.query.giftId
    }
    this.getList()
  },
  methods: {
    /** 查询抽奖中奖列表 */
    getList() {
      this.loading = true
      listResult(this.queryParams).then(response => {
        this.resultList = response.data.list
        this.total = response.data.totalCount
        this.loading = false
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    }
  }
}
</script>
