<template>

  <div class="hello">
 <div class="nav">
        <van-icon name="arrow-left" @click="onClickLeft"></van-icon>
      </div>
    

    <div class="list">

        <div class="head">
            {{author.userName}}
        </div>
      <van-pull-refresh v-model="refreshing" @refresh="onRefresh">

        <van-list
         v-model="loading"
         :finished="finished"
         finished-text="没有更多了"
         @load="onLoad"
        >
        <div class="clist" v-for="(item,index) in list" :key="index" >
            <div class="time">
                {{item.data|moment}}
            </div>
            <div class="chat " :class="{self:item.fromUser==user.userName}">
              <van-image :src="item.fromUser==user.userName?picIp+user.image:picIp+author.image" lazy-load height="60" width="60" round/>
              
              
              <div class="content">
                  {{item.content}}
              </div>
            </div>
          </div>
        </van-list>
      </van-pull-refresh>

     <div class="comment-input">
         <van-field
         v-model ="content"
        clearable
        placeholder="请输入聊天内容"
        @keyup.enter="send"
         >
         <template #button>
            <van-button size="small" type="warning" @click="send">
                发送
            </van-button>
         </template>
         </van-field>
     </div>
     

    </div>
  </div>

  




 
</template>

<script>
import Vue from 'vue';
import { Lazyload } from 'vant';

Vue.use(Lazyload);

import { PullRefresh } from 'vant';
import SockJs from 'sockjs-client'
import Stomp from 'stompjs'
Vue.use(PullRefresh);
export default {
  name: 'DataSend',
  data () {
    return {
      
      author:this.$route.query,
      value:'',
      picIp:'http://localhost:8080/pic/',
     
      list:[],
      loading :false,
      finished:false,
      refreshing :false,
      page:1,
      user:JSON.parse(localStorage.getItem("userInfo")),
      followLabel:"关注",
      active:1,
      content:'',
      stompClient:null,
    }
  },
  created(){
    
        console.log(this.author.userName);
        console.log(this.user.userName);
  this.connect();
  },
  
  methods: {
      connect(){
            var socket = new SockJs("http://127.0.0.01:8080/ws/ep");
            this.stompClient = Stomp.over(socket);
            this.stompClient.connect({},success=>{
                this.stompClient.subscribe('/user/'+this.user.userName+'/queue/chat',response=>{
                    var receiveMsg = JSON.parse(response.body);
                    console.log(receiveMsg);
                    receiveMsg.toUser=receiveMsg.fromUser;
                    this.list.push(receiveMsg)
                });
            });
      },

      send()
      {
            if(this.user == null)
                    {
                    Toast("您没有登录，请先登录");
                    this.$router.push("/Mine");
                    return ;
                    }
                    if(this.content.trim()=="")
                    {
                    Toast("您未输入评论，请输入内容后再发表");
                    return;
                    }
                   var msg={
                       fromUser:this.user.userName,
                       content:this.content,
                       toUser:this.author.userName,
                     //  data:new Data()
                   };

                   this.list.push(msg)
                   this.stompClient.send("/chat",{},JSON.stringify(msg));
                   this.content=""

      },
     onLoad(){

         if(this.user == null)
    {

      Toast("您没有登录，请先登陆后再关注");
        return ;
    }

          if(this.refreshing){
            this.list = [];
            this.refreshing = false;
          }
          this.axios.get("chatmsg/list?current="+this.page+"&fromUser="+this.user.userName+"&toUser="+this.author.userName).then((response) =>{
            this.list = this.list.concat(response.data.records);
            this.loading = false;
            if(this.page >= response.data.pages){
              this.finished = true;

            }
            this.page++;
          })
      },

      onRefresh(){
        //下拉刷新
        this.finished = false;
        //将 loading 设置成true ，表示处于加载状态
        this.loading = true;
        this.page = 1;
        this.onLoad();
      },
       onClickLeft(){
      this.$router.go(-1);//返回上一个路由
    }
  },

 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.clist
{
    margin-bottom: 60px;

}

.clist .time
{
    margin:10px 0;
    font-size: 13px;
}

.clist .chat
{
        display: flex;
        padding:0 10px;
}

.chat .content
{
        height: 30px;
        line-height: 0 5px;
        margin: 0 5px;
        border: 0.5px solid #ddd;
        padding: 0 10px;
        background-color: rgb(255 246 143);
        border-radius: 5px;
        font-size: 17px;
}
.self
{
    display: flex;
    flex-direction: row-reverse;
}
.self .content{
    background-color:rgb(32 178 170)
}

.comment-input
{
  
  padding-bottom:  50px;
}
</style>
