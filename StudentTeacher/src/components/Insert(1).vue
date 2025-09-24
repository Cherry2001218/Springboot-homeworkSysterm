<template>
  <div class="hello">

      <div class="nav">
        <van-icon name="arrow-left" @click="onClickLeft"></van-icon>
      </div>
    <h1>增加作业项目</h1>
      <van-tabs v-model="active">

      <van-tab title="解答">

    <van-form @submit="onSubmit2" class="form">

     

      
      <van-field
        v-model="content.contentQuestion"
        name="content.contentQuestion"
        label="问题"
        placeholder="问题"
       
      
      />
       <div style="margin:16px">
        <van-button round block type="warning" native-type="submit">提交</van-button>
      </div>

    </van-form>

      </van-tab>
       <van-tab title="选择">
     <van-form @submit="onSubmit1" class="form">

     

      
      <van-field
        v-model="content.contentQuestion"
        name="content.contentQuestion"
        label="问题"
        placeholder="问题"
       
      
      />
       <van-field
        v-model="content.trueAnswer"
        name="content.trueAnswer"
        label="回答"
        placeholder="回答"
       
      
      />
        <van-field
        v-model="content.cA"
        name="content.cA"
        label="A"
        placeholder="A"
       
      
      />
        <van-field
        v-model="content.cB"
        name="content.cB"
        label="B"
        placeholder="B"
       
      
      />
        <van-field
        v-model="content.cC"
        name="content.cC"
        label="c"
        placeholder="c"
       
      
      />
        <van-field
        v-model="content.cD"
        name="content.cD"
        label="d"
        placeholder="d"
       
      
      />
      
      <div style="margin:16px">
        <van-button round block type="warning" native-type="submit">提交</van-button>
      </div>

    </van-form>
        </van-tab>
      </van-tabs>
   
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
      content: {
        contentQuestion: '',
        trueAnswer: '',
        kindid: 2,
        cA: '',
        cB: '',
        cC: '',
        cD: ' ',
        cid:0,
        
      },
  user:JSON.parse(localStorage.getItem("userInfo")),
    };
  },
  created() {},

  methods: {
   
    onClickLeft(){
      this.$router.go(-1);//返回上一个路由
    },
    onSubmit2()
    {
        console.log(this.value)
       console.log(this.content.contentQuestion)

       this.content.kindid=3
       this.axios.post("content/Add2",{
           cid:this.value,
            kindid:this.content.kindid,
            contentQuestion:this.content.contentQuestion,
       }).then(response=>{
  
         this.content.contentQuestion="";
          
        })
    },
    onSubmit1()
    {
          
       console.log("aaaaas")
       console.log(this.value)
       console.log(this.content.contentQuestion)
       console.log(this.content.trueAnswer)
       console.log(this.content.kindid)
       console.log(this.content.cA)
       console.log(this.content.cB)
       console.log(this.content.cC)
       console.log(this.content.cD)
        this.content.kindid=2
        this.axios.post("content/Add",{
           cid:this.value,
           cA:this.content.cA,
           cB:this.content.cB,
           cC:this.content.cC,
           cD:this.content.cD,
           contentQuestion:this.content.contentQuestion,
           trueAnswer:this.content.trueAnswer,
           kindid:this.content.kindid,
         

        }).then(response=>{
          this.content.cA="";
         this.content.cB="";
         this.content.cC="";
         this.content.cD="";
         this.content.trueAnswer="";
         this.content.contentQuestion="";
          
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
