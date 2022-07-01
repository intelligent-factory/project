package com.example.mes.process.Vo.EquipmentVo;

import java.util.HashMap;

public class TemplateEquipmentVo {
    int equipment_id;

    String name;

    HashMap<String,String> attribute;

    public int getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getAttribute() {
        return attribute;
    }

    public void setAttribute(HashMap<String, String> attribute) {
        this.attribute = attribute;
    }
}
