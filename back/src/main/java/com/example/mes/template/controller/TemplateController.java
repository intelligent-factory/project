package com.example.mes.template.controller;
import com.example.mes.template.entity.MaterialTemplate;
import com.example.mes.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/template")
public class TemplateController {
        @Autowired
        TemplateService templateService;

        @CrossOrigin
        @GetMapping("/material/all")  //全部物料模板信息 格式在下方注释
        public Object materialAll() throws Exception {
            List<MaterialTemplate> materialTemplate = templateService.getAllMaterial();
            HashMap<String,Object> map = new HashMap<>();
            //key为物料名，value为物料属性数组，如：{"拉链":["规格","颜色"],"把手":["颜色","状态","描述"]}
            List<String> list = new ArrayList<>();
            int before=0;
            //id与上一记录不一样就是另一模板，清空list重新加入key value
            for(MaterialTemplate t : materialTemplate) {
                if (before==t.getMaterial_id()){
                list.add(t.getAttribute());
                map.put(t.getName(),list);
                }else {
                    list=new ArrayList<>();
                    list.add(t.getAttribute());
                    }
                before = t.getMaterial_id();
                }
            return map;
        }

    @CrossOrigin
    @GetMapping("/material/getMaterialTemplateByName")  //全部物料模板信息 格式在下方注释
    public Object getMaterialTemplateByName(String name) throws Exception {

        List<MaterialTemplate> materialTemplate = templateService.getMaterialTemplateByName(name);
        HashMap<String,Object> map = new HashMap<>();
        //key为物料名，value为物料属性数组，如：{"拉链":["规格","颜色"],"把手":["颜色","状态","描述"]}
        List<String> list = new ArrayList<>();
        int before=0;
        for(MaterialTemplate t : materialTemplate) {

            if (before==t.getMaterial_id()){

                list.add(t.getAttribute());
                map.put(t.getName(),list);
            }else {
                list=new ArrayList<>();
                list.add(t.getAttribute());
            }
            before = t.getMaterial_id();
        }
        return map;
    }



}
