package com.example.mes.dataAnalysis.Vo;

public class MaterialStock {

    String material_id;
    String name;
    String size;
    String color;
    int count;

    public MaterialStock() {
    }

    public MaterialStock(String material_id, String name, String size, String color, int count) {
        this.material_id = material_id;
        this.name = name;
        this.size = size;
        this.color = color;
        this.count = count;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "MaterialStock{" +
                "material_id='" + material_id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", count=" + count +
                '}';
    }
}
