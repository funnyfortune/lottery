<template>
  <div id="app">
    <el-scrollbar style="height:100%">
      <router-view />
    </el-scrollbar>
  </div>
</template>

<script>
  export default  {
    name:  'App'
  }
</script>
<style scoped>
  /deep/ .el-scrollbar__wrap {
    overflow-x: hidden;
  }
</style>
