<template>
    <div class="hello">
        <h2>发布课程</h2>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="课程名称" prop="course">
                <el-input v-model="ruleForm.course"></el-input>
            </el-form-item>
            <el-form-item label="课程老师" prop="teachername">
                <el-input v-model="ruleForm.teachername"></el-input>
            </el-form-item>
            <el-form-item label="教授老师编号" prop="tno">
                <el-input v-model="ruleForm.tno"></el-input>
            </el-form-item>
            
            <el-upload
               ref="upload"
               action="http://localhost:8080/ss/class/add"
               list-type="picture-card"
               :auto-upload="false"
               :file-list="fileList"
               :data="ruleForm"
               name="imgFile"
               :on-success="handleSuccess">
                    <i slot="default" class="el-icon-plus"></i>
                    <div slot="file" slot-scope="{file}">
                    <img
                       class="el-upload-list__item-thumbnail"
                       :src="file.url" alt=""
                    >
                    <span class="el-upload-list__item-actions">
                        <span
                           class="el-upload-list__item-preview"
                           @click="handlePictureCardPreview(file)"
                        >
                            <i class="el-icon-zoom-in"></i>
                        </span>
                        <span
                            v-if="!disabled"
                            class="el-upload-list__item-delete"
                            @click="handleDownload(file)"
                        >
                            <i class="el-icon-download"></i>
                        </span>
                        <span
                            v-if="!disabled"
                            class="el-upload-list__item-delete"
                            @click="handleRemove(file)"
                        >
                            <i class="el-icon-delete"></i>
                        </span>
                    </span>
                </div>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    name:'AddNews',
    data(){
        return{
            fileList:[],
            dialogImageUrl: '',
            dialogVisible: false,
            disabled: false,
            options:[],
            ruleForm:{
                title:'',
                kindid:1,
                content:'',
                type:"1",
                source:"",
                editor:""
            },
            rules:{
                course:[
                    {required:true,message:'请输入课程名字',trigger:'blur'},
                    {min:3,max:30,message:'长度在3到30个字符',trigger:'blur'}
                ],
            
            }
        }
    },
    created(){
       
    },
    methods:{
        handleSuccess(){
            this.$message({
                message:'发布新闻成功',
                type:'success'
            });
            this.$router.push("/");
        },
        handleRemove(file) {
        console.log(file);
        },
        handlePictureCardPreview(file) {
           this.dialogImageUrl = file.url;
           this.dialogVisible = true;
        },
        handleDownload(file) {
           console.log(file);
        },
        submitForm(formName){
            this.$refs[formName].validate((valid)=>{
                if(valid){
                    this.$refs.upload.submit();
                }else{
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName){
            this.$refs[formName].resetFields();
        }
    },
}
</script>

<style scoped>

</style>