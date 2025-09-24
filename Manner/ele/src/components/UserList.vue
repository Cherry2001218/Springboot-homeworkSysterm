<template>
  <div class="hello">
    <h2>用户列表</h2>
    <div class="btn">
      <download-excel
            class = "export-excel-wrapper"
            :data = "tableData"
            :fields = "json_fields"
            name = "userlist.xlsx">
            <!-- 上面可以自定义自己的样式，还可以引用其他组件button -->
            <el-button type="primary" size="large">导出EXCEL</el-button>
      </download-excel> 
      <el-button type="primary" size="large" @click="exportWord">导出WORD</el-button>     
    </div>
    <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        fixed
        prop="uid"
        label="用户编号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="userName"
        label="用户名"
        width="150">
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱"
        width="200">
      </el-table-column>
      <el-table-column
        label="头像"
        width="150">
        <template slot-scope="scope">          
          <el-avatar size="large" :src="picIp+scope.row.image"></el-avatar>
        </template>
      </el-table-column>     
   
      <el-table-column
        label="用户类型"
        width="150">
        <template slot-scope="scope">
          <span style="margin-left: 10px" v-if="scope.row.type==2">老师</span>
          <span style="margin-left: 10px" v-else>学生</span>
        </template>
      </el-table-column>     
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">查看</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete1(scope.$index, scope.row)" v-if="scope.row.type==0">解禁</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)" v-if="scope.row.type==1">封禁</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      @size-change="handleSizeChange"
      layout="total, sizes, prev, pager, next"
      :page-size="pageSize"
      :current-page="currentPage"
      :page-count="pageCount"
      :total="total"
      @current-change="handleCurrentChange"
      :page-sizes="[5, 10, 20, 30]">
    </el-pagination>
  </div>
</template>

<script>
import docxtemplater from 'docxtemplater'
import PizZip from 'pizzip'
import JSZipUtils from 'jszip-utils'
import {saveAs} from 'file-saver'
import ImageModule from 'docxtemplater-image-module-free'

export default {
  name: 'NewsList',
  data () {
    return {
      tableData: [],
      currentPage:1,//当前的页码
      pageSize:10,//每页显示的记录数
      pageCount:20,//总页数
      total:300,//总的记录数
      picIp:"http://localhost:8080/pic/",
      json_fields:{
        "用户编号": "uid",
        "用户名": "userName",
        "注册时间": "regTime",
        "邮箱": "email",
        "用户类型":{
          field:"type",
          callback:type=>{
            if(type==0){
              return "封禁用户"
            }else if(type==2){
              return "老师"
            }else{
              return "学生"
            }
          }
        }
      },
      list:{
        "userData":[]
      }
    }
  },
  created(){
    this.getList();
  },
  methods: {
    exportWord() {
        this.tableData.forEach(item=>{
          if(item.type==0){
            item.type="封禁用户"
          }else if(item.type==2){
            item.type="老师"
          }else{
            item.type="学生"
          }
          item.userImage=this.picIp+item.image;
        })
        this.list.userData=this.tableData;
        let that = this;
        
        // 读取并获得模板文件的二进制内容
        JSZipUtils.getBinaryContent("../../static/userlist.docx", function(error, content) {
          // model.docx是模板。我们在导出的时候，会根据此模板来导出对应的数据
          // 抛出异常
          if (error) {
            throw error;
          }
          var opts = {}
          opts.centered = false;
          opts.getImage = function (tagValue, tagName) {
            return new Promise(function (resolve, reject) {
              JSZipUtils.getBinaryContent(tagValue, function (error, content) {
                if (error) {
                  return reject(error);
                }
                return resolve(content);
              });
            });
          }
          opts.getSize = function (img, tagValue, tagName) {
              return [50, 50];//图片大小 
          }
          var imageModule = new ImageModule(opts);
          // 创建一个PizZip实例，内容为模板的内容
          var zip = new PizZip(content);
              var doc = new docxtemplater()
                .loadZip(zip)
                .attachModule(imageModule)
                .compile();

              //console.log(that.tableData)
              doc.resolveData(that.list).then(function () {
                doc.render();
                var out = doc.getZip().generate({
                  type: "blob",
                  mimeType: "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                });
                //输出文档
                saveAs(out, "userlist.docx");
              })
        });
    },

    getList(){
      this.axios.get("user/getPage?current="+this.currentPage+"&size="+this.pageSize).then(response=>{
        this.tableData=response.data.records;
        
        this.total=response.data.total;
      });
    },
    handleEdit(index, row) {
        if(row.type==0){
          row.type="封禁用户"
        }else if(row.type==2){
          row.type="管理员用户"
        }else{
          row.type="普通用户"
        }
        row.userImage=this.picIp+row.image;
      //console.log(index, row);
        let that = this;
        // 读取并获得模板文件的二进制内容
        JSZipUtils.getBinaryContent("../../static/userInfo.docx", function(error, content) {
          // model.docx是模板。我们在导出的时候，会根据此模板来导出对应的数据
          // 抛出异常
          if (error) {
            throw error;
          }
          var opts = {}
          opts.centered = false;
          opts.getImage = function (tagValue, tagName) {
            return new Promise(function (resolve, reject) {
              JSZipUtils.getBinaryContent(tagValue, function (error, content) {
                if (error) {
                  return reject(error);
                }
                return resolve(content);
              });
            });
          }
          opts.getSize = function (img, tagValue, tagName) {
              return [50, 50];//图片大小 
          }
          var imageModule = new ImageModule(opts);
          // 创建一个PizZip实例，内容为模板的内容
          var zip = new PizZip(content);
              var doc = new docxtemplater()
                .loadZip(zip)
                .attachModule(imageModule)
                .compile();
              doc.resolveData(row).then(function () {
                doc.render();
                var out = doc.getZip().generate({
                  type: "blob",
                  mimeType: "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                });
                //输出文档
                saveAs(out, "userInfo.docx");
              })
        });
    }

    ,
    handleDelete(index, row) {
      // console.log(index, row);
      this.axios.get("user/updateUserType?uid="+row.uid).then(response=>{
      });

    },
      handleDelete1(index, row) {
      // console.log(index, row);
      this.axios.get("user/updateUserType1?uid="+row.uid).then(response=>{
      });

    },
    handleCurrentChange(val) {
      //console.log(`当前页: ${val}`);
      this.currentPage=val;
      this.getList();
    },
     handleSizeChange(val) {
      //console.log(`每页 ${val} 条`);
      this.pageSize=val;
      this.getList();
    },
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.btn{
  text-align: left;
  margin: 5px 0;
  display: flex;
}
.btn .el-button{
  margin-left: 5px;
}
</style>
