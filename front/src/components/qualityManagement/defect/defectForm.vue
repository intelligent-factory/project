
<!--
表单：用于增改瑕疵
-->

<template>
  <div>
    <i class="el-icon-circle-plus-outline" @click="dialogFormVisible = true"></i>
    <el-dialog
        title="添加/修改瑕疵"
        :visible.sync="dialogFormVisible"
        @close="clear">
      <el-form :model="defectForm" :rules="rules" ref="dataForm" label-width="100px" size="large">
        <el-form-item label="瑕疵分类" prop="classification">
            <el-select v-model="defectForm.classification" filterable placeholder="请选择" style="width: 100%;">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="瑕疵代号" prop="defectCode">
          <el-input v-model="defectForm.defectCode" style="width: 100%;"></el-input>
        </el-form-item>
        <el-form-item label="瑕疵描述" prop="defectType">
          <el-input v-model="defectForm.defectType" style="width: 100%;"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="onCancel()">取 消</el-button>
        <el-button type="primary" @click="onSubmit()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import {request} from "@/network/request";

export default {
  name: 'defectForm',
  data() {
    return {
      dialogFormVisible: false,
      defectForm: {
        classification: '',//瑕疵分类
        defectCode: '',//瑕疵代号
        defectType: '',//瑕疵名称
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
      value: '',
      formLabelWidth: '120px',
      rules: {
        classification: [
          {required: true, message: '请选择瑕疵分类', trigger: 'change' },
        ],
        defectCode: [
          {required: true, message: '请输入瑕疵代号', trigger: 'blur' },
        ],
        defectType: [
          {required: true, message: '请输入瑕疵名称', trigger: 'blur' },
        ],
      },
    }
  },
  methods: {
    clear() {
      this.resetDataForm()
    },
    validateForm() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          request({
            url: '/defect/add',
            method:'post',
            data:{
              classification: this.defectForm.classification,
              defectCode: this.defectForm.defectCode,
              defectType: this.defectForm.defectType,
            }
          }).then(resp => {
            if (resp && resp.status === 200) {
              this.dialogFormVisible = false
              this.$emit('onSubmit')
            }
          })
        } else {
          this.$message.error('网络出错，请稍后再试');
          return false;
        }
      });
    },
    resetDataForm() {
      this.$refs['dataForm'].clearValidate();
      this.$refs['dataForm'].resetFields();
    },
    onSubmit() {
      this.validateForm();
    },
    onCancel(){
      this.dialogFormVisible = false;
      this.resetDataForm();
    },
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


