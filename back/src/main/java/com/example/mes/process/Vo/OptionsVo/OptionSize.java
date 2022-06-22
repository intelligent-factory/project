package com.example.mes.process.Vo.OptionsVo;

import java.util.ArrayList;

public class OptionSize {
    String size;
    ArrayList<String> colors;

    public OptionSize(String size, ArrayList<String> colors) {
        this.size = size;
        this.colors = colors;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        String str="[";
        int len = colors.size();
        for(String color:colors){
            color = "{\"value\":\""+color+"\",\"label\":\""+color+"\"}";
            if(len>1){
                color+=",";
                len--;
            }
            str += color;
        }
        str+="]";
        return "{\"value\":\""+size+"\",\"label\":\""+size+"\",\"children\":"+str+"}";
    }
}
