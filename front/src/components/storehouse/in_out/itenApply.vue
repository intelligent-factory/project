<template>
<div style="margin: 50px;width: 40%">
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="设备申请" name="first">
      <el-form :model="equipmentInfo" ref="equipmentInfo" label-width="100px" class="demo-ruleForm">
        <el-form-item
            label="车间编号"
            prop="workshop_id"
            :rules="[
                      { required: true, message: '车间编号不能为空'},
                    ]">
          <el-input  v-model.trim="equipmentInfo.workshop_id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            label="设备编号"
            prop="equip_id"
            :rules="[
                      { required: true, message: '设备编号不可为空'},
                    ]">
          <el-input  v-model.number="equipmentInfo.equip_id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            label="申请数量"
            prop="quantity"
            :rules="[
                      { required: true, message: '申请数量不能为空'},
                    ]">
          <el-input  v-model.trim="equipmentInfo.quantity" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitApplyEquip('equipmentInfo')">提交</el-button>
          <el-button @click="resetForm('equipmentInfo')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-tab-pane>
    <el-tab-pane label="物料申请" name="second">
      <el-form :model="materialInfo" ref="materialInfo" label-width="100px" class="demo-ruleForm">
        <el-form-item
            label="车间编号"
            prop="workshop_id"
            :rules="[
                      { required: true, message: '车间编号不能为空'},
                    ]">
          <el-input  v-model.trim="materialInfo.workshop_id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            label="物料编号"
            prop="material_id"
            :rules="[
                      { required: true, message: '物料编号不可为空'},
                    ]">
          <el-input  v-model.number="materialInfo.material_id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            label="申请数量"
            prop="quantity"
            :rules="[
                      { required: true, message: '申请数量不能为空'},
                    ]">
          <el-input  v-model.trim="materialInfo.quantity" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitApplyMaterial('materialInfo')">提交</el-button>
          <el-button @click="resetForm('equipmentInfo')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
import {my_request} from "@/network/requests/workshop_request";

export default {
  name: "itenApply",
  data() {
    return {
      activeName: 'second',
      equipmentInfo:{
        workshop_id:'',
        equip_id:'',
        quantity:'',
      },
      materialInfo:{
        workshop_id: '',
        material_id:'',
        quantity: ''
      }
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    submitApplyEquip(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let req = {
            workshop_id: this.equipmentInfo.workshop_id,
            id: this.equipmentInfo.equip_id,
            quantity: this.equipmentInfo.quantity,
            type: 'equipment',
            user: this.$store.getters.userinfo.id
            // user: 1
          }
          console.log('申请设备的req：',req)
          my_request({
            url:'goods/apply',
            method:'get',
            params:req
          }).then(res=>{
            console.log('申请设备的res：',res)

            if (res.data.success===true){
              this.$message({
                type:'success',
                message:'申请成功'
              })
              this.$refs[formName].resetFields();
            }else {
              this.$message.error('申请失败')
            }
          }).catch(err=>{
            this.$message.error('服务器异常')
          })

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    submitApplyMaterial(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let req = {
            workshop_id: this.materialInfo.workshop_id,
            id: this.materialInfo.material_id,
            quantity: this.materialInfo.quantity,
            type:'material',
            user: this.$store.getters.userinfo.id
            // user: 1
          }
          console.log('申请材料的req：',req)

          my_request({
            url:'goods/apply',
            method:'get',
            params:req
          }).then(res=>{
            console.log('申请材料的res：',res)

            if (res.data.success===true){
              this.$message({
                type:'success',
                message:'申请成功'
              })
              this.$refs[formName].resetFields();
            }else {
              this.$message.error('申请失败')
            }
          }).catch(err=>{
            this.$message.error('服务器异常')
          })

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }


}
</script>

<style scoped>

</style>