package com.example.mes.process.Vo.EquipmentVo;

import java.sql.Timestamp;

public class DeleteEquipmentVo {

    String equipment_id;
    String operator_id;
    Timestamp modified_time;

    public DeleteEquipmentVo() {
    }

    public DeleteEquipmentVo(String equipment_id, String operator_id) {
        this.equipment_id = equipment_id;
        this.operator_id = operator_id;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public Timestamp getModified_time() {
        return modified_time;
    }

    public void setModified_time(Timestamp modified_time) {
        this.modified_time = modified_time;
    }

    @Override
    public String toString() {
        return "DeleteEquipmentVo{" +
                "equipment_id='" + equipment_id + '\'' +
                ", operator_id='" + operator_id + '\'' +
                ", modified_time=" + modified_time +
                '}';
    }
}
