package com.example.mes.process.Service.Impl;

import com.example.mes.process.Mapper.MaterialMapper;
import com.example.mes.process.Service.IMaterialService;
import com.example.mes.process.Vo.MaterialVo.DeleteMaterialVo;
import com.example.mes.process.Vo.MaterialVo.InsertMaterialVo;
import com.example.mes.process.Vo.MaterialVo.QueryMaterialVo;
import com.example.mes.process.Vo.MaterialVo.UpdateMaterialVo;
import com.example.mes.process.Vo.PageVo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService implements IMaterialService {

    @Autowired(required = false)
    MaterialMapper mapper;

    //从物料表material中查询全部物料信息，id、name、size、color、comments、status
    @Override
    public List<QueryMaterialVo> getMaterials(PageVo pageVo) {
        try {
            return mapper.getMaterials(pageVo);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service：查询物料信息失败！");
            return null;
        }
    }

    //跟据物料id查询对应物料的信息
    @Override
    public QueryMaterialVo getMaterialByID(String material_id) {
        try {
            return mapper.getMaterialByID(material_id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service:根据id查询物料信息失败");
            return null;
        }
    }

    //增加一个物料
    @Override
    public String addMaterial(InsertMaterialVo insertMaterialVo) {
        try {
            //用于检验用户权限
            String operator_id = insertMaterialVo.getOperator_id();
            //return "无权限";
            if (mapper.checkDuplicate(insertMaterialVo.getName(),insertMaterialVo.getSize(),insertMaterialVo.getColor())!=0){
                return "添加失败";
            }

            return mapper.addMaterial(insertMaterialVo)?"添加成功":"添加失败";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service:增加物料失败！");
            return "添加失败";
        }
    }

    //根据物料id删除一个物料
    @Override
    public String deleteMaterialByID(DeleteMaterialVo deleteMaterialVo) {
        try {
            //用于检验用户权限
            String operator_id = deleteMaterialVo.getOperator_id();
            //return "无权限";
            return mapper.deleteMaterialByID(deleteMaterialVo)?"删除成功":"删除失败";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service:删除物料失败");
            return "删除失败";
        }
    }

    //修改一个物料，status与comments
    @Override
    public String updateMaterialByID(UpdateMaterialVo updateMaterialVo) {
        try {
            //用于检验用户权限
            String operator_id = updateMaterialVo.getOperator_id();
            //return "无权限";
            return mapper.updateMaterialByID(updateMaterialVo)?"更新成功":"更新失败";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service:更新物料失败");
            return "更新失败";
        }
    }

    @Override
    public int getCount(){
     return mapper.getCount();
    }

    @Override
    public int getIndex() {
        return mapper.getIndex();
    }

}
