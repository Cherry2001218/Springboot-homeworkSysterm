<template>
  <div class="hello">
    <h2>课程列表</h2>
    <el-table
      :data="tableData"
      border
      style="width:100%">
      <el-table-column
        fixed
        prop="sno"
        label="课程编号"
        width="150">
      </el-table-column>
      <el-table-column    
        prop="course"
        label="课程名"
        width="400">
      </el-table-column>
      <el-table-column
        prop="teachername"
        label="授课老师"
        width="200">
      </el-table-column>
      <el-table-column
        label="授课时间"
        width="300">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left:10px">{{ scope.row.time|moment }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index,scope.row)">删除</el-button>
        </template>
     </el-table-column>
    </el-table>
    <el-pagination
      background
      @size-change="handleSizeChange"
      layout="total,sizes,prev, pager, next"
      :page-size="pageSize"
      :current-page="currentPage"
      :page-count="pageCount"
      :total="total"
      @current-change="handleCurrentChange"
      :page-sizes="[5,10,20,30]">
   </el-pagination>
  </div>
</template>

<script>
export default {
  name: 'NewsList',
  data () {
    return {
      tableData:[],
      currentPage:1,
      pageSize:10,
      pageCount:20,
      total:300,
    }
  },
  created(){
    this.getList();
  },
  methods:{
    getList(){
      this.axios.get("class/getPage?current="+this.currentPage+"&size="+this.pageSize).then(response=>{
        this.tableData=response.data.records;
        this.total=response.data.total;
      });
    },
    handleEdit(index,row){
      console.log(index,row);
      console.log(index,row.sno);
     
    },
    handleDelete(index,row){
     
      console.log(index,row);
      console.log(index,row.sno);
       this.axios.post("class/remove",
          {
              sno:row.sno,
          }).then((response)=>{
            
              Toast.success("删除课程成功成功！");
          })
    },
    handleCurrentChange(val){
      this.currentPage=val;
      this.getList();
    },
    handleSizeChange(val){
      this.pageSize=val;
      this.getList();
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
