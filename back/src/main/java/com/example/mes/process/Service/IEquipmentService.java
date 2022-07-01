package com.example.mes.process.Service;

import com.example.mes.process.Vo.EquipmentVo.*;
import com.example.mes.process.Vo.PageVo.PageVo;

import java.util.List;

public interface IEquipmentService {

    List<TemplateEquipmentVo> getTemplateEquipments(String company_id);

    List<QueryEquipmentVo> getEquipments(PageVo pageVo);

    QueryEquipmentVo getEquipmentByID(String equipment_id);

    String addEquipment(InsertEquipmentVo insertEquipmentVo);

    String deleteEquipmentByID(DeleteEquipmentVo deleteEquipmentVo);

    String updateEquipmentByID(UpdateEquipmentVo updateEquipmentVo);

    int getCount();

    int getIndex();

}
