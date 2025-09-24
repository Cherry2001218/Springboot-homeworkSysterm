<template>
 <div class="hello">
      <div class="nav">
        <van-icon name="arrow-left" @click="onClickLeft"></van-icon>
      </div>

   

   
<div class="list">
    
       
      
  <div class="card" v-for="item in list" :key="item.sno" @click="getDetail(item.sno)">
           
            <div class="card-right">
              <div class="title">
                {{item.contentQuestion}}
              </div>
              <div class="student">
                 <span>学生 {{item.userName}}</span>
                <br/>
                <div class="answer">
                <span>{{item.answer}}</span>
              </div>
              </div>
            </div>
           
          </div>
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
      content:{

      },
       result: [],
      list:[],
       loading :false,
      finished:false,
      refreshing :false,
      page: 0,
      top:'',
        user:JSON.parse(localStorage.getItem("userInfo")),
        active:1,
    }
  },
  created(){
//查询这个课程相关信息

//查询这个课程的作业相关信息
     this.axios.get("stuanswer/Show?tno="+this.sno).then((response) =>{
            this.list = response.data;
            this.loading = false;
            console.log(this.list)
            if(this.list.length == 0)
            {
                Toast("Empty");
                    this.$router.push("/CourseServe");
            }
          })

   
  },
  
 
  methods: {
   
    onClickLeft(){
      this.$router.go(-1);//返回上一个路由
    }
  }
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
.content{
    text-align: center;
}



<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.student
{   color :#aaa;
  margin-top: 40px;
   font-size: 20px;
    line-height: 30px;
    text-align: left;
   }

.my-swipe .van-swipe-item {
    color: #fff;
    font-size: 20px;
    line-height: 150px;
    text-align: center;
    background-color: #39a9ed;
  }
  .answer{
      color :#aaa;
  margin-top: 40px;
   font-size: 20px;
    line-height: 30px;
    text-align: center;
    background-color:   #fff5ee;;
  }
  
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.van-swipe{
  height: 200px;
}
.van-swipe img{
  height: 180px;
}
.list{
  background-color: #eee;
}
.card{
  display:flex;
  margin: 5px 1px;
  background-color: #ffffff;
}
.card-right{
  flex:8;
  text-align: right;
  padding:5px;
}
.card-right .title{
     text-align: center;
  font-size: 17px;
  color :#000;
}
.card-right .time{
  font-size: 20px;
  color :#aaa;
  margin-top: 40px;
}
.card-right .time span{
  margin: 0 2px;
  font-size: 20px;
}
.card-left{
  text-align: left;
  flex:2;
}
.card-left img{
  height: 90px;
  width: 120px;
}
.van-swipe
{

  height: 250px;
  margin-top: 50px;
}
.van-swipe img{
  height: 100%;
  width: 100%;
}
</style>
