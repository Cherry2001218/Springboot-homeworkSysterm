<template>
  <div class="hello">

      <div class="nav">
        <van-icon name="arrow-left" @click="onClickLeft"></van-icon>
      </div>
    <h1>增加作业项目</h1>
    <van-form @submit="onSubmit" class="form">

     

      <van-field
        v-model="course.wname"
        name="course.wname"
        label="作业名称"
        placeholder="作业名称"
       
      
      />
      
      <van-field
        v-model="course.wtxt"
        name="course.wtxt"
        label="作业详细"
        placeholder="作业详细"
       
      
      />
    
      
      <div style="margin:16px">
        <van-button round block type="warning" native-type="submit"  color="skyblue">提交</van-button>
      </div>

    </van-form>
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
      
      picIp: "http://localhost:8080/pic/",
      course: {
        sno: '',
        wname: '',
        wtxt: '',
        
      },
  user:JSON.parse(localStorage.getItem("userInfo")),
    };
  },
  created() {},

  methods: {
   
    onClickLeft(){
      this.$router.go(-1);//返回上一个路由
    },

    onSubmit()
    {
         
       console.log("aaaaas")
       console.log(this.value)
       console.log(this.course.wtxt)
       console.log(this.course.wname)
        
        this.axios.post("course/add",{
            sno:this.value,
            ctxt:this.course.wtxt,
            wname:this.course.wname,

        }).then(response=>{
          this.course.wtxt="";
           this.course.wname="";
          
        })
        
    }
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
