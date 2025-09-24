<template>
  <div class="hello">

      <div class="nav">
        <van-icon name="arrow-left" @click="onClickLeft"></van-icon>
      </div>
    <h1>注册</h1>
    <van-form @submit="onSubmit" class="form">

      <van-field
        v-model="user.uid"
        name="uid"
        label="Uid"
        placeholder="请输入您的学号"
        
        :rules="[{pattern,required :true,messgae:'学号6-12'}]"
      />

      <van-field
        v-model="user.userName"
        name="userName"
        label="用户名"
        placeholder="用户名"
       
        :rules="[{required :true,messgae:'用户名6-12'}]"
      />
      <van-field
        v-model="user.password"
        type="password"
        name="password"
        label="密码"
        placeholder="密码"
        :rules="[{pattern,required :true,messgae:'请填写密码密码是6-20'}]"
      />

      <van-field
        v-model="user.password1"
        type="password"
        name="password1"
        label="密码"
        placeholder="密码"
        :rules="[{pattern,required :true,messgae:'请确认填写密码'}]"
      />

      <van-field
        v-model="user.email"
        name="email"
        label="邮箱"
        placeholder="邮箱"
        :rules="[{validator,required :true,messgae:'请填写邮箱，并保证其正确'}]"
      />
      <van-field name="uploader" label="头像">
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
  name: "Regist",
  data() {
    return {
      show: true,
      value: "",
      picIp: "http://localhost:8080/pics/",
      user: {
        uid: '',
        username: '',
        password: '',
        password1: '',
        email: ''
      },
      pattern:/^.{6,32}$/,
      emailPattern:/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/,
      uploader: []
    };
  },
  created() {},

  methods: {
    validator(val)
    {
        return /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(val);
    },
    checkUsername()
    {
      if(this.user.username.trim()=='')
      {
        Toast("请输入用户名");
        return ;
      }
      this.axios.post("user/checkUsername",{
        userName:this.user.userName
      }).then(response=>{
        if(response.data == "exist")
      {
        Toast("Error!!!");
        this.user.userName=''
     }else
     {
       Toast("Yep!");
     }
      })
    },
    onSubmit(values) {
      if ((this.user.password != this.user.password1)) {
        Toast("密码与确认密码不一致");
        return;
      }
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
      this.axios
        .post("user/regist", formData, {
          "content-type": "multipart/form-data"
        })
        .then(response => {
          console.log(response.data);
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
