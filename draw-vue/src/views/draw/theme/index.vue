<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="活动名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入活动名称"
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
          @click="handleAdd"
          v-hasPermi="['draw:theme:save']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['draw:theme:update']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['draw:theme:delete']"
        >删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="themeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主题ID" align="center" prop="id" />
      <el-table-column label="活动名称" align="center" prop="name" />
      <el-table-column label="抽奖类型" align="center" prop="drawType" :formatter="typeFormat"/>
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
            icon="el-icon-s-ticket"
            @click="handleGift(scope.row)"
            v-hasPermi="['draw:gift:list']"
          >奖项管理</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-user"
            @click="handleCustomer(scope.row.id)"
            v-hasPermi="['draw:customer:list']"
          >客户管理</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-medal"
            @click="handleResult(scope.row)"
            v-hasPermi="['draw:result:list']"
          >中奖名单</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['draw:theme:update']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['draw:theme:delete']"
          >删除</el-button>
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

    <!-- 添加或修改抽奖主题对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body  :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="概率类型">
          <el-radio-group v-model="form.drawType">
            <el-radio
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
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
import { listTheme, getTheme, delTheme, addTheme, updateTheme, exportTheme } from "@/api/draw/theme"

export default {
  name: "Theme",
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
      // 总条数
      total: 0,
      typeOptions: [],
      // 抽奖主题表格数据
      themeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "活动名称不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getDicts("draw_type").then(response => {
      this.typeOptions = response.data
    })
    this.getList()
  },
  methods: {
    // 状态字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.drawType)
    },
    /** 查询抽奖主题列表 */
    getList() {
      this.loading = true
      listTheme(this.queryParams).then(response => {
        this.themeList = response.data.list
        this.total = response.data.totalCount
        this.loading = false
      })
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
        drawType: '0'
      }
      this.resetForm("form")
    },
    handleGift(row) {
      this.$router.push({path: '/draw/gift',query:{themeId:row.id}})
    },
    handleCustomer(id){
      this.$router.push({path: '/draw/customer',query:{themeId:id}})
    },
    handleDrawStart(row) {
      this.$router.push({path: '/startDraw',query:{themeId:row.id}})
    },
    handleResult(row) {
      this.$router.push({path: '/draw/result',query:{themeId:row.id}})
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
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加抽奖主题"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getTheme(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改抽奖主题"
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateTheme(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功")
                this.open = false
                this.getList()
              }
            })
          } else {
            addTheme(this.form).then(response => {
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
            msg = '是否确认删除抽奖主题数据项?'
        } else {
         ids = this.ids
         msg = '是否确认删除所选抽奖主题数据项?'
        }
        this.$confirm(msg, '警告', {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          return delTheme(ids)
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
      this.$confirm('是否确认导出所有抽奖主题数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          return exportTheme(queryParams)
        }).then(response => {
          this.download(response.data)
        }).catch(e => {
          // this.msgError(e)
        })
    }
  }
}
</script>
