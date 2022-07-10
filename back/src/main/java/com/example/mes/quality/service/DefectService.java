package com.example.mes.quality.service;

import com.example.mes.quality.bean.DefectBean;

import java.util.List;


public interface DefectService {

    List<DefectBean> getAllDefect();
    List<DefectBean> getSearchDefect(String keyword);
    List<DefectBean> getDefectByClassification(String keyword);
    List<DefectBean> getDefectTypeAndCode();
    void deleteByCode(String defectCode);
    void addDefect(DefectBean defectBean);
}
