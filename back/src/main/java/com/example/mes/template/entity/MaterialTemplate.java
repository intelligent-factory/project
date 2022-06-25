package com.example.mes.template.entity;

import java.io.Serializable;

//表中一条记录
public class MaterialTemplate implements Serializable {
    int material_id;
    String name;
    String attribute;
    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }



    public int getMaterialCode() {
        return  material_id;
    }


}
