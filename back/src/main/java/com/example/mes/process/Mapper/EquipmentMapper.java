package com.example.mes.process.Mapper;

import com.example.mes.process.Vo.EquipmentVo.DeleteEquipmentVo;
import com.example.mes.process.Vo.EquipmentVo.InsertEquipmentVo;
import com.example.mes.process.Vo.EquipmentVo.QueryEquipmentVo;
import com.example.mes.process.Vo.EquipmentVo.UpdateEquipmentVo;
import com.example.mes.process.Vo.PageVo.PageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EquipmentMapper {

    List<QueryEquipmentVo> getEquipments(@Param("pageVo") PageVo pageVo);

    QueryEquipmentVo getEquipmentByID(@Param("equipment_id") String equipment_id);

    boolean addEquipment(@Param("insertEquipmentVo") InsertEquipmentVo insertEquipmentVo);

    boolean deleteEquipmentByID(@Param("deleteEquipmentVo") DeleteEquipmentVo deleteEquipmentVo);

    boolean updateEquipmentByID(@Param("updateEquipmentVo") UpdateEquipmentVo updateEquipmentVo);

    int getCount();

    int checkDuplicate(@Param("name") String name,@Param("purpose") String purpose);

    int getIndex();
}
