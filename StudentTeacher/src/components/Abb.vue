<template>
  <div class="hello">

      
    <h1>增加作业项目</h1>
    
  </div>
</template>

<script>
import Vue from "vue";
import { Toast } from "vant";

Vue.use(Toast);

export default {
  name: "Regist",
  data() {
      
    return {
         value:this.$route.params.value,
      show: true,
      
      picIp: "http://localhost:8080/pics/",
      course: {
        sno: '',
        wname: '',
        wtxt: '',
        
      },
  user:JSON.parse(localStorage.getItem("userInfo")),
    };
  },
  created() {

  },

  methods: {
   
    onClickLeft(){
      this.$router.go(-1);//返回上一个路由
    },

    
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.hello {
  padding: 2px;

}
.nav{

  background-color:#4fc08d;
  color:#fff;
  height:45px;
  width:100%;
  text-align: left;
  line-height: 45px;
  font-size:16px;
}

.nav .van-icon{
  margin-left:5px;
}
</style>
