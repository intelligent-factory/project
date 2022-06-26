package com.example.mes.template.service;

import com.example.mes.process.Vo.MaterialVo.DeleteMaterialVo;
import com.example.mes.process.Vo.MaterialVo.InsertMaterialVo;
import com.example.mes.process.Vo.MaterialVo.QueryMaterialVo;
import com.example.mes.process.Vo.MaterialVo.UpdateMaterialVo;

import com.example.mes.template.entity.MaterialTemplate;
import com.example.mes.template.mapper.TemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TemplateService implements TemplateServiceImpl{

    @Autowired
    private TemplateMapper templateMapper;

    public List<MaterialTemplate> getAllMaterial() {
        return templateMapper.getInfo();
    }

    @Override
    public List<MaterialTemplate> getMaterialTemplateByName(String name) {
        return templateMapper.selectMaterialTemplateByName(name);
    }

    @Override
    public List<MaterialTemplate> getMaterialTemplateByID(int material_id) {
        return templateMapper.selectMaterialTemplateById(material_id);
    }

    //增加一个模板要一次性插入多条，保证事务（原子性）
    @Override
    public String addMaterialTemplate(MaterialTemplate MaterialTemplate) {
        return null;
    }

    @Override
    public String deleteMaterialTemplateByID(DeleteMaterialVo deleteMaterialVo) {
        return null;
    }

    @Override
    public String updateMaterialTemplateByID(UpdateMaterialVo updateMaterialVo) {
        return null;
    }



}
