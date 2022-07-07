<template>
<div style="margin: 30px">
  <template >
    <el-table
        v-loading="loading"
        :data="tableData"
        style="width: 100%">
      <el-table-column
          prop="workshop_id"
          label="车间编号">
      </el-table-column>
      <el-table-column
          prop="goods_id"
          label="物品编号">
      </el-table-column>
      <el-table-column
          prop="goods_name"
          label="物品名称">
      </el-table-column>
      <el-table-column
          prop="quantity"
          label="申请数量">
      </el-table-column>
      <el-table-column
          prop="created_time"
          label="申请时间">
      </el-table-column>
      <el-table-column
          prop="created_by"
          label="申请人">
      </el-table-column>
      <el-table-column
          label="操作">
        <template slot-scope="scope">
          <el-button v-if="(scope.row.location==='' | scope.row.location===null )"  @click="searchQuantity(scope.$index,scope.row)" type="text" size="small">查询库存</el-button>
          <el-button v-else @click="inOutApply(scope.$index,scope.row)" :disabled="scope.row.location < scope.row.quantity" type="text" size="small">出库申请</el-button>
        </template>
      </el-table-column>
      <el-table-column
          fixed="right"
          style="width: 1px"
          prop="location"
          label="库存数量">
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
</div>
</template>

<script>
import {my_request} from "@/network/requests/workshop_request";

export default {
  name: "itemRequest",
  data(){
    return {
      enough:true,
      input:'',
      tableData:[

      ],
      loading:false,
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
      let req = {
        current: this.page.current,
        pages:this.page.pages
      }

      my_request({
        url:'goods/applyItem',
        method:'get',
        params:req
      }).then(res=>{
        console.log(res)
        if (res.data.success === true){
          this.tableData = res.data.result.records
          this.total = res.data.result.total

        }else {
          this.$message.error('查询异常')
        }
      }).catch(err=>{
        console.log('请求数据getdata的返回值err:',err)
        this.$message.error('服务器异常')
      })

    },
    searchQuantity(index,row){
      let req = {
        good_id: row.goods_id,
        type:row.types
      }
      console.log('查询数量的req:',req)
      my_request({
        url:'goods/searchQuantity',
        params:req,
        method:'get'
      }).then(res=>{
        console.log('给我卡卡库存数量:',res)
        if(res.data.success===true){
          this.tableData[index].location = res.data.result
          // this.tableData[index].location =98
          console.log(this.tableData)
        }else {
          this.$message.error('查询失败')
        }
      }).catch(err=>{
        this.$message.error('服务器异常')
      })

    },
    inOutApply(index,row){
      let  req = {
        uuid : row.uuid,
        user:this.$store.getters.userinfo.id
        // user:1
      }
      console.log('申请出库的req',req)

      my_request({
        url:'goods/setApply',
        params:req,
        method:'get'
      }).then(res=>{
        console.log('发出出库申请的res',res)
        if(res.data.success===true){
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

    }
  }
}
</script>

<style scoped>
/deep/ td {
  padding:3px !important;

  height: 6px;
  overflow: hidden;
}
</style>