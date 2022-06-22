package com.example.mes.process.Service.Impl;

import com.example.mes.process.Mapper.EquipmentMapper;
import com.example.mes.process.Service.IEquipmentService;
import com.example.mes.process.Vo.EquipmentVo.DeleteEquipmentVo;
import com.example.mes.process.Vo.EquipmentVo.InsertEquipmentVo;
import com.example.mes.process.Vo.EquipmentVo.QueryEquipmentVo;
import com.example.mes.process.Vo.EquipmentVo.UpdateEquipmentVo;
import com.example.mes.process.Vo.PageVo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService implements IEquipmentService {

    @Autowired(required = false)
    EquipmentMapper mapper;

    @Override
    public List<QueryEquipmentVo> getEquipments(PageVo pageVo) {
        try {
            return mapper.getEquipments(pageVo);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("controller:获取设备信息列表失败！");
            return null;
        }

    }

    @Override
    public QueryEquipmentVo getEquipmentByID(String equipment_id) {
        try {
            return mapper.getEquipmentByID(equipment_id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("controller:根据设备编号获得设备信息失败！");
            return null;
        }
    }

    @Override
    public String addEquipment(InsertEquipmentVo insertEquipmentVo) {
        try {
            //用于检验用户权限
            String operator_id = insertEquipmentVo.getOperator_id();
            //return "无权限";
            if (mapper.checkDuplicate(insertEquipmentVo.getName(),insertEquipmentVo.getPurpose())!=0){
                return "添加失败";
            }
            return mapper.addEquipment(insertEquipmentVo)?"添加成功":"添加失败";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("controller:增加设备失败！");
            return "添加失败";
        }
    }

    @Override
    public String deleteEquipmentByID(DeleteEquipmentVo deleteEquipmentVo) {
        try {
            //用于检验用户权限
            String operator_id = deleteEquipmentVo.getOperator_id();
            //return "无权限";
            return mapper.deleteEquipmentByID(deleteEquipmentVo)?"删除成功":"删除失败";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("controller:删除设备失败！");
            return "删除失败";
        }
    }

    @Override
    public String updateEquipmentByID(UpdateEquipmentVo updateEquipmentVo) {
        try {
            //用于检验用户权限
            String operator_id = updateEquipmentVo.getOperator_id();
            //return "无权限";
            return mapper.updateEquipmentByID(updateEquipmentVo)?"更新成功":"更新失败";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("controller:更新设备失败！");
            return "更新失败";
        }
    }

    @Override
    public int getCount(){
        return mapper.getCount();
    }

    @Override
    public int getIndex() {
        return mapper.getIndex();
    }
}
