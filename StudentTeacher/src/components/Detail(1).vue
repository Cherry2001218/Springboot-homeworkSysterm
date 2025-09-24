<template>
 <div class="hello">
      <div class="nav">
        <van-icon name="arrow-left" @click="onClickLeft"></van-icon>
      </div>

    <div class="news">
      <div class="n-title">
        {{top.course}}
      </div>
      <div class ="top" >
          <div class="top-left"  @click="chat">
              <div class="check" v-if="classs.teachername != null">
            <img :src="picIp+classs.image" alt=""  lazy-out height="20">
              </div>

           <div class="check" v-if="classs.teachername == null">
          <van-icon name="user-circle-o" size="40"/>
              </div>

            <span>
               教师姓名  {{top.teachername}}
            </span>
          </div>
          <div class="top-right">
            <van-button color="skyblue" @click="follow" plain>{{followLabel}}</van-button>
          </div>
      </div>
    </div>
   

   
<div class="list">
    
        <div class="card" v-for="item in list" :key="item.sno"  @click="getQuestion(item.cid)" >
            <div class="card-left" >
              <div class="title">
                {{item.wname}}
              </div>
              <div class="time">
                <span>{{item.wdate|moment}}</span>
                <span>{{item.cid}}</span>
                
              </div>
            </div>
   
          </div>
        
     

     
     </div>
  


    

  <div class="comment" v-for="ite in commentList" :key="ite.ccid">
      
          
          <div class="comment-right">
              <div class="comment-user">
                  <div class="user-left">
                    {{ite.userName}}
                  </div>
                  <div class="user-right">
                 
                  <van-icon name="good-job-o" size="13"></van-icon>
          

                  </div>
              </div>
              <div class="comment-content">
                {{ite.ctxt}}
              </div>
              <div class="comment-time">
                {{ite.ctime|moment}}
              </div>
          </div>

      </div>

    <div class="comment-input">
    <van-field
    v-model ="ctxt"
    clearable
    placeholder="请输入评论内容"
    @keyup.enter="sendComment"
    >
    <template #button>
<van-button size="small" type="warning" @click="sendComment"  color = "skyBlue">
    发送
</van-button>
    </template>
    </van-field>
  </div>




    </div>



  

</template>


<script>
import Vue from 'vue';
import {Toast} from 'vant';
import{ImagePreview} from 'vant';


Vue.use(ImagePreview);
Vue.use(Toast);
export default {
  name: 'Mine',
  data () {
return {
//配置详细页面所需要的路径问题
      sno:this.$route.params.value,
      picIp:'http://localhost:8080/pic/',
      classs:{},
      list:[],
       loading :false,
      finished:false,
      refreshing :false,
      ctxt:"",
      commentList:[],
      user:JSON.parse(localStorage.getItem("userInfo")),
      active:1,
      top:{},
       followLabel:"关注"
    }
  },
  created(){
     this.axios.get("class/show?sno="+this.sno).then((response) =>{
      this.top = response.data;
       this.isFollow();
});
//查询这个课程相关信息
    this.axios.get("class/getByIdB?sno="+this.sno).then((response) =>{
      this.classs = response.data;
});
//查询这个课程的作业相关信息
     this.axios.get("class/getById?sno="+this.sno).then((response) =>{
            this.list = response.data;
            this.loading = false;
          })
      this.getCommentList();

   //   console.log(top)

  },
  
  methods:  {
      getQuestion(cid){
        this.$router.push('/Question/'+cid);
      },

//返回上一级的功能
    onClickLeft(){
      this.$router.go(-1);//返回上一个路由
    },
//判断是否刷新
      onLoad(){
          if(this.refreshing){
            this.list = [];
            this.refreshing = false;
          }
         
      },
      onRefresh(){
        //下拉刷新
        this.finished = false;
        //将 loading 设置成true ，表示处于加载状态
        this.loading = true;
        this.page = 1;
        this.onLoad();
      },


   getCommentList()
  {
    this.axios.get("comment/getBySno?sno="+this.sno).then((response)=>
    {
      this.commentList = response.data;
    })

  },



 isFollow()
  {
    if(this.user == null)
    {
      return ;
    }
     var userId = this.user.uid;
    var followUserId = this.top.tno;
    this.axios.get("followUser/isFollowUser?userId="+userId+"&followUserId="+followUserId).then((response)=>{

      var result = response.data;
      if(result == true)
      {
        this.followLabel = '已关注'
      }
    })
  },

  follow()
  {
    if(this.user == null)
    {

      Toast("您没有登录，请先登陆后再关注");
    }

    var userId = this.user.uid;
    var followUserId = this.top.tno;
    console.log(followUserId);
   console.log(userId);
   if(this.followLabel == '已关注')
    {

      this.axios.get("followUser/disFollowUser?userId="+userId+"&followUserId="+followUserId).then((response)=>{

        var result = response.data;
        this.followLabel = '关注';
      })
    }else
    {
      this.axios.get("followUser/followUser?userId="+userId+"&followUserId="+followUserId).then((response)=>{

        var result = response.data;
        this.followLabel = '已关注';
      })
    }

  },


   chat()
      {
          Toast("跳转进聊天界面")
          console.log(this.top.tno)
          this.$router.push({
              name:'Chat',query:{
                authorId:this.top.tno,
                userName:this.classs.teachername,
                image:this.classs.image,
              }
              
            })
      },



     sendComment()
    {
        if(this.user == null)
        {
          Toast("您没有登录，请先登录");
          this.$router.push("/Mine");
          return ;
        }
        if(this.ctxt.trim()=="" || this.ctxt.trim() == null)
        {
          Toast("您未输入评论，请输入内容后再发表");
          return;
        }
        this.axios.post("comment/add",{
            sno:this.sno,
            ctxt:this.ctxt,
            uid:this.user.uid,

        }).then(response=>{
          this.ctxt="";
          this.getCommentList();
        })

    },
 

  },

  



}
</script>
<style scoped>
.hello{
  margin-bottom: 50px;
}
.news{
  padding: 5px;
  text-align: left;
  line-height: 2;
}
.n-title{
  font-size:29px;
}
.n-content{

  text-indent: 30px;
}
.n-img img,video{

  height: 300px;
  width:100%;
}

.n-user{
  display:flex;
  justify-content: space-between;
}
.n-img1 img{
  height: 150px;
  width:100%；
}
.comment-input
{
  border-top:0.5px solid #ddd;

}
h3,.comment-input
{
  text-indent: 0px;

}
.comment,.comment-user
{
  display: flex;
}
.comment-right
{
  margin-left: 10px;
  width:100%;
}
.comment-user
{
justify-content: space-between;
font-size: 14px;
}
.user-left
{
  font-weight: 800;
}
.comment-time
{
font-size: 14px;
color:#7777;

}
.comment{
  height: 100px;
}
</style>
