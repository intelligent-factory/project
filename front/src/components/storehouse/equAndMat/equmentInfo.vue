<template>
  <div style=" ;width: 80%;height:80%;margin: 10px auto 0;">
    <div style="width: 100%;height: 40px;margin: 10px 0;margin-bottom: 20px ;display: flex;justify-content: space-between">
      <div style="width: 30%;height: 40px; ;display: flex;justify-content: flex-start">
        <div style="width: 80%;height: 40px;display: flex;justify-content: space-between">
        <el-form :model="input" ref="input" label-width="100px" class="demo-ruleForm">
          <el-form-item
              label="设备编号"
              prop="input"
              :rules="[
                        // { required: true, message: '物料id不能为空'},
                      ]">
            <el-input v-model.trim="input.input" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        </div>
        <el-button  @click="submitForm('input')">搜索</el-button>
      </div>

      <div style="width: 30%;height: 40px; "></div>
      <div style="width: 30%;height: 40px; ">
        <el-button @click="addMaterial = true"  >增加</el-button>
      </div>
    </div>
    <template >
      <el-table
          v-loading="loading"
          :data="equipments"
          style="width: 100%">
        <el-table-column
            prop="equipment_id"
            label="设备编号">
        </el-table-column>
        <el-table-column
            prop="name"
            label="设备名称">
        </el-table-column>
        <el-table-column
            prop="purpose"
            label="用途">
        </el-table-column>
        <el-table-column
            prop="status"
            label="状态">
        </el-table-column>
        <el-table-column
            prop="comments"
            label="描述">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button @click="deleteThis(scope.row)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>

      </el-table>
    </template>
    <div class="block" style="padding: 10px;margin-top: 10px">
      <el-pagination
          @prev-click="preclick"
          @next-click="nextclick"
          @current-change="curChange"
          :hide-on-single-page=true
          :total="total"
          background
          layout="total,prev, pager, next, jumper"
          :page-size="page.pages">
      </el-pagination>
    </div>

    <el-dialog
        title="修改物品信息x"
        :visible.sync="addMaterial"
        width="30%"
        :before-close="handleClose">

      <el-form :model="incrementM" ref="incrementM" label-width="100px" class="demo-ruleForm">
        <el-form-item
            label="设备名称"
            prop="name"
            :rules="[
                        { required: true, message: '物料名称不能为空'},
                      ]">
          <el-input v-model.trim="incrementM.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            label="设备用途"
            prop="purpose"
            :rules="[
                        { required: true, message: '物料型号不能为空'},
                      ]">
          <el-input  v-model.trim="incrementM.purpose" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            label="设备状态"
            prop="status"
            :rules="[
                        { required: true, message: '物料颜色不能为空'},
                      ]">
          <el-input  v-model.trim="incrementM.status" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            label="设备描述"
            prop="comments"
            :rules="[
                        { required: true, message: '物料描述不能为空'},
                      ]">
          <el-input  v-model.trim="incrementM.comments" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="incrementThis('incrementM')">提交</el-button>
          <el-button @click="resetForm('incrementM')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog
        title="修改信息"
        :visible.sync="updateM"
        width="30%"
        :before-close="handleClose">

      <el-form :model="updateInfo" ref="updateInfo" label-width="100px" class="demo-ruleForm">
        <el-form-item
            label="状态"
            prop="status"
            :rules="[
                        { required: true, message: '物料状态不能为空'},
                      ]">
          <el-input v-model.trim="updateInfo.status" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            label="描述"
            prop="comments"
            :rules="[
                        { required: true, message: '物料状态不能为空'},
                      ]">
          <el-input v-model.trim="updateInfo.comments" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="updateThis('updateInfo')">修改</el-button>
          <el-button @click="resetForm('updateInfo')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {my_request} from "@/network/requests/workshop_request";
export default {
  name: "equipmentInfo",
  data(){
    return {
      loading:false,
      updateM:false,
      addMaterial:false,
      incrementM:{
        name:'',
        purpose:'',
        status:'',
        comments:'',
        operator_id:'',
      },
      input:{
        input:'',
      },
      updateInfo:{
        equipment_id:'',
        operator_id:'',
        status:'',
        comments:'',
      },
      equipments:[
        {
          equipment_id:'1',
          name:1,
          purpose:'osad',
          status:'正',
          comments:'sad'
        },
      ],
      total: 1,
      page:{
        pages: 13,
        current: 1,
      },

    }
  },
  created() {
    this.getData()
  },
  methods:{
    preclick(current){
      this.page.current=current
      this.loading = true
      setTimeout(()=>{
        this.getData()
        this.loading=false
      },1000)
    },
    nextclick(current){
      this.page.current=current
      this.loading = true
      setTimeout(()=>{
        this.getData()
        this.loading=false
      },1000)

    },
    curChange(current){
      this.page.current=current
      this.loading = true
      setTimeout(()=>{
        this.getData()
        this.loading=false
      },1000)
    },
    getData(){
      this.loading = true
      let req = {
        pageOffset: this.page.current,
        pageSize: this.page.pages
      }
      console.log("???")
      my_request({
        url: 'process/getEquipments',
        method: 'get',
        params: req,
      }).then(res=>{

        console.log(res)
        this.equipments = res.data.equipments
        this.total = res.data.count
        this.loading = false
      }).catch(err=>{

        this.loading = false
        this.$message.error('服务器异常')
      })

    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    incrementThis(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true
          let req = {
            name: this.incrementM.name,
            purpose: this.incrementM.purpose,
            status:this.incrementM.status,
            comments: this.incrementM.comments,
            operator_id: 1,
          }

          my_request({
            url:'process/addEquipment',
            method: 'post',
            data: req,
          }).then(res =>{
            if (res.data==='添加成功'){
              this.addMaterial = false
              this.getData()
              this.$message({
                type:'success',
                message:'添加成功'
              })
            }else {
              this.$message.error('添加失败')
            }
            this.loading=false

          }).catch(err=>{

          })

        } else {

          this.loading = false
          this.$message.error('服务器异常')
        }
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          if (this.input.input === '') {
            this.getData()
            // }else {

            this.loading = true
            let req = {
              equipment_id: this.input.input
            }

              my_request({
                url: 'process/getEquipmentByID',
                params: req,
                method: 'get'
              }).then(res => {
                let s = []
                s.push(res.data)
                if (res.data!==null){
                  this.equipments = s
                  this.total = 1


                }else {
                  this.$message.error('未查询到该物品')
                }
            this.loading = false
            this.loading=false
              }).catch(err => {

              })
            }
          } else {

            this.loading = false
            this.$message.error('服务器异常')
          }

      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    handleClick(row){
      this.$router.push({
        path:'/storehouse/equAndMatMain/equipmentInfoDetail',
        query:{
          info: row
        }
      })
    },
    deleteThis(row){
      this.loading = true
      let req = {
        equipment_id : row.equipment_id,
        operator_id : '1',
      }

      my_request({
        url:'process/deleteEquipmentByID',
        method:'post',
        data:req
      }).then(res=>{
        if (res.data==='删除成功'){
          this.$message({
            type:'success',
            message:'删除成功'
          })
        }else {
          this.$message.error('删除失败')
        }
        this.getData()
      }).catch(err=>{
        console.log(err)
        this.loading = false
        this.$message.error('服务器异常')
      })
    },

    edit(row){
      this.updateM = true
      this.updateInfo.equipment_id = row.equipment_id
      this.updateInfo.operator_id = '1'
    },
    updateThis(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let req= this.updateInfo
          this.loading=true

          my_request({
            url:'process/updateEquipmentByID',
            data:req,
            method:'post'
          }).then(res=>{
            console.log(res)
            this.$message({
              type:'success',
              message:'修改成功'
            })

            this.updateM = false
            this.getData()
          }).catch(err=>{
            console.log(err)
            this.loading = false
            this.$message.error('服务器异常')
          })

        } else {

          this.loading = false
          this.$message.error('服务器异常')
        }
      });
    }
  }
}
</script>

<style scoped>
/deep/  td {
  padding:3px !important;
  /*border: 1px solid blue;*/
  height: 6px;
  overflow: hidden;
}
</style>