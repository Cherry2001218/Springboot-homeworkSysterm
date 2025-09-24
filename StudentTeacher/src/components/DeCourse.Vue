<template>

  <div class="hello">

<div class="nav">
        <van-icon name="arrow-left" @click="onClickLeft"></van-icon>
      </div>
    <van-list
  v-model="loading"
  :finished="finished"
  finished-text="没有更多了"
  @load="onLoad"
>
  <div class="card" v-for="item in list" :key="item.sno" >
             <div class="card-left">
              <img :src="picIp+item.pic" alt="">

            </div>
            <div class="card-right">
              <div class="title">
                {{item.course}}
              </div>
              <div class="time">
                <span>上课时间：{{item.time|moment}}</span>
                <br/>
                <span>教授老师：{{item.teachername}}</span>
              
              </div>
            </div>
     
           <van-button circle text="删除" type="danger" class="delete-button" @click="removeCourse(index,item.sno)" color="skyblue"></van-button>
        
          </div>
   
</van-list>
   </div>
</template>


<script>

import { Search } from 'vant';

Vue.use(Search);
import { Toast } from 'vant';
import Vue from 'vue';
import { List } from 'vant';

import { Grid, GridItem } from 'vant';
import { PullRefresh } from 'vant';
import { Lazyload } from 'vant';

Vue.use(Lazyload);

Vue.use(PullRefresh);
Vue.use(Grid);
Vue.use(GridItem);
Vue.use(List);
export default {
  name: 'HelloWorld',
  data () {
    return {
      value:'',
      picIp:'http://localhost:8080/pic/',
      images: [],
      list:[],
      loading :false,
      finished:false,
      refreshing :false,
      page:1
    }
  },
    created(){
    this.getSwipeList();
  },
   methods: {
       onClickLeft(){
      this.$router.go(-1);//返回上一个路由
    },
     getDetail(sno){
        this.$router.push('/Detail/'+sno);
      },

       onSearch() {
       
       this.$router.push("/Find");

      
    },
      getSwipeList(){
        this.axios.get("class/getSwipe").then((response) => {
          console.log(response.data)
          this.images=response.data.records;
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
    
    onLoad() {
       if(this.refreshing){
            this.list = [];
            this.refreshing = false;
          }
          this.axios.get("class/getPage?current="+this.page).then((response) =>{
            this.list = this.list.concat(response.data.records);
            this.loading = false;
            if(this.page >= response.data.totalpage){
              this.finished = true;

            }
            this.page++;
          })
  


  },
  removeCourse(index,sno)
  {
        
          this.axios.post("class/remove",
          {
              sno:sno,
          }).then((response)=>{
              this.list.splice(index,1);
              Toast.success("删除课程成功！");
          })
  }
},
}
</script>

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


.my-swipe .van-swipe-item {
    color: #fff;
    font-size: 20px;
    line-height: 150px;
    text-align: center;
    background-color: #39a9ed;
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
