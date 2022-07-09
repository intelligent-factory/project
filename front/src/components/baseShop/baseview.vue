<template>
  <div id="theBaseMainBox">
    <div style="height: 20px"></div>
    <div id="theBaseHeader">
      <!--实现查询的输入框和按钮    start-->
      <div id="theLeftBase">
        <el-form :inline="true" :model="formInline" class="demo-form-inline" ref="formInline" :rules="rules">

          <el-form-item label="选择信息">
            <el-select v-model="formInline.category" placeholder="选择信息" @change="selectChange">
              <el-option label="车间" value="workshop"></el-option>
              <el-option label="产线" value="line"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="查询信息" prop="info">
            <el-input v-model.trim="formInline.info" :placeholder="this.showLine===false?'车间编号/名称':'车间编号/名称+产线编号/名称'"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="search(formInline)">查询</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div id="theRightBase">
        <el-button :plain="true" icon="el-icon-plus" style="height: 90%" @click="createItem">新建项目</el-button>
      </div>
    </div>
    <div style="height: 20px"></div>
    <div id="theBaseMain">
      <!--      表格主题       -->
      <div v-if="!showLine">
        <el-table

            v-loading="loading"
            id="theMainTable"
            :data="tableData"
            highlight-current-row
            stripe
            @sort-change="sortchange"
            style="width: 100%">
          <el-table-column
              label="车间编号"
              prop="id">
          </el-table-column>
          <el-table-column
              label="车间名称"
              prop="name"
              width="180">
          </el-table-column>
          <el-table-column
              label="工厂名称"
              prop="factory_name">
          </el-table-column>
          <el-table-column
              label="产线数量"
              prop="lineNum"
              width="200px">
          </el-table-column>
          <el-table-column
              label="创建日期"
              prop="created_time"
              width="200px">
          </el-table-column>
          <el-table-column
              label="修改日期"
              prop="modified_time"
              width="200px">
          </el-table-column>
          <el-table-column
              fixed="right"
              label="操作"
              width="200">
            <template slot-scope="scope">

              <!--            加参数：        -->
              <router-link :to="{
              path: '/work/workshop/workshopDetail',
              query: {
              workshopId: scope.row.id,
              workshopName: scope.row.name
              }
            }">
                <!--            <el-button style="padding: 8px" type="text" @click="handleClick({type:!showLine,id:scope.row.workshopId,name:scope.row.workshopName})">查看</el-button>-->
                <el-button style="padding: 8px" type="text">查看</el-button>
              </router-link>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div v-else>
        <el-table
            v-loading="loading"
            id="theSubTable"
            :data="tableLineData"
            highlight-current-row
            stripe
            @sort-change="sortchange"
            style="width: 100%">
          <el-table-column
              label="车间编号"
              prop="workshop_id">
          </el-table-column>
          <el-table-column
              label="产线编号"
              prop="id">
          </el-table-column>
          <el-table-column
              label="产线名称"
              prop="name"
              width="180">
          </el-table-column>
          <el-table-column
              label="设备数量"
              prop="equipNum">
          </el-table-column>
          <el-table-column
              label="工位数量"
              prop="stationNum"
              width="200px">
          </el-table-column>
          <el-table-column
              label="创建日期"
              prop="created_time"
              width="200px">
          </el-table-column>

          <el-table-column
              fixed="right"
              label="操作"
              width="200">
            <template slot-scope="scope">
              <!--              <el-button style="padding: 8px" type="text" @click="handleClick({type:!showLine,id:scope.row.workshopId,name:scope.row.workshopName})">查看</el-button>-->
              <!--            加参数：        -->
              <router-link :to="{
              path: '/work/workshop/workshopDetail',
              query: {
              workshopId: scope.row.workshop_id,
              workshopNmae:'s'
              }}">
                <el-button style="padding: 8px" type="text">查看</el-button>
              </router-link>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <!--      下面是分页效果  -->
      <div class="block" style="padding: 10px">
        <el-pagination
            @size-change="current-change"
            @prev-click="preclick"
            @next-click="nextclick"
            @current-change="curChange"
            :hide-on-single-page=true
            :total="total"
            background
            layout="total,prev, pager, next, jumper"
            :page-size="page.pagesize">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import {request} from "@/network/request";
import {my_request} from "@/network/requests/workshop_request";

export default {
  name: "baseview",
  data() {
    return {
      total:1,
      formInline: {
        category: 'workshop',
        info: ''
      },
      value: 'workshop',

      page: {
        pagesize: 15,
        curpage: 1,
      },
      // sortForm: '车间编号',
      tableData: [],
      tableLineData: [],
      workshopItem: '',


      loading: false,
      //是否展示当前页面， 作为一个表示
      showLine: false,

      path: '',

      rules: {

        info: [
          {required: true, message: ' ', trigger: 'blur'},
          {min: 1, message: '信息不可为空', trigger: 'blur'}
        ],
      }
    }

  },
  created() {
    this.getData()
  },
  activated() {
    this.$router.push(this.path)
  },
  beforeRouteLeave(to, from, next) {
    this.path = this.$route.path;
    next();
  },
  methods: {
    //  翻页  ----------------------------
    preclick(curpage) {
      this.page.curpage = curpage
      this.loading = true
      this.getData()
      this.loading = false

    },
    nextclick(curpage) {
      this.page.curpage = curpage
      this.loading = true
      this.getData()
      this.loading = false


    },
    curChange(curpage) {
      this.page.curpage = curpage
      this.loading = true
      setTimeout(() => {
        this.getData()
        this.loading = false
      }, 1000)
    },

    //页表上方的排序
    sortchange() {
      this.loading = true
      setTimeout(() => {
        this.loading = false
      }, 1000)
    },

    //选择当前页面展示信息分类
    selectChange() {
      this.loading = true
      setTimeout(() => {
        this.loading = false
        this.showLine = !this.showLine
        this.getData()
      }, 1000)
    },

    //搜索方法
    search(formInline) {

      //---------------------------------------开始查寻数据。
      this.$refs.formInline.validate((valid) => {
        if (valid) {

          // 如果通过验证 则开始进行搜索------------
          this.getData({type: 'search', data: this.formInline.info})

        } else {
          return false;
        }
      });


    },

    //已经磨的用了
    handleCommand(command) {
      this.sortForm = command
      this.$message('click on item ' + command);
    },

    //查看每一行数据的 已经ok 了吧。  访问数据在另一个网页进行访问
    handleClick(obj) {
      if (obj.type) {
        this.$router.push({
          path: '/work/workshop/workshopDetail',
          query: {
            workshopId: obj.id,
            workshopName: obj.name
          }
        })
      } else {
        this.$router.push({
          path: '/work/workshop/line/lineDetail',
          query: {
            lineId: obj.id,
            lineName: obj.name
          }
        })
      }

    },

    //新建项目的 网页
    createItem() {
      this.$router.push('/test/baseTest/createPro')
    },


    getData(obj = {type: 'def'}) {

      //先看是哪个类型的 车间 还是 产线：
      if (!this.showLine) {
        //根据 obj的类型  选择不同的查询内容：
        if (obj.type === 'search') {
          //  -----------查询车间内容数据返回。  查询的内容在 obj.data 里面
          this.loading = true
          let req = {
            info: obj.data
          }
          my_request({
            url: 'workshop/searchWorkshop',
            params: req,
            methods: 'get'
          }).then(res => {
            if (res.data.success === true){
              this.$message({
                type:'success',
                message:'查询成功'
              })
              console.log('查询结果',res)
              let s = []
              s.push(res.data.result)
              this.tableData = s;
              this.total = res.data.total;
              this.loading = false
            }else {
              this.$message.error('暂未查询到此记录')
            }

          }).catch(err => {
            this.$message.error('服务器异常')
          })

          this.formInline.info = ''
          this.loading = false
        } else {
          ('2');

          let req = {
            currentPage: this.page.curpage,
            pagesize: this.page.pagesize
          }
          this.loading = true
          my_request({
            //设置一下让他请求 从 ( curpage - 1 ) *  pageSize 开始  一共 pageSize个数据 -------------------------------
            url: 'workshop/workshopItem',
            params: req,
            methods: 'get'
          }).then(res => {
            console.log('凡回家过',res)
            if (res.data.message==='操作成功！'){
              this.tableData = res.data.result.records
              this.total = res.data.total
            }else {
              this.$message.error(res.data.message)
            }

          }).catch(err => {
            this.$message.error('服务器异常')
          })
          this.loading = false
        }
      } else {
        if (obj.type === 'search') {
          if (obj.data.indexOf('+') !== -1 && obj.data.split('+').length === 2 && obj.data.split('+')[0] !== '' && obj.data.split('+')[1] !== '') {
            this.loading = true
            //  -----------查询产线内容数据返回。  查询的内容在 obj.data 里面
            let s = obj.data.split("+")
            let req = {
              Id: s[0],//车间
              info:s[1]//产线
            }
            this.loading = true
            console.log('搜索',req)
            my_request({
              url: 'line/searchLine',
              params: req,
              methods: 'get'
            }).then(res => {
              console.log('产线查询:',res)
              if(res.data.success === true) {
                let s = []
                s.push(res.data.result)
                this.tableLineData = s
                this.total = res.data.total;
                this.$message({
                  type:'success',
                  message:'查询成功'
                })
              }else{
                this.$message.error('查无此记录')
              }

            }).catch(err => {
              this.$message.error('服务器异常')
            })

            this.loading = false

            this.formInline.info = ''
          }else {
           this.$message({
             type:'info',
             message:'请输入正确格式'
           })
          }
        } else {
          let req = {
            currentPage: this.page.curpage,
            pagesize: this.page.pagesize
          };
          this.loading = true
          my_request({
            //设置一下让他请求 从 curpage *  ( pageSize - 1 ) 开始  一共 pageSize个数据 -------------------------------

            url: 'line/searchLineItem',
            params: req,
            methods: 'get'
          }).then(res => {
            console.log('产线新细',res)
            this.tableLineData = res.data.result.records
            this.total = res.data.result.total
          }).catch(err => {
            this.$message.error('服务器异常')
          })
          this.loading = false
        }

      }


    },
  }
}
</script>

<style scoped>

#theBaseMainBox {
  display: flex;
  /*background-color: gray;*/
  height: 100%;
  flex-direction: column;
  width: auto;
}

#theBaseHeader {
  /*background-color: aliceblue;*/
  height: 5%;
  width: 100%;
  display: flex;
  justify-content: space-between;
  /*align-items: center;*/
}

#theBaseMain {
  /*background-color: beige;*/
  height: 100%;

  width: inherit;
  padding: 0 80px;

}

#theLeftButton {
  display: flex;
  align-items: flex-end;
}

.theLeftHeaderInput {
  display: flex;
  height: 40px;
  width: 300px;
  /*justify-content: space-between;*/
}

#theLeftBase {
  width: 45%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  height: auto
}

#theRightBase {
  width: 20%;
  height: 40px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}


#theMainTable th {
  padding: 10px 20px !important;
  /*background-color: aliceblue;*/
  font-size: large;
  /*border: 1px solid red;*/
}

/deep/ #theMainTable td {
  padding: 6px 20px !important;
  /*border: 1px solid blue;*/

}


#theSubTable th {
  padding: 10px 20px !important;
  /*background-color: aliceblue;*/
  font-size: large;
  /*border: 1px solid red;*/
}

/deep/ #theSubTable td {
  padding: 6px 20px !important;
  /*border: 1px solid blue;*/

}


.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
  /*size: ;*/
}
/deep/  td {
  padding:3px !important;
  /*border: 1px solid blue;*/
  height: 6px;
  overflow: hidden;
}

</style>
