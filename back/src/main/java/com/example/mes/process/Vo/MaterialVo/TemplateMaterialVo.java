package com.example.mes.process.Vo.MaterialVo;

import java.util.HashMap;

public class TemplateMaterialVo {
    int material_id;

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public HashMap<String, String> getAttribute() {
        return attribute;
    }

    public void setAttribute(HashMap<String, String> attribute) {
        this.attribute = attribute;
    }

    HashMap<String,String> attribute;
}
