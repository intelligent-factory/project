package com.example.mes.process.Service;

import com.example.mes.process.Vo.EquipmentVo.DeleteEquipmentVo;
import com.example.mes.process.Vo.EquipmentVo.InsertEquipmentVo;
import com.example.mes.process.Vo.EquipmentVo.QueryEquipmentVo;
import com.example.mes.process.Vo.EquipmentVo.UpdateEquipmentVo;
import com.example.mes.process.Vo.PageVo.PageVo;

import java.util.List;

public interface IEquipmentService {

    List<QueryEquipmentVo> getEquipments(PageVo pageVo);

    QueryEquipmentVo getEquipmentByID(String equipment_id);

    String addEquipment(InsertEquipmentVo insertEquipmentVo);

    String deleteEquipmentByID(DeleteEquipmentVo deleteEquipmentVo);

    String updateEquipmentByID(UpdateEquipmentVo updateEquipmentVo);

    int getCount();

    int getIndex();

}
