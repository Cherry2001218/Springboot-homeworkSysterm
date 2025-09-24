<template>
  <div class="hello">

      <div class="nav">
        <van-icon name="arrow-left" @click="onClickLeft"></van-icon>
      </div>
    <h1>增加课程</h1>
  
    <van-form @submit="onSubmit" class="form">

  
      <van-field
          v-model ="classs.teachername"
        name="teachername"
        label="老师姓名"
        placeholder="老师名"
       
        :rules="[{required :true,messgae:'老师名'}]"
      />

      <van-field
          v-model ="classs.tno"
        name="tno"
        label="工号"
        placeholder="工号"
       
        :rules="[{required :true,messgae:'工号'}]"
      />
      <van-field
          v-model ="classs.course"
        name="course"
        label="课程"
        placeholder="课程名"
       
        :rules="[{required :true,messgae:'课程名'}]"
      />

    
      <van-field name="uploader" label="课程图片">
        <template #input>
          <van-uploader v-model="uploader" :before-read="beforeRead"></van-uploader>
        </template>
      </van-field>
      <div style="margin:16px">
        <van-button round block type="warning" native-type="submit">提交</van-button>
      </div>

    </van-form>
   
  </div>
</template>

<script>
import Vue from "vue";
import { Toast } from "vant";

Vue.use(Toast);

export default {
  name: "Course",
  data() {
    return {
      show: true,
      value: "",
      picIp: "http://localhost:8080/pic/",
      classs:{
          sno: '',
          course: '',
          teachername: '',
          time: '',
          pic: '',
          tno:'',
      },  
      user:{},
      pattern:/^.{6,32}$/,
      emailPattern:/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/,
      uploader: []
    };
  },
  created() {
  var user=JSON.parse(localStorage.getItem("userInfo"));

     this.classs.teachername = user.userName;
     this.classs.tno = user.uid;
  },

  methods: {
    validator(val)
    {
        return /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(val);
    },
   
    onSubmit(values) {
      
      console.log(values);
      var form = document.querySelector(".form");
      var formData = new FormData(form);
      var upload = this.uploader[0];
      if(upload == undefined)
      {
          Toast("No img");
          return ;
      }
      formData.append("imgFile", this.uploader[0].file);
     
     console.log(this.classs.tno);
      console.log(this.classs.teachername);
      this.axios
        .post("class/add", formData, {
          "content-type": "multipart/form-data"
        })
        .then(response => {
         
          if(response.data=="failed")
          {
            Toast("REGIST ERROR ， MAKE SURE");
          }else
          {
            Toast("CONGRATUATION");
            this.$router.push("/Mine");
          }
        });

    },
    beforeRead(file) {
      if (file.type !== "image/jpeg") {
        Toast("请上传jpg格式图片");
        return false;
      }
      return true;

    },
    onClickLeft(){
      this.$router.go(-1);//返回上一个路由
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
