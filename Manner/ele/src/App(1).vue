<template>
  <div id="app">
    <el-container style="height: 100%; border: 1px solid #eee">
      <el-header>
        <div class="title">作业系统后台</div>
        <div class="name"><span>Emiliya</span></div>        
      </el-header>           
      <el-container> 
        <el-aside width="20%" style="background-color: rgb(238, 241, 246)">       
          <el-menu 
           :router=router
          :default-openeds="['1', '2','3']" 
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-message"></i>课程管理</template>
              <el-menu-item-group>              
                <el-menu-item index="/">课程列表</el-menu-item>
                <el-menu-item index="/AddNews">发布课程</el-menu-item>
               
              </el-menu-item-group>            
            </el-submenu>   
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-message"></i>课程作业管理</template>
              <el-menu-item-group>              
              
                <el-menu-item index="/Chart">课程作业数据列表</el-menu-item>
              </el-menu-item-group>            
            </el-submenu>  
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-message"></i>用户管理</template>
              <el-menu-item-group>              
                <el-menu-item index="/UserList">用户列表</el-menu-item>
              </el-menu-item-group>            
            </el-submenu>               
          </el-menu>
        </el-aside>        
        <el-main>
          <router-view></router-view>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
     return{
       router:true
     };
  },
  methods: {
    
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.el-header {
  background-color:#6a737c;
  color: #fff;
  line-height: 60px;
  display: flex;
  justify-content: space-between;
}
.title{
  font-size: 18px;
}
.name{
  font-size: 14px;
}
.el-aside {
  color: #333;
}
</style>
