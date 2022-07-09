package com.example.mes.quality.service;

import com.example.mes.quality.bean.DefectBean;
import com.example.mes.quality.mapper.DefectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DefectServiceImpl implements DefectService {

    @Autowired
    DefectMapper defectMapper;

    @Override
    public List<DefectBean> getAllDefect() {
        return defectMapper.getInfo();
    }

    @Override
    public List<DefectBean> getSearchDefect(String keyword) {
        return defectMapper.getByDefectTypeOrCode(keyword);
    }

    @Override
    public List<DefectBean> getDefectByClassification(String keyword) {
        return defectMapper.getByClassification(keyword);
    }

    @Override
    public List<DefectBean> getDefectTypeAndCode() {
        return defectMapper.getTypeAndCode();
    }

    @Override
    public void deleteByCode(String defectCode) {
        defectMapper.deleteByCode(defectCode);
    }

    @Override
    public void addDefect(DefectBean defectBean) {
        defectMapper.addDefect(defectBean);
    }
}
