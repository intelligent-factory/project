package com.example.mes.quality.controller;

import com.example.mes.quality.bean.DefectBean;
import com.example.mes.quality.service.DefectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

//@EnableOpenApi
//@Api(description="瑕疵管理")
@RestController
public class DefectController {
    @Autowired
    DefectServiceImpl defectService;

//    @ApiOperation(value = "所有瑕疵")
    @CrossOrigin
    @GetMapping("/defect/all")
    public List<DefectBean> defectAll() throws Exception{
        List<DefectBean> defectBean = defectService.getAllDefect();
        return defectBean;
    }

//    @ApiOperation(value = "返回搜索瑕疵")
    @CrossOrigin
    @GetMapping("/defect/search")
    public List<DefectBean> defectSearch(@RequestParam("keywords") String keyword) throws Exception{
        if("".equals(keyword)){
            System.out.println(defectService.getSearchDefect(keyword));

            return defectAll();

        }else{
            System.out.println(defectService.getSearchDefect(keyword));
            return defectService.getSearchDefect(keyword);
        }
    }

//    @ApiOperation(value = "删除瑕疵")
    @CrossOrigin
    @PostMapping("/defect/delete")
    public void deleteDefectByCode(@RequestBody DefectBean defectBean) throws Exception{
        defectService.deleteByCode(defectBean.getDefectCode());
    }

//    @ApiOperation(value = "添加瑕疵")
    @CrossOrigin
    @PostMapping("/defect/add")
    public void addDefect(@RequestBody DefectBean defectBean) throws Exception{
        defectBean.setStatus("1");
        defectBean.setIsDeleted("0");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        defectBean.setCreatedTime(timestamp.toString());
        defectBean.setCreatedBy("x");
        defectBean.setModifiedTime(timestamp.toString());
        defectBean.setModifiedBy("x");
        defectService.addDefect(defectBean);
    }

    //    @ApiOperation(value = "返回瑕疵分类")
    @CrossOrigin
    @GetMapping("/defect/{keyword}/defects")
    public List<DefectBean> defectByClassification(@PathVariable("keyword") String keyword) throws Exception{
        if(!keyword.equals("全部瑕疵")){
            return defectService.getDefectByClassification(keyword);
        }else{
            return defectAll();
        }
    }
    //    @ApiOperation(value = "返回瑕疵代码及名字")

    @CrossOrigin
    @GetMapping("/defect/typeAndCode")
    public List<DefectBean> defectTypeAndCode() throws Exception{
        return defectService.getDefectTypeAndCode();
    }
}
