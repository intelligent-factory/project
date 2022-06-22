package com.example.mes.quality.service;

import com.example.mes.quality.bean.DefectBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: XiaoYu
 * @Date: 2021/07/08/8:53
 * @Description:
 */
public interface DefectServiceImpl {

    List<DefectBean> getAllDefect();
    List<DefectBean> getSearchDefect(String keyword);
    List<DefectBean> getDefectByClassification(String keyword);
    List<DefectBean> getDefectTypeAndCode();
    void deleteByCode(String defectCode);
    void addDefect(DefectBean defectBean);
}
