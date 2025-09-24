<template>

  <div class="hello">
   
    <div class="nav">
        <van-icon name="arrow-left" @click="logout"></van-icon>
      </div>

      <div v-if="user.type == 0">
      <span>同学你被封禁了</span>
      </div >
       <div v-if="user.type == 1">
   <div class="head">
    <div class = "top">
    <div class="top-left"  @click="chat">
           
            <img :src="picIp+user.image" alt=""  lazy-out height="20">
              </div>
      <div class = "top-right">
          <van-icon name="scan" size ="20"/>
      </div>
      <div class = "top-right">
          <van-icon name="envelop-o" size ="20"></van-icon>
          <van-icon name="setting-o" size="20"></van-icon>
      </div>
    </div>

     <div class="top">
       <div class="top-left" >
        
          <span>姓名:{{user.userName}}</br></br></span>
          <span>学号:{{user.uid}}</span>
      </div>
      <div class="top-right">

      </div>
     </div>

    </div>
    <div class="grid">
      <van-row>
          <van-col span ="6" >
            <div class="digiit">0</div>
            <div class="text">课程</div>
          </van-col>
          <van-col span ="6">
            <div class="digiit">0</div>
            <div class="text">课本</div>
          </van-col>
                  <van-col span ="6" to="/Follow">
            <div class="digiit">0</div>
            <div class="text">粉丝</div>
          </van-col>
                  <van-col span ="6">
            <div class="digiit" @click="goFollow">0</div>
            <div class="text">关注</div>
          </van-col>
      </van-row>
    </div>
    <!-- 中间显示-->
    <div class="grid">
      <van-row>
       <van-col span="6">
         <div class="digit">
           <van-icon name="comment" color="skyblue"  />
         </div>
         <div class="text" @click="goFllow">
           消息
         </div>
       </van-col>
       <van-col span="6">
         <div class="digit">
           <van-icon name="star"  color="skyblue"  />
         </div>
         <div class="text">
           收藏
         </div>
       </van-col>
        <van-col span="6">
         <div class="digit">
           <van-icon name="clock" color="skyblue"  />
         </div>
         <div class="text">
          浏览
         </div>
       </van-col>     
       <van-col span="6">
         <div class="digit">
           <van-icon name="down" color="skyblue"  />
         </div>
         <div class="text">
          下载
         </div>
       </van-col>
       
      </van-row>

      <van-cell-group>
        <van-cell title="我的关注"  label="我关注的院校" >
        <van-icon name="manager-o" size="30" />
        </van-cell>
        <van-cell title="我的讨论"  label="参与讨论都在这里" >
        <van-icon name="gem-o"  size="30"/>
        </van-cell>
        <van-cell title="绑定手机"  label="换绑" > 
        <van-icon name="phone-o" size="30"/>
        </van-cell>
        <van-cell title="个人信息"  label="姓名/学校等信息" >
        <van-icon name="contact" size="30"/>
        </van-cell>
        <van-cell title="帮助与反馈"  label="有疑问查询帮助" >
        <van-icon name="flag-o" size="30"/>
        </van-cell>
        <van-cell title="联系我们"  label="商务合作，服务等资讯" >
        <van-icon name="gift-card-o" size="30"/>
        </van-cell>
      </van-cell-group>
    </div>
    </div>
     <div v-if="user.type == 2">
         <div class="head">
    <div class = "top">
      <div class = "top-left">
          <van-icon name="scan" size ="20"/>
      </div>
      <div class = "top-right">
          <van-icon name="envelop-o" size ="20"></van-icon>
          <van-icon name="setting-o" size="20"></van-icon>
      </div>
    </div>

     <div class="top">
         <div class="top-left"  @click="chat">
           
            <img :src="picIp+user.image" alt=""  lazy-out height="20">
              </div>
       <div class="top-right" >
        
          <span>姓名:{{user.userName}}</br></br></span>
          <span>工号:{{user.uid}}</span>
      </div>
      <div class="top-right">

      </div>
     </div>

    </div>
   
    <!-- 中间显示-->
    <div class="grid">
      <van-row>
       <van-col span="6">
         <div class="digit">
           <van-icon name="comment" color="skyblue"  />
         </div>
         <div class="text" @click="goFollow">
           消息
         </div>
       </van-col>
       <van-col span="6">
         <div class="digit">
           <van-icon name="star"  color="skyblue"  />
         </div>
         <div class="text"  @click="onComment">
           评论
         </div>
       </van-col>
        <van-col span="6">
         <div class="digit">
           <van-icon name="clock" color="skyblue"  />
         </div>
         <div class="text">
          浏览
         </div>
       </van-col>     
       <van-col span="6">
         <div class="digit">
           <van-icon name="down" color="skyblue"  />
         </div>
         <div class="text">
          下载
         </div>
       </van-col>
       
      </van-row>

      <van-cell-group>
        <van-cell title="课程管理"  label="增加课程" to="/Course" >
        <van-icon name="manager-o" size="30" />
        </van-cell>
        <van-cell title="课程管理"  label="删除课程" to="/DeCourse">
        <van-icon name="gem-o"  size="30"/>
        </van-cell>
        <van-cell title="课程管理"  label="查看课程" @click="goCourseServe" > 
        <van-icon name="phone-o" size="30"/>
        </van-cell>
        <van-cell title="个人信息"  label="姓名/学校等信息" >
        <van-icon name="contact" size="30"/>
        </van-cell>
        <van-cell title="帮助与反馈"  label="有疑问查询帮助" >
        <van-icon name="flag-o" size="30"/>
        </van-cell>
        <van-cell title="联系我们"  label="商务合作，服务等资讯" >
        <van-icon name="gift-card-o" size="30"/>
        </van-cell>
      </van-cell-group>
    </div>

     </div>

    <van-overlay :show="show" @click="show=false">
      <div class="wrapper" @click.stop>
        <div class="block">
          <h1>登录</h1>
          <van-form @submit="onSubmit">
            <van-field v-model="user.uid"
                            name="uid"
                            label="用户名"
                            clearable
                            placeholder="用户名"
                            :rules="[{required :true,messgae:'请填写用户名'}]"
                            />
             <van-field v-model="user.password"
                            type="password"
                            name="password"
                            clearable
                            label="密码"
                            placeholder="密码"
                            :rules="[{required :true,messgae:'请填写密码'}]"
                            />
              <div style="margin:16px">
                <van-button round block type="info" native-type="submit" color="skyblue">登录</van-button>
              </div>
                                                       
          </van-form>
          <div class="reg" style="margin:16px" @click="goRegist" >
             <van-button round block type="info" native-type="submit" color="skyblue">注册</van-button>
          </div>
        </div>
      </div>

    </van-overlay>
  </div>
</template>

<script>
import Vue from 'vue';

import { Icon } from 'vant';

import { Toast } from 'vant';

Vue.use(Toast);

import { Cell, CellGroup } from 'vant';

Vue.use(Cell);
Vue.use(CellGroup);


export default {
  name: 'Mine',
  data () {
    return {
      show:true,
      value:'',
      picIp:'http://localhost:8080/pic/',
      user:{
        uid:'',
        username:'',
        password:'',
        image:'./logo.png',
      }
     
    }
  },
  created(){
   var user= JSON.parse(localStorage.getItem("userInfo"));
   if(user!=null)
   {
     this.user=user;
     this.show=false;
   }
  },
  
  methods: {

       goFollow(){
        this.$router.push("/Follow");
      },
      
      onComment()
      {
  this.$router.push("/Comment");
      },
      toCourse()
      {
        this.$router.push("/Course");
      },
      onSubmit(values){
        console.log(values);
        this.axios.post("user/login",this.user).then(response=>{
          console.log(response.data);
          if(response.data==''||response.data==null)
          {
            Toast("登陆失败，请重新输入");
            return ;
          }
          Toast("登陆成功");

          this.user=response.data;
          this.show=false;

        
          localStorage.setItem("userInfo",JSON.stringify(this.user));

        });
      },
      goRegist(){
        this.$router.push("/Regist");
      },
      logout(){
          localStorage.removeItem("userInfo");
          this.user={
            uid:0,
            username:'',
            password:'',
            image:'',
          }
        
          this.show=true;
      },
      goClass(){
        this.$router.push("/Class");
      },
       goCourseServe(){
        this.$router.push("/CourseServe");
      },
      goAbb()
      {
        this.$router.push("/Abb");
      }
      
  },

 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.top-left span{

  margin-top:30px;
}
</style>
