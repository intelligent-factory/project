package com.example.mes.process.Vo.TransactionVo;

public class InsertProMaterialVo {

    String name;
    String color;
    String size;
    int count;

    public InsertProMaterialVo() {
    }

    public InsertProMaterialVo(String name, String color, String size, int count) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "InsertProMaterialVo{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", count=" + count +
                '}';
    }


}
