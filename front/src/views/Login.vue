<template>
  <div id="building" style="display: flex;width: 100%;height: 100vh;">
    <el-card class="box-card" style="height: 450px;width: 700px;margin-left: 30%;margin-top:150px;background: rgba(255,255,255,0.7)">
      <template #header>
        <div class="card-header">
          <span>欢迎登录管理系统</span>
        </div>
      </template>
      <el-form :model="form" label-width="120px">
        <el-form-item label="用户名"  style="opacity: 100%;margin-top: 40px;width: 500px">
          <el-input v-model="form.name" placeholder="Please input username" />
        </el-form-item>
        <div style="height: 30px"></div>
        <el-form-item label="密码" style="opacity: 100%;margin-top: 20px;width: 500px">
          <el-input v-model="form.password"  type="password"
                    placeholder="Please input password"
                    show-password/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">登录</el-button>
          <el-button>注册</el-button>
          <el-button>找回密码</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
//import someComponent from './someComponent'
export default {
  name: "Login",
  data() {
    return {
      form:{
        name:'',
        password:'',
      }

    }
  },

}
</script>

<style scoped>
#building{
  background:url("../assets/login.jpeg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}

</style>
