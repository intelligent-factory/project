<template>
  <div style=" ;width: 80%;height:80%;margin: 10px auto 0;">
      <div style="width: 100%;display: flex;margin-bottom: 20px;justify-content: flex-start;flex-wrap: wrap">
    <div style="width: 100%;height: auto; ;margin-bottom: 10px;display: flex;justify-content: flex-start;flex-wrap: wrap">
      <div style="width: 10%;height:80px;margin: 0;padding:0;display: flex;justify-content: flex-start"><h2>设备信息:</h2></div>
      <div style="margin-left:50px;height: 80px;display: flex;justify-content: flex-start;">
        <div style="margin-right: 80px;height: 80px;line-height: 80px">设备编号: <span style="font-size: small;line-height: 80px">{{$route.query.info.equipment_id}}</span></div>
        <div>设备名称:<span style="font-size: small;line-height: 80px">{{$route.query.info.name}}</span></div>
      </div>
      <div style="display: flex;width: 60%;flex-direction: row-reverse">
        <div style="height: 40px;line-height: 40px;margin-top: 40px;display: flex;">
          <el-button @click="increment">增加</el-button>
        </div>
      </div>
    </div>
  </div>
      <div style="width: 100%">
    <template>
      <el-table
          :data="tableData"
          style="width: 100%">
        <el-table-column
            prop="storage_id"
            label="库区编号">
        </el-table-column>
        <el-table-column
            prop="shelf_id"
            label="货架编号"
            width="180">
        </el-table-column>
        <el-table-column
            prop="id"
            label="库位信息">
        </el-table-column>
        <el-table-column
            prop="quantity"
            label="库存数量">
        </el-table-column>
      </el-table>
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
    </template>
    </div>
    <el-dialog
        title="提示"
        :visible.sync="jinhuoFlag"
        width="30%"
        :before-close="handleClose">

      <el-form :model="jinhuoInfo" ref="jinhuoInfo" label-width="100px" class="demo-ruleForm">
        <el-form-item
            label="库区编号"
            prop="storage_id"
            :rules="[
                      { required: true, message: '库区编号不可为空'},
                    ]">
          <el-input type="age" v-model.trim="jinhuoInfo.storage_id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            label="货架编号"
            prop="shelf_id"
            :rules="[
                      { required: true, message: '货架编号不可为空'},
                    ]">
          <el-input type="age" v-model.trim="jinhuoInfo.shelf_id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            label="库位信息"
            prop="location"
            :rules="[
                      { required: true, message: '库区信息不可为空'},
                    ]">
          <el-input type="age" v-model.trim="jinhuoInfo.location" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            label="进库数量"
            prop="quantity"
            :rules="[
                      { required: true, message: '进货数量不可为空'},
                    ]">
          <el-input type="age" v-model.trim="jinhuoInfo.quantity" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="jinhuo('jinhuoInfo')">提交</el-button>
          <el-button @click="resetForm('jinhuoInfo')">重置</el-button>
        </el-form-item>
      </el-form>


    </el-dialog>
    <el-dialog
        title="提示"
        :visible.sync="updateFlag"
        width="30%"
        :before-close="handleClose">

      <el-form :model="updateInfo" ref="updateInfo" label-width="100px" class="demo-ruleForm">
        <el-form-item
            label="修改数量"
            prop="quantity"
            :rules="[
                      { required: true, message: '数量不可为空'},
                    ]">
          <el-input  v-model.number="updateInfo.quantity" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="incrementNum('updateInfo')">增加</el-button>
          <el-button @click="decrementNum('updateInfo')">减少</el-button>
        </el-form-item>
      </el-form>


    </el-dialog>

  </div>
</template>

<script>
import {my_request} from "@/network/requests/workshop_request";

export default {
  name: "equipmentInfoDetail",
  data(){
    return {
      updateFlag:false,
      updateInfo: {
          storage_id:'',
          shelf_id:'',
          location:'',
          id:'',
          quantity:'',
          update:'',
          type:'',
          user:'',
        },
      updateMax:0,
      jinhuoFlag:false,
      jinhuoInfo:{
        storage_id:'',
        shelf_id:'',
        location:'',
        id:'',
        quantity:'',
        type:'',
        user:'',
      },
      tableData:[

      ],
      page:{
        pages: 15,
        current: 1,
      },
      total:0,
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

      let req = {
        type: 'equipment',
        current: this.page.current,  //页数 从1开始
        pages: this.page.pages,
        goods_id: this.$route.query.info.equipment_id
      }
      console.log('equipment的getdata,req：',req)
      my_request({
        url:'goods/locationItem',
        params:req,
        method:'get'
      }).then(res=>{

        console.log('equipment的getdata：res',res)
        if (res.data.success===true){
          this.tableData = res.data.result.records
          this.total = res.data.total
        }else {
          this.$message.error('库区无此类物品存放')
        }
      }).catch(err=>{

      })
    },
    increment(){
      this.jinhuoFlag = true
    },
    jinhuo(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
         let req = {
           user : this.$store.getters.userinfo.id,
           // user : 1,
           type : 'equipment',
           id : this.$route.query.info.equipment_id,
           storage_id:this.jinhuoInfo.storage_id,
           shelf_id:this.jinhuoInfo.shelf_id,
           location:this.jinhuoInfo.location,
           quantity:this.jinhuoInfo.quantity
         }
          console.log('你也小时啊',req)
          my_request({
            url:'goods/addGoods',
            params:req,
            method:'get'
          }).then(res=>{
            console.log('金库信息的res',res)
            if (res.data.success===true){
              this.$message({
                type:'success',
                message:'申请成功'
              })
            }else {
              this.$message.error('申请失败')
            }
          }).catch(err=>{
            console.log('金库信息的err',err)
            this.$message.error('服务器异常')
          })
          this.jinhuoFlag=false
        } else {
          console.log('error submit!!');
          return false;
        }
      });
      this.$refs[formName].resetFields();
    },
    incrementNum(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let req = {
            storage_id:this.updateInfo.storage_id,
            shelf_id:this.updateInfo.shelf_id,
            location:this.updateInfo.location,
            id:this.updateInfo.id,
            quantity:this.updateInfo.quantity,
            update:'increment',
            type:'equipment',
            user:this.$store.getters.userinfo.id
            // user:1
          }

          my_request({
            url:"",
            params: req,
            method:'get'
          }).then(res=>{

            if (res.data.success===true){
              this.$message({
                type:'success',
                message:'申请成功'
              })
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

      this.updateFlag = false
      this.$refs[formName].resetFields();
    },
    decrementNum(formName){
      this.$refs[formName].validate((valid) => {
      if (valid) {
        let req = {
          storage_id:this.updateInfo.storage_id,
          shelf_id:this.updateInfo.shelf_id,
          location:this.updateInfo.location,
          id:this.updateInfo.id,
          quantity:this.updateInfo.quantity,
          update:'decrement',
          type:'equipment',
          user:this.$store.getters.userinfo.id
          // user:1
        }

        my_request({
          url:"",
          params: req,
          method:'get'
        }).then(res=>{

          if (res.data.success===true){
            this.$message({
              type:'success',
              message:'申请成功'
            })
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

      this.updateFlag = false
      this.$refs[formName].resetFields();
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    handleSearch(index,row){
      this.updateFlag = true
      this.updateInfo.storage_id = row.storage_id
      this.updateInfo.shelf_id = row.shelf_id
      this.updateInfo.location = row.location
      this.updateInfo.id = this.$route.query.info.equipment_id
      this.updateInfo.type = 'equipment'

      this.updateMax = row.quantity
      console.log('ths:',this.updateInfo)
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