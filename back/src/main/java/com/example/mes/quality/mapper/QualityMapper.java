package com.example.mes.quality.mapper;

import com.example.mes.quality.bean.QualityBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: XiaoYu
 * @Date: 2021/07/13/12:14
 * @Description:
 */
public interface QualityMapper {

    void addQualityResults(QualityBean qualityBean);
    List<QualityBean> getQualityResults();
    List<QualityBean> getQualityResultsByDate(String[] list);
    List<QualityBean> getQualityResultsByWorkshop(String[] list);
    List<QualityBean> getQualityResultsByDefectTypePart(String[] list);
    List<QualityBean> getQualityResultsByDefectType(String[] list);
}
