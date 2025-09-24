<template>

  <div class="hello">
    <div class="nav">

        <van-icon name="arrow-left" @click="onClickLeft"/>Exit
    </div>


    <div class="top">
        <div class="top-left">
             <img :src="picIp+user.image" alt=""  lazy-out height="20">
            <div class="user">
                {{user.userName}}
            </div>
        </div>
        <div class="top-right">
            <div class="green">
                个人主页
                <van-icon name="arrow"/>
            </div>
        </div>
    </div>

    
     <van-tabs v-model="active">
   


        <van-tab title="评论">
            <div class="list">
                
                    <div class="comment-card" v-for="(item,index) in list" :key="item.ccid">
                        <van-swipe-cell>

                            <div class="comment-title">
                              评论内容：  {{item.ctxt}}
                            </div>
                            <div class="comment-title">
                               评论时间： {{item.ctime|moment}}
                            </div>
                            <div class="news-card" @click="getDetail(item.sno)">
                                <div class="top">
                                    <div class="top-left">
                                        教授课程 {{item.course}}
                                        <div class="user">
                                           教师姓名 {{item.teachername}}
                                        </div>
                                    </div>
                                    <div class="top-right">

                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-left">
                                        <div class="title">
                                            {{item.title}}
                                        </div>
                                        <div class="time">
                                            
                                        </div>
                                    </div>
                                    <div class="card-right">
                                        <img :src="picIp+item.pic" alt="">
                                    </div>
                                </div>
                                <div class="share">
                                    <van-row>
                                        <van-col span="8">
                                            <van-icon name="share-o"></van-icon>
                                            Share
                                        </van-col>
                                         <van-col span="8">
                                            <van-icon name="chat-o"></van-icon>
                                            Chat
                                        </van-col>
                                         <van-col span="8">
                                            <van-icon name="good-job-o"></van-icon>
                                            Good
                                        </van-col>
                                    </van-row>
                                </div>
                            </div>
                            <template #right> 
                                <van-button square text="删除" type="danger" class="delete-button" @click="removeComment(index,item.ccid)"></van-button>
                            </template>
                        </van-swipe-cell>
                    </div>
                    </van-list>
            

            </div>
        </van-tab>







     </van-tabs>


  </div>

  




 
</template>

<script>
import Vue from 'vue';
import { Lazyload } from 'vant';

Vue.use(Lazyload);
import {Toast} from 'vant';
Vue.use(Toast);
import { PullRefresh } from 'vant';

Vue.use(PullRefresh);
export default {
  name: 'Mine',
  data () {
    return {
      show:true,
      picIp:'http://localhost:8080/pic/',
      list:[],
      loading :false,
      finished:false,
      refreshing :false,
      page:1,
      active:1,
      user:{}
    }
  },
  created(){
        var user=JSON.parse(localStorage.getItem("userInfo"));
          
          console.log(user);
          this.user=user;
         this.axios.get("comment/getByUid?uid="+this.user.uid).then((response) =>{
            this.list = response.data;
            
          })
  },
  
  methods: {
      removeComment(index,ccid)
      {
          this.axios.post("comment/remove",
          {
              ccid:ccid,
          }).then((response)=>{
              this.list.splice(index,1);
              Toast.success("删除评论成功！");
          })
      },
      
        onClickLeft()
        {
            this.$router.go(-1);
        },
        getDetail(sno)
        {
            this.$router.push("/Detail/"+sno)
        },
      onLoad(){
          var user=JSON.parse(localStorage.getItem("userInfo"));
          
          console.log(user);
          this.user=user;
          if(this.user=null)
          {
              Toast("登录，才有评论!");
              this.$router.push("/Mine");
              return ;
          }
           this.axios.get("comment/getByUid?uid="+this.user.uid).then((response) =>{
            this.list = response.data;
            
          })
         
          
      },
    
  },

 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.comment-title
{
    text-align: left;
    margin: 15px 10px;
}
.comment-card{
    background-color:#fff;
}
.news-card .top .top-left img
{
    height: 25px;
    width: 25px;
}
.news-card
{
    border: 0.8px solid #eeee;
    margin:15px 10px;
}
.share
{
    margin: 15px 10px;

}
.delete-button
{
    background-color: #42b983;
    border:#42b983;
    height: 100%;
}
</style>
