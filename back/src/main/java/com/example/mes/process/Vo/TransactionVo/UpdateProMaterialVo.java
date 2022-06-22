package com.example.mes.process.Vo.TransactionVo;

public class UpdateProMaterialVo {

    String name;
    String size;
    String color;
    String count;

    public UpdateProMaterialVo() {
    }

    public UpdateProMaterialVo(String name, String size, String color, String count) {
        this.name = name;
        this.size = size;
        this.color = color;
        this.count = count;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UpdateProMaterialVo{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
