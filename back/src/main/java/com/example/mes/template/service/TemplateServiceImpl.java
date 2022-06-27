package com.example.mes.template.service;

import com.example.mes.process.Vo.MaterialVo.DeleteMaterialVo;
import com.example.mes.process.Vo.MaterialVo.InsertMaterialVo;
import com.example.mes.process.Vo.MaterialVo.QueryMaterialVo;
import com.example.mes.process.Vo.MaterialVo.UpdateMaterialVo;
import com.example.mes.template.entity.MaterialTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface TemplateServiceImpl {
        List<MaterialTemplate> getAllMaterial();
        List<MaterialTemplate> getMaterialTemplateByName(String keyword);
        //跟据物料模板id查询对应物料的信息
        List<MaterialTemplate> getMaterialTemplateByID(int material_id);
        //增加一个物料模板
        String addMaterialTemplate(MaterialTemplate materialTemplate);
        //根据物料模板id删除一个物料
        String deleteMaterialTemplateByID(DeleteMaterialVo deleteMaterialVo);
        //修改一个物料模板
        String updateMaterialTemplateByID(UpdateMaterialVo updateMaterialVo);


}
