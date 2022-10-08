<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="主键ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入主键ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主题Id" prop="themeId">
        <el-input
          v-model="queryParams.themeId"
          placeholder="请输入主题Id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主题奖项Id" prop="giftId">
        <el-input
          v-model="queryParams.giftId"
          placeholder="请输入主题奖项Id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者id" prop="creatorId">
        <el-input
          v-model="queryParams.creatorId"
          placeholder="请输入创建者id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-input
          v-model="queryParams.createTime"
          placeholder="请输入创建时间"
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
          v-hasPermi="['draw:action:save']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['draw:action:update']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['draw:action:delete']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['draw:action:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="actionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="主题Id" align="center" prop="themeId" />
      <el-table-column label="主题奖项Id" align="center" prop="giftId" />
      <el-table-column label="创建者id" align="center" prop="creatorId" />
      <el-table-column label="创建时间" align="center" prop="createTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['draw:action:update']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['draw:action:delete']"
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

    <!-- 添加或修改抽奖行为对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body  :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="主题Id" prop="themeId">
          <el-input v-model="form.themeId" placeholder="请输入主题Id" />
        </el-form-item>
        <el-form-item label="主题奖项Id" prop="giftId">
          <el-input v-model="form.giftId" placeholder="请输入主题奖项Id" />
        </el-form-item>
        <el-form-item label="创建者id" prop="creatorId">
          <el-input v-model="form.creatorId" placeholder="请输入创建者id" />
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
import { listAction, getAction, delAction, addAction, updateAction, exportAction } from "@/api/draw/action"

export default {
  name: "Action",
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
      // 抽奖行为表格数据
      actionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: undefined,
        themeId: undefined,
        giftId: undefined,
        creatorId: undefined,
        createTime: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "主键ID不能为空", trigger: "blur" }
        ],
        themeId: [
          { required: true, message: "主题Id不能为空", trigger: "blur" }
        ],
        giftId: [
          { required: true, message: "主题奖项Id不能为空", trigger: "blur" }
        ],
        creatorId: [
          { required: true, message: "创建者id不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询抽奖行为列表 */
    getList() {
      this.loading = true
      listAction(this.queryParams).then(response => {
        this.actionList = response.data.list
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
        themeId: undefined,
        giftId: undefined,
        creatorId: undefined,
        createTime: undefined
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
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加抽奖行为"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getAction(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改抽奖行为"
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateAction(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功")
                this.open = false
                this.getList()
              }
            })
          } else {
            addAction(this.form).then(response => {
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
            msg = '是否确认删除抽奖行为ID为【' +row.id + '】的数据项?'
        } else {
         ids = this.ids
         msg = '是否确认删除所选抽奖行为数据项?'
        }
        this.$confirm(msg, '警告', {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          return delAction(ids)
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
      this.$confirm('是否确认导出所有抽奖行为数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          return exportAction(queryParams)
        }).then(response => {
          this.download(response.data)
        }).catch(e => {
          // this.msgError(e)
        })
    }
  }
}
</script>
