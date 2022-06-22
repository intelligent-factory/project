package com.example.mes.quality.mapper;

import com.example.mes.quality.bean.DefectBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: XiaoYu
 * @Date: 2021/07/08/8:44
 * @Description:瑕疵清单接口类
 */
public interface DefectMapper {

    List<DefectBean> getInfo();
    List<DefectBean> getByDefectTypeOrCode(String keyword);
    List<DefectBean> getByClassification(String keyword);
    List<DefectBean> getTypeAndCode();
    void deleteByCode(String defectCode);
    void addDefect(DefectBean defectBean);

}
