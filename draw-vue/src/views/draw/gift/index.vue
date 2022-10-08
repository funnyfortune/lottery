<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
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
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd()"
          v-hasPermi="['draw:gift:save']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['draw:gift:update']"
        >修改</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="giftList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动主题" align="center" prop="themeName" />
      <el-table-column label="奖项名称" align="center" prop="name" />
      <el-table-column label="数量" align="center" prop="num" />
      <el-table-column label="状态" align="center" prop="completed" :formatter="statusFormat"/>
      <el-table-column label="结束时间" align="center" prop="completeTime" >
        <template slot-scope="scope">
          <span>{{ scope.row.completed == 0? '' :parseTime(scope.row.completeTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-video-play"
            v-if="scope.row.completed == 0"
            @click="handleDrawStart(scope.row)"
            v-hasPermi="['draw:start']"
          >开始抽奖</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-medal"
            v-if="scope.row.completed==1"
            @click="handleResult(scope.row)"
            v-hasPermi="['draw:result:list']"
          >中奖名单</el-button>
          <el-button
            type="text"
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd(scope.row)"
            v-hasPermi="['draw:gift:save']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['draw:gift:update']"
          >修改</el-button>
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

    <!-- 添加或修改抽奖礼品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body  :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动主题" prop="themeId">
          <el-select v-model="form.themeId" clearable size="small" style="width: 100%">
            <el-option
              v-for="dict in themeList"
              :key="dict.id"
              :label="dict.name"
              :value="dict.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="奖项名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="奖项数量" prop="num">
          <el-input v-model="form.num" placeholder="请输入数量" type="number" min="1" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listGift, getGift, delGift, addGift, updateGift, exportGift } from "@/api/draw/gift"
import { selectTheme } from "@/api/draw/theme"
import {getInfo} from '@/api/draw/lottery'

export default {
  name: "Gift",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      statusOptions: [],
      // 总条数
      total: 0,
      // 抽奖礼品表格数据
      giftList: [],
      themeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: undefined,
        name: undefined,
        themeId: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        num: [
          { required: true, message: "数量不能为空", trigger: "blur" }
        ],
        themeId: [
          { required: true, message: "活动主题不能为空", trigger: "blur" }
        ]
      }
    }
  },
  mounted() {
    this.getDicts("draw_complete").then(response => {
      this.statusOptions = response.data
    })

      if(this.$route.query.themeId){
        this.queryParams.themeId = parseInt(this.$route.query.themeId)
      }
      this.getList()
  },
  methods: {
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.completed)
    },
    /** 查询抽奖礼品列表 */
    getList() {
      this.loading = true
      listGift(this.queryParams).then(response => {
        this.giftList = response.data.list
        this.total = response.data.totalCount
        this.loading = false
      })
    },
    handleDrawStart(row) {
      let p = {themeId:row.themeId,giftId: row.id}
      getInfo(p).then((res)=>{
        localStorage.setItem("giftCustomer",JSON.stringify(res.data))
        this.$router.push({path: '/startDraw',query:p})
      })

    },
    handleResult(row) {
      this.$router.push({path: '/draw/result',query:{themeId:row.themeId,giftId: row.id}})
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        name: undefined,
        num: undefined,
        themeId:undefined
      }
      this.resetForm("form")
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
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      selectTheme().then((res)=>{
        this.themeList = res.data
      })
      if(row){
        this.reset()
        this.form.themeId = row.themeId
      } else {
        if(this.$route.query.themeId){
          this.form.themeId = parseInt(this.$route.query.themeId)
        }
      }
      this.open = true
      this.title = "添加抽奖礼品"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      selectTheme().then((res)=>{
          this.themeList = res.data
      })
      const id = row.id || this.ids
      getGift(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改抽奖礼品"
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateGift(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功")
                this.open = false
                this.getList()
              }
            })
          } else {
            addGift(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功")
                this.open = false
                this.getList()
              }
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
        let msg = undefined
        let ids = undefined
        if (row.id) {
         ids = [row.id]
            msg = '是否确认删除抽奖礼品ID为【' +row.id + '】的数据项?'
        } else {
         ids = this.ids
         msg = '是否确认删除所选抽奖礼品数据项?'
        }
        this.$confirm(msg, '警告', {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          return delGift(ids)
        }).then(() => {
          this.getList()
          this.msgSuccess("删除成功")
        }).catch(e => {
         // this.msgError(e)
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      let queryParams = this.queryParams
      this.$confirm('是否确认导出所有抽奖礼品数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          return exportGift(queryParams)
        }).then(response => {
          this.download(response.data)
        }).catch(e => {
          // this.msgError(e)
        })
    }
  }
}
</script>
