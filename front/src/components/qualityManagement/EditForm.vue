<template>
  <div style="width: 80%;margin-left: 10%">
<!--    <i class="el-icon-circle-plus-outline" @click="dialogFormVisible = true"></i>-->
<!--    <el-dialog-->
<!--        title="添加/修改质检结果"-->
<!--        :visible.sync="dialogFormVisible"-->
<!--        @close="clear">-->
      <el-form :model="qualityForm" :rules="rulesForm" ref="dataForm" label-width="100px" size="large">
<!--      质检时间-->
        <el-form-item label="质检时间" prop="checkTime">
            <el-date-picker type="datetime" placeholder="选择日期时间" v-model="qualityForm.checkTime"
                            format="yyyy 年 MM 月 dd 日 HH时 mm分 ss秒"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            :picker-options="pickerBeginDateBefore"
                            style="width: 100%;"></el-date-picker>
        </el-form-item>
<!--      订单数量、样品数量、瑕疵数量-->
        <el-form-item label="订单数量" required>
          <el-col :span="6">
            <el-form-item prop="orderNum">
              <el-input v-model="qualityForm.orderNum" clearable style="width: 100%;"></el-input>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="3">样品数量</el-col>
          <el-col :span="6">
            <el-form-item prop="checkNum">
              <el-input v-model="qualityForm.checkNum" clearable style="width: 100%;"></el-input>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="3">不合格数</el-col>
          <el-col :span="6">
            <el-form-item prop="defectNum">
              <el-input v-model="qualityForm.defectNum" clearable style="width: 100%;"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
<!--        瑕疵种类-->
        <el-form-item label="瑕疵种类" prop="defectType">
          <el-select v-model="qualityForm.defectType" multiple placeholder="请添加瑕疵种类"
                     style="width: 100%">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
<!--        瑕疵代号-->
        <el-form-item label="瑕疵代号" prop="defectCode">
          <el-select
              v-model="qualityForm.defectCode"
              multiple
              filterable
              remote
              reserve-keyword
              placeholder="输入关键词快速查找"
              :remote-method="remoteMethod"
              :loading="loading"
              style="width: 100%">
            <el-option
                v-for="item in options2"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <!--        车间、生产线、工序、工作人员-->
        <el-form-item label="车间" required>
          <el-col :span="10">
            <el-form-item prop="workshop">
              <el-input v-model="qualityForm.workshop" clearable style="width: 100%;"></el-input>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="4">生产线</el-col>
          <el-col :span="10">
            <el-form-item prop="productionLine">
              <el-input v-model="qualityForm.productionLine" clearable style="width: 100%;"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="工序" required>
          <el-col :span="10">
            <el-form-item prop="procedure">
              <el-input v-model="qualityForm.procedure" clearable style="width: 100%;"></el-input>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="4">负责人员</el-col>
          <el-col :span="10">
            <el-form-item prop="staff">
              <el-input v-model="qualityForm.staff" disabled style="width: 100%;"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="onCancel()">重 置</el-button>
        <el-button type="primary" @click="onSubmit()">确 定</el-button>
      </div>
<!--    </el-dialog>-->
  </div>
</template>

<script>

import qualityForm from "@/components/qualityManagement/qualityForm";
import {request} from "@/network/request";

export default {
  name: 'EditForm',
  data() {
    //这里是自定义的表单验证规则，保证订单数量 >= 样品数量 >= 瑕疵数量
    const compareSample = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请填写样品数量'));
      } else if (parseInt(value) > parseInt(this.qualityForm.orderNum) ||
          parseInt(value) < parseInt(this.qualityForm.defectNum)) {
        return callback(new Error('请再次确认样品数量'));
      } else {
        callback()
      }
    };
    const compareDefect = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请填写瑕疵数量'));
      } else if (parseInt(value) > parseInt(this.qualityForm.checkNum) ||
          parseInt(value) > parseInt(this.qualityForm.orderNum)) {
        return callback(new Error('请再次确认瑕疵数量'));
      } else {
        callback()
      }
    };
    return {
      dialogFormVisible: false,
      qualityForm: {
        checkOrder: '',//质检记录id
        checkTime: '',//质检日期时间
        orderNum: '',//订单数量
        checkNum: '',//样品数量
        defectNum: '',//瑕疵数量
        defectType: [],//瑕疵分类
        defectCode: [],//瑕疵代号及名字
        modelNum:'',
        modelType: [],//瑕疵分类
        modelCode: [],//瑕疵代号及名字
        workshop: '',//车间
        productionLine: '',//生产线
        procedure: '',//对应的工序
        staff: '',//负责人员
      },
      options: [{
        value: '安全与监管',
        label: '安全与监管'
      }, {
        value: '材料',
        label: '材料'
      }, {
        value: '清洁度',
        label: '清洁度'
      }, {
        value: '制作',
        label: '制作'
      }, {
        value: '组件和修剪',
        label: '组件和修剪'
      }, {
        value: '洗涤和整理',
        label: '洗涤和整理'
      }, {
        value: '包装检验',
        label: '包装检验'
      }, {
        value: '测量',
        label: '测量'
      }, {
        value: '其他',
        label: '其他'
      },
      ],
      options2: [],
      value: [],
      list: [],
      loading: false,
      states: [],
      formLabelWidth: '120px',
      rulesForm: {
        checkTime: [
          {required: true, message: '请选择时间', trigger: 'blur' },
        ],
        orderNum: [
          {required: true, message: '请填写订单数量', trigger: 'blur' },
        ],
        checkNum: [
          {type: 'number',required: true, validator: compareSample, trigger: 'blur' },
        ],
        defectNum: [
          {type: 'number',required: true, validator: compareDefect, trigger: 'blur' },
        ],
        defectType: [
          {type: 'array',required: true, message: '请至少选择一个瑕疵种类', trigger: 'change' },
        ],
        defectCode: [
          {type: 'array',required: true, message: '请至少选择一个瑕疵代号', trigger: 'change' },
        ],
        workshop: [
          {required: true, message: '请填写车间', trigger: 'blur' },
        ],
        productionLine: [
          {required: true, message: '请填写生产线', trigger: 'blur' },
        ],
        procedure: [
          {required: true, message: '请填写工序', trigger: 'blur' },
        ],
        staff: [
          {required: true, message: '请填写负责人员', trigger: 'blur' },
        ],
    },
      pickerBeginDateBefore:{
        disabledDate(time) {
          return time.getTime() > Date.now();
        }
      },
    }
  },
  mounted() {
    this.loadCode();
    this.qualityForm.staff = this.$store.getters.userinfo.name;
  },
  methods: {
    clear() {
      this.resetDataForm()
    },
    validateForm() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          let i;
          const type = this.qualityForm.defectType;
          let defectType = "";
          for(i = 0; i< type.length; i++)
            defectType += type[i] + ",";
          const code = this.qualityForm.defectCode;
          let defectCode = '';
          for(i = 0; i< code.length; i++)
            defectCode += code[i] + ",";

          request({
            url:'/quality/addResults',
            method:'post',
            data:{
              checkTime: this.qualityForm.checkTime,//质检日期时间
              orderNum: parseInt(this.qualityForm.orderNum),//订单数量
              checkNum: parseInt(this.qualityForm.checkNum),//样品数量
              defectNum: parseInt(this.qualityForm.defectNum),//瑕疵数量
              defectType: defectType,//瑕疵分类
              defectCode: defectCode,//瑕疵代号及名字
              defectRatio: parseInt(this.qualityForm.defectNum)/parseInt(this.qualityForm.checkNum),
              workshop: this.qualityForm.workshop,//车间
              productionLine: this.qualityForm.productionLine,//生产线
              procedure: this.qualityForm.procedure,//对应的工序
              staff: this.qualityForm.staff,//负责人员
            },
          }).then(resp => {
            if (resp && resp.status === 200) {
              // this.dialogFormVisible = false
              this.$emit('onSubmit')
              this.$confirm('录入成功', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'info'
              })
              this.resetDataForm();
            }
          })
        } else {
          return false;
        }
      });
    },
    resetDataForm() {
      this.$refs['dataForm'].clearValidate();
      this.$refs['dataForm'].resetFields();
      this.qualityForm.staff = this.$store.getters.userinfo.name;
    },
    onSubmit() {
      this.validateForm();
      //this.resetDataForm();
    },
    onCancel(){
      // this.dialogFormVisible = false;
      this.resetDataForm();
    },
    //瑕疵代号远程搜时用到
    remoteMethod(query) {
      if (query !== '') {
        this.loading = true;
        setTimeout(() => {
          this.loading = false;
          this.options2 = this.list.filter(item => {
            return item.label.toLowerCase()
                .indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.options2 = [];
      }
    },
    loadCode(){
      request({
        url:'/defect/typeAndCode',
        method:'get',
      }).then(resp =>{
          if(resp && resp.status === 200){
            let data = resp.data;
            this.list = data.map(item => {
              return { value: `${item.defectCode}`, label: `${item.defectCode}:${item.defectType}` };
            });
          }
        }).catch(err =>{
        this.$message.error('网络出错，请稍后再试');
      });
    },
    getCurrentTime(){
      let yy = new Date().getFullYear();
      let mm = (new Date().getMonth()+1)<10 ? '0'+(new Date().getMonth()+1) : new Date().getMonth()+1;
      let dd = new Date().getDate()<10 ? '0'+new Date().getDate() : new Date().getDate();
      let hh = new Date().getHours();
      let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
      let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
      const currentTime = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss;
      this.timeDefault = currentTime;
    }
  }
}
</script>

<style scoped>
.el-icon-circle-plus-outline {
  margin: 50px 0 0 20px;
  font-size: 100px;
  float: left;
  cursor: pointer;
}
</style>


