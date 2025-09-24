<template>
 <div class="hello">
      <div class="nav">
        <van-icon name="arrow-left" @click="onClickLeft"></van-icon>
      </div>

   
<div class="qq">
    <h2>{{top.wname}}</h2>
</div>
   
<div class="list">
    
        <div class="card" v-for="(item,index) in list" :key="item.cid"   >
           <div class="test-content" v-if="index==page">
            <div class="card-left" >
              <div class="title">
                
                {{index+1}}   {{item.contentQuestion}}
                <br/>
              </div>
                <div class="check" v-if="item.kindid != 3">
          <van-checkbox-group v-model="result[index]">
             <van-checkbox name="A">A {{item.ca}}</van-checkbox>
             <br/>
             <van-checkbox name="B">B {{item.cb}}</van-checkbox>
             <br/>
             <van-checkbox name="C">C {{item.cc}}</van-checkbox>
             <br/>
             <van-checkbox name="D">D {{item.cd}}</van-checkbox>
            <br/>
        </van-checkbox-group>
               <div style="margin:16px" @click=" submit(item,index,grade)">
                    <van-button round block type="info" native-type="submit(item,index,grade)" color="skyblue">提交答案</van-button>
                </div>
                </div>
            <div class="qa" v-if="item.kindid == 3">
         
                 <van-field
                    v-model ="cTxt"
                    clearable
                    placeholder="请输入答案"
                    @keyup.enter="submitAnswer"
                    >
                    <template #button>
                <van-button size="small" type="warning" @click="submitAnswer(item)"  color = "skyBlue">
                    发送
                </van-button>
                    </template>
                    </van-field>


            </div>
            <div style="margin:16px" @click="toBack(index) ">
                    <van-button round block type="info" native-type="submit">上一题</van-button>
                </div> 
             <div style="margin:16px" @click="toNext(index,item.cid)" >
                    <van-button round block type="info" native-type="submit">下一题</van-button>
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
      picIp:'http://localhost:8080/pics/',
      content:{

      },
      qid:1,
      cTxt:'',
       result: [],
      list:[],
       loading :false,
      finished:false,
      refreshing :false,
      page: 0,
      top:'',
        user:JSON.parse(localStorage.getItem("userInfo")),
    }
  },
  created(){
//查询这个课程相关信息
  this.axios.get("content/getC?cid="+this.sno).then((response) =>{

    this.top = response.data;
  })
//查询这个课程的作业相关信息
     this.axios.get("content/getQ?cid="+this.sno).then((response) =>{
            this.list = response.data;
            this.loading = false;
          })


  },
  
  methods:  {

  submitAnswer(item)
  {

     if(this.user == null)
        {
          Toast("您没有登录，请先登录");
          this.$router.push("/Mine");
          return ;
        }

         console.log(item.qid)
        console.log(this.cTxt)
        if(this.cTxt.trim()=="" || this.cTxt.trim() == null)
        {
          Toast("亲爱的小朋友你没有输入答案哦");
          return;
        }
          console.log(this.user.uid)
          //console.log(cTxt)
          this.axios.post("stuanswer/add",{
            cid:this.user.uid,
            answer:this.cTxt,
            qid:item.qid
          }).then(response=>{
            this.cTxt="";
          })

          
          
  },



      getQuestion(cid){
        this.$router.push('/Question'+cid);
      },

      toNext(index,id)
      {
        index++;
        if(this.page == this.list.length-1)
        {
          Toast("已经是最后一题")
        }
        else
        {
        this.page++;
        }
        
      },

      toBack(index)
      {
        index--;
        if(this.page == 0)
        {

            Toast("这是第一题")
        }
        else
        {
          this.page--;
        }

      },

//返回上一级的功能
    onClickLeft(){
      this.$router.go(-1);//返回上一个路由
    },
//判断是否刷新
      onLoad(){
          if(this.refreshing){
            this.list = [];
            this.refreshing = false;
          }
         
      },
      onRefresh(){
        //下拉刷新
        this.finished = false;
        //将 loading 设置成true ，表示处于加载状态
        this.loading = true;
        this.page = 1;
        this.onLoad();
      },

    submit(item,index)
    {
    console.log(item.trueAnswer);
      console.log(this.result);

     if(this.result[index] != item.trueAnswer)
      {
        Toast("真不幸，你写错了 正确答案是"+item.trueAnswer);
        
      }
      else
      {
       
        Toast("真棒你写对了！");
      }
    },
  },

  
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
</style>
