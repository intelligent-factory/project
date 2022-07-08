package com.example.mes.quality.controller;

import com.alibaba.fastjson.JSON;
import com.example.mes.WorkshopStorage.vo.Result;
import com.example.mes.quality.bean.QualityListBean;
import com.example.mes.quality.bean.Vo.QualityListVo;
import com.example.mes.quality.service.QualityListService;
import com.example.mes.system.controller.MyUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/qualityList")
public class QualityListController {

    @Autowired
    QualityListService qualitylistService;

    //查找所有质检任务
    @GetMapping("/queryAllList")
    public List<QualityListBean> queryAll() {
        return qualitylistService.queryAll();
    }

    //根据状态查询质检任务
    @GetMapping("/queryListByStatus")
    public List<QualityListBean> queryListByStatus() {
        return qualitylistService.querylistByStatus();
    }

    //根据产线查询质检任务
    @GetMapping("/queryListByLine")
    public List<QualityListBean> queryListByLine() {
        return qualitylistService.querylistByLine();
    }

    //根据车间查询质检任务
    @GetMapping("/queryListByWorkShop")
    public List<QualityListBean> queryListByWorkShop() {
        return qualitylistService.querylistByWorkShop();
    }

    //查询某质检任务的产品数
    @GetMapping("/queryNumByWorkShop")
    public Integer queryListNum() {
        return qualitylistService.queryListNum();
    }

    //分页查询质检任务
    @PostMapping("/queryQualityList")
    public Object queryQualityList(@RequestBody QualityListVo qualityListVo) {

        List<QualityListBean> qualityList = qualitylistService.queryQualityList(qualityListVo);
        int qualityListCount = qualitylistService.getLastCount();

        HashMap<String, Object> res = new HashMap<>();
        res.put("qualityList", qualityList);
        res.put("total", qualityListCount);
        MyUtils.success(res);
        return res;
    }



    @GetMapping( "/updateStatus")
    public Result<?> updateQualityListStatus(String list_id){
        try {
            qualitylistService.updateQualityListStatus(list_id);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("申请提交失败",e.getMessage());
            return Result.error("申请提交失败!");
        }
        return Result.ok("申请提交成功!");
    }



    //根据list_id查询质检任务
    @PostMapping("/queryQualityByList")
    public Object queryQualityByList(@RequestParam("list_id") String list_id) {

        QualityListBean quality = qualitylistService.queryQualityByList(list_id);
        HashMap<String, Object> res = new HashMap<>();
        res.put("quality", quality);
        MyUtils.success(res);
        return res;
    }

    //为车间下拉框提供选项
    @GetMapping("/getAllWorkshop")
    public String getAllWorkshop(){
        try {
            return JSON.toJSONString(qualitylistService.getAllWorkshop());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("车间下拉框生成失败！");
            return "";
        }
    }


    //为产线下拉框提供选项
    @GetMapping("/getAllLine")
    public String getAllLine(){
        try {
            return JSON.toJSONString(qualitylistService.getAllLine());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("产线下拉框生成失败！");
            return "";
        }
    }

    //为产线下拉框提供选项
    @GetMapping("/getAllLinesByWorkshop")
    public String getAllLinesByWorkshop(@RequestParam("corWorkShopId") String workshop_id){
        try {
            return JSON.toJSONString(qualitylistService.getAllLinesByWorkshop(workshop_id));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("产线下拉框生成失败！");
            return "";
        }
    }

}
