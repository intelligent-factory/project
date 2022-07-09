<template>
  <div id="mainDiv">
    <div id="header">
        <div class="headerRight">
          <el-button @click="submitDialog = true" type="primary" plain>&nbsp;&nbsp;&nbsp;删&nbsp;&nbsp;除&nbsp;&nbsp;&nbsp;</el-button>
          <el-dialog
              title="提示"
              :visible.sync="submitDialog"
              width="30%"
              :before-close="handleClose">
            <span>是否删除该车间</span>
            <span slot="footer" class="dialog-footer">
            <el-button @click="submitDialog = false">取 消</el-button>
            <el-button type="primary" @click="delWrokshop">确 定</el-button>
          </span>
          </el-dialog>

        </div>
    </div>
    <div class="subMain">
      <div class="item">
        <span>车间编号:</span>
        <div class="inputBox">
          <el-input
              :placeholder="workshopInfo.id"
              v-model="workshop.workshopId"
              clearable>
          </el-input>
        </div>

      </div>
      <div class="item">
        <span>车间名称:</span>
        <div class="inputBox">
          <el-input
              :placeholder="workshopInfo.name"
              v-model="workshop.workshopName"
              clearable>
          </el-input>
        </div>

      </div>
      <div class="item">
        <span>工厂名称:</span>
        <div class="inputBox">
          <el-input
              :placeholder="workshopInfo.factory_name"
              v-model="workshop.factory_name"
              clearable>
          </el-input>
        </div>
        <el-button @click="submitToUpdate()" plain>修   改</el-button>
        <el-button @click="addDialog = true" plain>增加产线</el-button>

        <el-dialog
            title="增加产线"
            :visible.sync="addDialog"
            width="25%"
            :before-close="handleClose">

          <el-form :model="newLineName" ref="newLineName" label-width="100px" class="demo-dynamic">
            <el-form-item
                prop="newLineId"
                label="产线编号"
                style="width: 80%"
                size="100px"
                :rules="[
                            { required: true, message: '请输入产线编号', trigger: 'blur' },
                          ]">
              <el-input v-model="newLineName.newLineId"></el-input>
            </el-form-item>
            <el-form-item
                prop="newLineName"
                label="产线名称"
                style="width: 80%"
                size="100px"
                :rules="[
                            { required: true, message: '请输入产线名称', trigger: 'blur' },
                          ]">
              <el-input v-model="newLineName.newLineName"></el-input>
            </el-form-item>
            <el-form-item
                prop="newLineRouting"
                label="产线工艺"
                style="width: 80%"
                size="100px"
                :rules="[
                            { required: true, message: '请输入产线工艺', trigger: 'blur' },
                          ]">
              <el-input v-model="newLineName.newLineRouting"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('newLineName')">提交</el-button>
              <el-button @click="addDialog = false">取 消</el-button>
            </el-form-item>
          </el-form>

<!--          --------------------------------------------------------------------------------------------------->
        </el-dialog>
      </div>
    </div>
    <div style="width: 100%;height:40px;display:flex;background-color: whitesmoke"></div>


<!--  下方产线信息  -->
    <div class="subMainA" id="main">
      <div class="itemA" v-for="(line,index) in workshopInfo.lines">
        <el-card shadow="hover"  class="box-card">
          <div slot="header" class="clearfix">
            <el-tooltip placement="top">
              <div slot="content">{{'产线工艺:'+ line.routing}}</div>
              <el-button style="width: 10%" @click="editLineName[index].flag = true" type="text">{{line.name}}</el-button>

<!--              <span>{{"产线名称:"+ line.name}}</span>-->
            </el-tooltip>
<!--            <span style="font-size: smaller">产线编号:{{line.id}}产线名称</span>-->
<!--            <el-button style="width: 10%" @click="editLineName[index].flag = true" type="text">{{line.name}}</el-button>-->
<!--            -------------------------------------------================================================================------->
            <el-dialog
                title="提示"
                :visible.sync="editLineName[index].flag"
                width="30%"
                :before-close="handleClose">

              <el-form :model="updateLineName" ref="updateLineName" label-width="100px" class="demo-ruleForm">
                <el-form-item
                    label="产线名称"
                    prop="newLineName"
                    :rules="[
                              { required: true, message: '产线名称不能为空'},
                            ]">
                  <el-input type="age" v-model.number="updateLineName.newLineName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item
                    label="产线工艺"
                    prop="newLineRouting"
                    :rules="[
                              { required: true, message: '产线工艺不能为空'},
                            ]">
                  <el-input type="age" v-model.number="updateLineName.newLineRouting" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updatetheLineName('updateLineName',workshopInfo.id,line.id,index)">修改</el-button>
                </el-form-item>
              </el-form>

            </el-dialog>
<!--            ---------------------------------------------------------------------------------------------------------------------->
            <!--            删除产线  增加工位-->
            <el-button  v-loading.fullscreen.lock="fullscreenLoading" @click="delLine(workshopInfo.id,line.id)"  style="float: right; padding: 3px 3px;margin-left: 6px"  icon="el-icon-close" circle></el-button>
            <el-button  v-loading.fullscreen.lock="fullscreenLoading" @click="shouwAddStaDialog(index)"  style="float: right; padding: 3px 3px"  icon="el-icon-plus" circle></el-button>
            <el-dialog
                title="增加工位"
                :visible.sync="addStationDialogs[index].flag"
                width="25%"
                :before-close="handleClose">
              <el-form :model="addStationInfo" ref="addStationInfo" label-width="100px" class="demo-ruleForm">
                <el-form-item
                    label="工位编号"
                    prop="stationId"
                    :rules="[
                              { required: true, message: '工位编号不能为空'},
                            ]">
                  <el-input v-model="addStationInfo.stationId" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item
                    label="工位位序"
                    prop="staOrd"
                    :rules="[
                              { required: true, message: '工位位序不能为空'},
                            ]">
                  <el-input v-model.number="addStationInfo.staOrd" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item
                    label="工人编号"
                    prop="user"
                    :rules="[
                              { required: true, message: '工人编号不能为空'},
                            ]">
                  <el-input v-model="addStationInfo.user" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item
                    label="设备编号"
                    prop="staEqu"
                    :rules="[
                              { required: true, message: '设备编号不能为空'},
                            ]">
                  <el-input v-model="addStationInfo.staEqu" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button @click="addAStation('addStationInfo',workshopInfo.id,line.id,index)">提交</el-button>
<!--                  <button @click="add221AStation('addStationInfo',workshopInfo.id,line.id,index)">提交</button>-->
<!--                  <el-button type="primary" @click="addStation(1,1,1,1)">点击</el-button>-->
                  <el-button @click="notShowAddStatinDialog(index)">取 消</el-button>
                </el-form-item>
              </el-form>

<!------------------------------------------------->
<!--              <span slot="footer" class="dialog-footer">-->
<!---->
<!--              <el-button type="primary" @click="addStation(workshopInfo.id,line.lineId,index)">确 定</el-button>-->
<!--            </span>-->
            </el-dialog>
          </div>

<!--          没条产线的信息 -->
          <div style="overflow: auto;height: 300px" >
          <div style="display: flex;justify-content: space-around;width: inherit" v-for="(o,index1) in line.stations"  class="text item" >
            <div>

              <el-tooltip placement="top">
                <div slot="content">{{'工位编号 : '+ o.id+' 工位位序 : '+o.stationOrder}}
                  <br>{{'设备编号 : ' + o.equip_id + ' 工人编号 : ' + o.user }}</div>
                <el-button style="width: 10%" @click="showEditStation(index,index1) " type="text">{{o.id+'-'+'工位' + index1}}</el-button>

              </el-tooltip>

            </div>
            <div style="display: flex;align-self:end">
              <!--                  :visible.sync="stationInfo[index][index1].flag"-->
              <!--                  :visible.sync="tryflag"-->


                            <el-dialog
                  title="修改信息"
                  :visible.sync="tryflag[index][index1].flag"
                  width="25%"
                  :before-close="handleClose">
<!--                -------------------------------------------------------->
                <el-form :model="newStation" ref="newStation" :rules="rules"  label-width="100px" class="demo-dynamic">
                  <div style="width: inherit">
                    <el-form-item
                        :label="'工位位序' "
                        style="width:80%"
                        size="100px"
                        prop="staOrder"
                        :rules="[
                            {required: true, message: '工位位序不可为空', trigger: 'blur', },
                            { type: 'number',message: '工位位序必须为数字', trigger: 'blur' },
                            { type: 'number',min:1, max:line.stationNum, message:'工位必须小于'+ line.stationNum}
                          ]">
                      <el-input style="width: 82%" v-model.number="newStation.staOrder" ></el-input>
                    </el-form-item>
                    <el-form-item
                        :label="'工位编号' "
                        style="width:80%"
                        prop="id"
                        :rules="{
                            required: true, message: '设备编号不可为空', trigger: 'blur'
                          }">
                      <el-input style="width: 82%" v-model="newStation.id"></el-input>
                    </el-form-item>
                    <el-form-item
                        :label="'设备编号' "
                        style="width:80%"
                        prop="staEqu"
                        :rules="{
                            required: true, message: '设备编号不可为空', trigger: 'blur'
                          }">
                      <el-input style="width: 82%" v-model="newStation.staEqu"></el-input>
                    </el-form-item>
                    <el-form-item
                        :label="'工人编号' "
                        style="width:80%"
                        prop="user"
                        :rules="{
                            required: true, message: '工人编号不可为空', trigger: 'blur'
                          }">
                      <el-input style="width: 82%" v-model="newStation.user"></el-input>
                    </el-form-item>
                  </div>
                  <el-form-item>
                    <el-button type="primary" @click="updateFormAddSta('newStation',workshopInfo.id,line.id,o.id,index,index1)">提交</el-button>
                  </el-form-item>
                </el-form>
<!--                --------------------------------------------------------->
              </el-dialog>

              <el-button  v-loading.fullscreen.lock="fullscreenLoading" size="mini" @click="delStation(workshopInfo.id,line.id,o.id)" icon="el-icon-delete" type="text" circle></el-button>
            </div>
          </div>
          </div>
        </el-card>
      </div>
    </div>




  </div>
</template>

<script>
import {request} from "@/network/request";
import {my_request} from "@/network/requests/workshop_request";
import {my_request_post} from "@/network/requests/my_request_post";

export default {

  data() {
    return {
      //用来存储传递的数据
      transferId: '',
      transferName: '',

      // 增加产线的信息
      linOrder: '',
      linName: '',

      submitDialog: false,
      //增加工位的信息
      staOrder: '',
      staEqu: '',
      staEquName: '',
      addStationInfo: {
        stationId: '',
        staOrd: '',
        user: '',
        staEqu: '',
      },

      newStation: {
        staOrder: '',
        user:'',
        staEqu: '',
        id:'',
        staName: '',
        staEquName: '',
      },

      updateLineName: {newLineName: '',newLineRouting:''},
      newLineOrder: '',
      newLineName: {
        newLineName: '',
        newLineId: '',
        newLineRouting:''
      },

      showDialog: false,
      editLineName: [],
      isDisabled: true,


      //展示的主要信息
      workshopInfo: '',
      dialogFormVisible: false,
      lineName: '',
      formLabelWidth: '80px',
      fullscreenLoading: false,

      //  增加产线 内容是否展示
      addStationDialog: false,
      addStationDialogs: [],
      addStationfiags: 0,
      //没调产线内部的信息。二维数组 【第几条产线。第几个工位】
      stationInfo: [],
      tryflag: [],
      showEditDialog: false,
      addDialog: false,

      //------------------
      nowShopworkId: "",
      workshop: {
        workshopId: '',
        workshopName: '',
        factory_name: '',
      },
      rules: {}
    }


  },
  name: "workshopDetail",

  // activated() {
  //
  //   // ---------------------------  要根据这个 查询对应仓库的信息
  //   this.nowShopworkId = this.$route.query.workshopId
  //   this.getData()
  // },
  created() {
    this.nowShopworkId = this.$route.query.workshopId
    this.getData()
  },
  updated() {

  },

  methods: {
    getData() {
      // 获取当前传送来的信息的数据，
      let req = {
        workshopId: this.nowShopworkId
      }
      console.log('...',req)
      // my_request({
      my_request({   // -------------------------暂时代替上面
        url: 'workshop/mainInfo',
        method: 'get',
        params: req
      }).then(res => {
        console.log('getData:',res)
        if(res.data.success===true){
          this.workshopInfo = res.data.result
        }else {
          this.$message.error('服务器异常')

        }
        console.log('返回的结果',res)
        // 暂时 设置为10  这里应该是产线总数的
        // this.addStationfiags = this.workshopInfo.lineNum;

      }).catch(err => {
        this.$message.error('服务器异常')


      })
      this.addStationfiags = 10;
      // 这里的10  应该是  this.workshop.lineNum   产线的总数
      for (let i = 0; i < 10; i++) {
        this.addStationDialogs.push({flag: false});
        this.editLineName.push({flag: false})
      }
      // 产线的总数
      for (let i = 0; i < 20; i++) {
        this.stationInfo.push([])
        this. tryflag.push([])

        //每条产线上面的工位总数。
        for (let j = 0; j < 20; j++) {
          this.stationInfo[i].push({flag: false})
          this.tryflag[i].push({flag: false})
        }
      }

    },

    //删除车间
    delWrokshop() {
      let req = {
        workshopId: this.nowShopworkId
      }
      request({
        url: 'workshop/delete',
        params: req,
        method: 'get'
      }).then(res => {
        console.log(res,'删除')
        if (res.data.success === true) {
          this.$message({
            type: 'success',
            message: '申请成功'
          })
          this.$router.go(-1)
        } else {
          this.$message({
            type: 'error',
            message: '申请失败'
          })
          this.submitDialog = false
        }
      }).catch(err => {
        this.$message.error("服务器异常")
        this.submitDialog = false
      })


    },
    //删除产线ok
    delLine(workshopId, lineId) {

      //  删除 对应车间的 对应 路线
      //  再次返回 对应车间编号的所有信息
      this.fullscreenLoading = true;
      let req = {

        // workshopId:workshopId,
        // lineId:lineId

        workshopId: workshopId,
        lineId: lineId
      };
      console.log('删除产线',req)
      my_request({
        // url: 'line/details',
        url: 'line/delete',
        params: req,
        method: 'get',

      }).then(res => {
        this.fullscreenLoading = false   // 这个 我不确定放在那里
        // -----------------------------------这里应重新渲染页面
        if(res.data.success===true){
          this.$message({
            type:'success',
            message:'申请成功'
          })
        }else {
          this.$message.error('申请失败')
        }
        console.log('删除产线::::::',res)

      }).catch(err => {
        this.fullscreenLoading = false   // 这个 我不确定放在那里
        this.$message.error('服务器异常')

      })
    },
    //增加ok
    addLine(workshopId, newLineId, newLineName,routing) {

      this.fullscreenLoading = true;
      this.addDialog = false
      let req = {
        workshopId: workshopId,
        // 创建的工位
        lineName: newLineName,
        lineId: newLineId,
        routing:routing,
      }
      my_request({
        url: 'line/create',
        params: req,
        method: 'get',
      }).then(res => {
        console.log('增加产线：',res)
        this.fullscreenLoading = false   // 这个 我不确定放在那里
        // -----------------------------------这里应重新渲染页面
        this.$message({
          message: '申请成功'
        })
      }).catch(err => {
        this.fullscreenLoading = false   // 这个 我不确定放在那里
        this.$message.error('申请失败')

      })
    },

    shouwAddStaDialog(index) {
      this.addStationDialogs[index].flag = true
    },
    notShowAddStatinDialog(index) {
      this.addStationDialogs[index].flag = false
    },
    updatetheLineName(formName, workshopId, lineId, index) {
      this.$refs[formName][index].validate((valid) => {
        if (valid) {
          let req = {
            workshopId: workshopId,
            lineId: lineId,
            newName: this.updateLineName.newLineName,
            routing : this.updateLineName.newLineRouting
          }
          console.log('修改产线名：',req)
          my_request({
            url: 'line/update',
            method: 'get',
            params: req
          }).then(res => {

            console.log('修改产线名：',res)
            if(res.data.success === true){
              this.$message({
                type:'success',
                message:'申请成功'
              })
            }else {
              this.$message.error('申请失败')
            }

          }).catch(err => {

            this.$message.error('服务器异常')
          })

        } else {
          return false;
        }
      });
      this.editLineName[index].flag = false
    },
    // 不行，
    addAStation(formName, workshopId, lineId, index) {
      this.$refs[formName][0].validate((valid) => {
        if (valid) {
          this.notShowAddStatinDialog(index)
          this.fullscreenLoading = true;
          this.addStationDialog = false;
          let req = {
            workshopId: workshopId,
            lineId: lineId,
            // 创建的工位
            // staOrd: this.staOrd,
            // staEqu: this.staEqu,
            stationId: this.addStationInfo.stationId,
            staOrd: this.addStationInfo.staOrd,
            user: this.addStationInfo.user,
            staEqu: this.addStationInfo.staEqu
          };
          request({
            url: 'station/create',
            params: req,
            method: 'get',
          }).then(res => {
            this.fullscreenLoading = false   // 这个 我不确定放在那里
            this.workshopInfo = res.deta
            // -----------------------------------这里应重新渲染页面
            this.$message({
              message: '申请成功'
            })
          }).catch(err => {
            this.fullscreenLoading = false   // 这个 我不确定放在那里
            this.$message.error('申请失败')
          })

          this.fullscreenLoading = false
        } else {
          return false;
        }
      });
    },


    //报错
    delStation(workshopId, lineId, staId) {
      this.fullscreenLoading = true;
      let req = {

        workshopId: workshopId,
        lineId: lineId,
        stationId: staId
      }
      request({
        url: 'station/delete',
        params: req,
        method: 'get',
      }).then(res => {
        this.fullscreenLoading = false   // 这个 我不确定放在那里
        // 需要返回数据
        if(res.data.success===true) {
          // -----------------------------------这里应重新渲染页面
          this.$message({
            message: '申请成功'
          })
        }else {
          this.$message.error('申请失败')
        }
      }).catch(err => {
        this.fullscreenLoading = false   // 这个 我不确定放在那里
        this.$message.error('申请失败')
      })

    },

    showEditStation(row, column) {
      this.stationInfo[row][column].flag = true
      this.tryflag[row][column].flag = true
    },
    notShowEditStation(row, column) {
      this.tryflag[row][column].flag = false

    },

    editStation(workshopId, lineId, staId, row, column) {
      this.notShowEditStation(row, column)
      this.fullscreenLoading = true;
      this.fullscreenLoading = true;
      this.showEditDialog = false;
      let req = {
        //传送过去的信息
        //定位
        workshopId: workshopId,
        lineId: lineId,
        stationId: staId,
        //传送的数据

        staOrd:this.newStation.staOrder,
        user: this.newStation.user,
        staEqu:this.newStation.staEqu,
        id:this.newStation.id

      }
      my_request({
        url: 'station/update',
        params: req,
        method: 'get',
      }).then(res => {
        this.fullscreenLoading = false   // 这个 我不确定放在那里
        // 需要返回数据

        // -----------------------------------这里应重新渲染页面
        this.$message({
          message: '申请成功'
        })

      }).catch(err => {
        this.fullscreenLoading = false   // 这个 我不确定放在那里
        this.$message.error('服务器异常')
      })

    },

    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
    },
    //  ------------------------------------

    submitToUpdate() {

      let req = {
        workshopId: this.workshopInfo.id,
        newWorkshopId: this.workshop.workshopId,
        newWorkshopName: this.workshop.workshopName,
        newFactory: this.workshop.factory_name
      }
      console.log('reqqq,',req)
      my_request({
        url: 'workshop/update',
        params: req,
        method: 'get'
      }).then(res => {
        if(res.data.success===true) {
          this.$message({
            type: 'success',
            message: '申请成功'
          })
        }else {
          this.$message.error('申请失败')
        }
        // this.getData()
        // this.nowShopworkId = req.workshopId
        console.log('修改：',res)
      }).catch(err => {
        this.$message.error('服务器异常')

      })

    },

    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          this.addLine(this.nowShopworkId, this.newLineName.newLineId, this.newLineName.newLineName,this.newLineName.newLineRouting)
        } else {
          this.$message.error('提交失败')
          return false;
        }
      });
    },

    updateFormAddSta(formName, id, lineId, stationId, index, index1) {
      this.$refs[formName][0].validate((valid) => {
        if (valid) {
          this.editStation(id, lineId, stationId, index, index1)
          this.$refs[formName][index].resetFields();


        } else {
          this.$message.error('提交失败')
          return false;
        }

      });
    }


  }

}
</script>

<style scoped>
#header {
  height: 50px;
  width: inherit;
  /*background-color: blanchedalmond;*/
  display: flex;
  flex-direction: row-reverse;
}

#mainDiv {
  display: flex;
  /*background-color: #42b983;*/
  width: inherit;
  padding: 0 80px;
  flex-direction: column;

}

.subMain {
  display: flex;
  width: 100%;
  height: auto;
  flex-direction: column;
  margin: 9px;
}
.subMainA {
  display: flex;
  width: 1500px;
  height: 600px;
  margin: 9px;
  justify-content: flex-start;
  flex-wrap: wrap;
  overflow: auto;
}

#main {
  display: flex;
  width: 1500px;
  height: 580px;
  margin: 9px;
  justify-content: flex-start;
  overflow-x: visible;
  flex-wrap-y: wrap;
}

.headerRight {
  width: 15%;
  margin: 10px 50px 0 0;
  display: flex;
  justify-content: flex-start;
  /*align-self: center;*/
  align-items: center;
}

.buttonDiv {
  width: 28%;
  margin-bottom: 20px;
  padding-left: 15px;
  display: flex;
  justify-content: space-between;
  /*align-self: center;*/
  /*align-items: center;*/
}

.lineItem {
  display: flex;
  align-items: flex-start;
  height: 40px !important;
  width: 20px;
  margin: 10px 0;
  padding-left: 22px;
  padding-bottom: 10px;
  border-bottom: solid 1px whitesmoke;
}

.lineItemdetail {
  display: flex;
  /*background-color: blanchedalmond;*/
  width: 500px;
  height: 100%;
  align-items: center;
}

.item {
  display: flex;
  /*background-color: whitesmoke;*/
  margin: 20px 20px;
  width: 800px;
  height: 20px;
  align-items: center;
}
.itemA{
  display: flex;
  background-color: lightskyblue;
  margin: 10px 10px;
  width: 18%;
  height: 400px;
  /*width: 100px;*/
  /*height: 50px;*/
  justify-content: flex-start;
}

.inputBox {
  display: flex;
  width: 300px;
  padding: 0 20px;
}

.lineItem {
  /*background-color: #FFFFFF;*/
  width: 100%;
  height: 100px;
}

.incre_or_delete {
  /*background-color: #ff7300;*/
  display: flex;
  justify-content: flex-end;
  width: 100px;
}
.elinput {
  flex-direction: row;
  align-self: flex-start;
  width: 300px;
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
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
  /*size: ;*/
}



/*  卡片  */
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 480px;
}

</style>