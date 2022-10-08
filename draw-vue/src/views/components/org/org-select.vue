<template>
  <el-dialog
    :close-on-click-modal="false"
    :visible.sync="visible" width="70%" style="z-index: 10002" :modal-append-to-body='false' append-to-body>
    <el-row>
      <el-col :span="8">
      <el-aside width="260px" style="overflow: hidden;">
        <!--调用饿了么Tree树形组件，定义node-key属性，动态绑定data数组，定义配置选项props，绑定点击事件，引入tree方法-->
        <el-tree node-key="id" :data="orgTrees" :default-expand-all="true"  :highlight-current="false"
                 :props="defaultProps" @node-click="_handleNodeClick"
                 style="height:400px;overflow-y:scroll;"
                 ref="tree"></el-tree>
      </el-aside>
      </el-col>
      <el-col :span="15">
        <el-table
          ref="personTable"
          :data="tableData"
          highlight-current-row
          @current-change="_handleSelectionChange"
          @select="_handleSelectOne"
          @select-all="_handleSelectAll"
          style="width: 100%" max-height="400" >
          <el-table-column
            type="selection"
            width="55" v-if="multiple">
          </el-table-column>
          <el-table-column
            prop="id"
            :label="multiple? '全选' : ''"
            width="200px">
            <template slot-scope="scope">
              <div style="width: 335px;">
                <div style="padding:10px;">
                  <span class="header">{{scope.row.name.substring(scope.row.name.length-2, scope.row.name.length)}}</span>
                  <div style="position: absolute;top: 10px;left: 60px;">
                  <span>{{scope.row.name}}  <time class="time">{{scope.row.duty}}</time></span>
                </div>
                  <div style="position: absolute;top: 30px;left: 60px;">
                    <time class="time">{{scope.row.deptName}}</time></div>
                </div>
              </div>
            </template>
          </el-table-column>
          <el-table-column
            align="right">
            <template slot="header" slot-scope="scope" width="200px">
              <el-input
                v-model="name"
                size="mini"
                placeholder="请输入名字搜索" @change="_changeName"/>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row :gutter="14" style="margin-top: 5px">
      <span style="margin-left: 15px;line-height: 3" v-for="(tag,i) in selectList"
              :key="tag.name">
        <el-tag
          closable @close="_removeSelect(i)">
          {{tag.name}}
        </el-tag>
      </span>
    </el-row>

    <span slot="footer" class="dialog-footer">
      <el-button  @click="visible = false">取消</el-button>
      <el-button type="primary" @click="_submit">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {selectList as deptList} from '@/api/system/dept'
  import {selectList as userList} from '@/api/system/user'
  export default {
    props: {
      multiple: {
        type: Boolean,
        default: false
      }
    },
    data () {
      return {
        visible: this.show,
        selectList: [],
        tableData: [],
        pageIndex: 1,
        pageSize: 5,
        totalPage: 0,
        id: 0,
        name: '',
        orgTrees: [],
        defaultProps: {
          // 指定子节点的属性children值
          children: 'children',
          // 指定节点标签为节点对象的label值
          label: 'name'
        }
      }
    },
    created () {
      // id，调用getTree方法
      this._getTree(0)
    },
    // vue创建时调用自定义init方法
    methods: {
      selectSelection (list) {
        this.visible = true
        if (list) {
          this.selectList = list
        }
      },
      _submit () {
        if (this.selectList.length === 0) {
          this.$message.error('请选择人员')
          return
        }
        this.visible = false
        console.log(this.selectList)
        this.$emit('change', this.selectList)
      },
      _handleNodeClick (e) {
        console.log(e)
        this._selectTable(e.id)
      },
      _getTree () {
        // 调用已封装的$http访问后台
        deptList().then(data => {
          this.orgTrees = data.data
        })
      },
      _selectTable (id) {
        // 将data中的属性传入tree组件中的getnode方法，得到当前node对象
        let node = this.$refs.tree.getNode(id)
        // 将name置空
        this.name = ''
        // 如果node的等级为1
        if (node.level === 1) {
          // 将OAID置空
          this.id = 0
        } else {
          // 将传入的OAID赋值给data对象中的OAID
          this.id = id
        }
        // 最后调用personInfo方法
        this._getPersonInfo()
      },
      _getPersonInfo () {
        if (this.id === 0 && this.name === '') {
          this.tableData = []
          return
        }
        // 调用已封装的$http访问后台
        userList({
            // 传入部门id
            'deptId': this.id,
            // 传入人员名称
            'userName': this.name
          }).then(data => {
          console.log(data)
          // 如果data为空或者响应码为0
          if (data && data.code === 200) {
            // 将data对象中的data下的list数组赋值给tableData
            this.tableData = data.data
            setTimeout(() => {
              this._selectRowSelection()
            }, 200)
          } else {
            // 将列表数组和页码设为空
            this.tableData = []
          }
        })
      },
      // 单选
      _handleSelectionChange (val) {
        if (this.multiple) {
          return
        }
        console.log(val)
        if (val === null) {
          return
        }
        this.selectList = [val]
      },
      // 全选 取并集，全取消，取差集
      _handleSelectAll (val) {
        console.log(val)
        if (val.length === 0) {
          this.selectList = this._difference(this.selectList, this.tableData)
        } else {
          this.selectList = this._union(this.selectList, val)
        }
      },
      // 选择取并集，取消，取差值
      _handleSelectOne (val, row) {
        console.log(val)
        console.log(row)
        val = this._unite(val, [row])// 取交集
        if (val.length === 0) {
          this.selectList = this._difference(this.selectList, [row])
        } else {
          this.selectList = this._union(this.selectList, val)
        }
      },
      // 渲染选选中
      _selectRowSelection () {
        if (this.multiple) {
          this.$refs.personTable.clearSelection()
          let val = this._unite(this.tableData, this.selectList)
          console.log(val)
          val.forEach(row => {
            this.$refs.personTable.toggleRowSelection(row, true)
          })
        } else {
          let val = this._unite(this.tableData, this.selectList)
          console.log(val)
          val.forEach(row => {
            this.$refs.personTable.setCurrentRow(row, true)
          })
        }
      },
      _removeSelect (index) {
        this.selectList.splice(index, 1)
        this._selectRowSelection()
      },
      _changeName (value) {
        this.name = value
        this.id = 0
        this._getPersonInfo()
      },
      // 交集
      _unite (arr1, arr2) {
        if (arr2.length === 0) {
          return []
        }
        return arr1.filter(x => arr2.some(y => y.id === x.id)) // 取交集
      },
      // 并集
      _union (arr1, arr2) {
        if (arr2.length === 0) {
          return arr1
        }
        let set1 = new Set(arr1)
        let set2 = new Set(arr2)
        return Array.from(new Set([...set1, ...set2]))
      },
      // 差集
      _difference (arr1, arr2) {
        if (arr2.length === 0) {
          return arr1
        }
        return arr1.filter(x => arr2.every(y => y.id !== x.id))
      }
    }
  }
</script>

<style scoped>
  .header{
    background-color: #0081FF;
    display: inline-block;
    vertical-align: middle;
    border-radius: 50%;
    box-shadow: 0 0 1px rgba(0,0,0,0.3);
    color: #fff;
    font-size: 1px;
    text-align: center;
    height: 38px;
    width: 38px;
    line-height: 38px;
    font-family: Arial,'微软雅黑','宋体';
  }
  .time {
    font-size: 11px;
    color: #999;
  }
  ::v-deep .el-dialog__header {
    padding: 0px 20px 10px;
  }
  ::v-deep .el-table--medium td {
    padding: 0px 0;
    border: 0;
  }
  ::v-deep .el-table--medium th {
    padding: 0px 0;
    border: 0;
  }

  ::v-deep .el-table td{
    border-bottom: 0px;
    border: 0;
  }
  aside{
    background: white;
  }
  table{
    border: 0;
  }
</style>
