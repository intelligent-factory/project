package com.example.mes.template.vo;

import java.util.ArrayList;

//对应前端返回的一个定义好的模板
public class MaterialTemplateVo {
    int material_id;

    String name;

    ArrayList<String> attribute;

    int company_id;

    public MaterialTemplateVo(int material_id, String name, ArrayList<String> attribute, int company_id) {
        this.material_id = material_id;
        this.name = name;
        this.attribute = attribute;
        this.company_id = company_id;
    }

    public MaterialTemplateVo() {

    }

    @Override
    public String toString() {
        return "MaterialTemplateVo{" +
                "material_id=" + material_id +
                ", name='" + name + '\'' +
                ", attribute=" + attribute +
                ", company_id=" + company_id +
                '}';
    }

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

    public ArrayList<String> getAttribute() {
        return attribute;
    }

    public void setAttribute(ArrayList<String> attribute) {
        this.attribute = attribute;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
