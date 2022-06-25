package com.example.mes.template.mapper;
import com.example.mes.template.entity.MaterialTemplate;
import org.apache.ibatis.annotations.*;


import java.util.List;
@Mapper
public interface TemplateMapper {
    @Select("select * from material_template")
    List<MaterialTemplate> getInfo();
    @Select("select * from material_template where name like #{name}")
    List<MaterialTemplate> selectMaterialTemplateByName(@Param("name") String name);
    @Select("select * from material_template where material_id = #{material_id}")
    List<MaterialTemplate> selectMaterialTemplateById(@Param("material_id") int material_id);
    @Insert("insert into material_template(material_id, name, password, attribute) values(#{material_id},#{name},# {attribute})")
    Integer addMaterialTemplate(MaterialTemplate materialTemplate);
    @Delete("delete from material_template where name=#{name}")
    int deleteMaterialTemplateByName(@Param("name") String name);
}
