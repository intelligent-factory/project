package com.example.mes.quality.service;

import com.example.mes.quality.bean.QualityBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: XiaoYu
 * @Date: 2021/07/13/12:17
 * @Description:
 */
public interface QualityServiceImpl {

    void addResults(QualityBean qualityBean);
    List<QualityBean> getResults();
    List<QualityBean> getResultsByDate(String[] list);
    List<QualityBean> getResultsByWorkshop(String[] list);
    List<QualityBean> getResultsByDefectTypePart(String[] list);
    List<QualityBean> getResultsByDefectType(String[] list);
}
