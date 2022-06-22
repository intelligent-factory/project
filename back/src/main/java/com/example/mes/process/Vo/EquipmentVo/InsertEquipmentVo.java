package com.example.mes.process.Vo.EquipmentVo;

import java.sql.Timestamp;

public class InsertEquipmentVo {

    String equipment_id;
    String name;
    String purpose;
    String comments;
    String status;
    String operator_id;
    Timestamp created_time;

    public InsertEquipmentVo() {
    }

    public InsertEquipmentVo(String name, String purpose, String comments, String status, String operator_id) {
        this.name = name;
        this.purpose = purpose;
        this.comments = comments;
        this.status = status;
        this.operator_id = operator_id;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    @Override
    public String toString() {
        return "InsertEquipmentVo{" +
                "equipment_id='" + equipment_id + '\'' +
                ", name='" + name + '\'' +
                ", purpose='" + purpose + '\'' +
                ", comments='" + comments + '\'' +
                ", status='" + status + '\'' +
                ", operator_id='" + operator_id + '\'' +
                ", created_time=" + created_time +
                '}';
    }
}
