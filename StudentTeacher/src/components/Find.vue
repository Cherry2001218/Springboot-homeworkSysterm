<template>
<div class="hello">
  <div >
    <div class="fixed">
    <van-search
  v-model="value"
  shape="round"

  placeholder="请输入搜索关键词"
  @search="onSearch"
/>
    </div>

    <!-- 搜索记录Begin-->
  <van-divider/>
    <div class="Search">
        <div>
            搜索历史
        </div>
        <div>
            <van-icon name="delete-o" @click ="clearHistory">

            </van-icon>
        </div>
    </div>
    <van-row>
        <van-col span="12" v-for="(item,index) in list" :key="index" @click="goSearch(item)">
            {{item}}    
        </van-col>
    </van-row>
    <van-divider/>
 </div>
 </div>
</template>

<script>
import Vue from 'vue';
import { Icon } from 'vant';
Vue.use(Icon);
export default {
  name: 'Find',
  data () {
    return {
    
      value:'',
      list:[],
    }
  },
  created(){
      var history = localStorage.getItem("history");
      if(history!=null){

          this.list=history.split(",");
      }
  },
  
  methods: {
      goSearch(item){
       this.$router.push("/FindAnswer"+item);

      },
      onSearch(val){
         

          var newArr = this.list.filter(item => item!=val)

          this.list = newArr;
          
          this.list.unshift(val);

          localStorage.setItem("history",this.list.toString());
         
         this.goSearch(val)
      },

      clearHistory(){
          localStorage.removeItem("history");
          this.list=[];
      }
  },

 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.search{
    display: flex;
    justify-content: space-between;
    padding: 5px;
    height: 30px;
    line-height: 30px;
}
.van-divider{
    margin-top: 65px;

}
.van-col{
    text-align: left;
    border-right: 0.5px solid #eee;
    padding:5px;
}
</style>
