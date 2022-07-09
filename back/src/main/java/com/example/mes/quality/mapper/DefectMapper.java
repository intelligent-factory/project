package com.example.mes.quality.mapper;

import com.example.mes.quality.bean.DefectBean;

import java.util.List;


public interface DefectMapper {

    List<DefectBean> getInfo();
    List<DefectBean> getByDefectTypeOrCode(String keyword);
    List<DefectBean> getByClassification(String keyword);
    List<DefectBean> getTypeAndCode();
    void deleteByCode(String defectCode);
    void addDefect(DefectBean defectBean);

}
