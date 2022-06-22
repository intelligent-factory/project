package com.example.mes.WorkshopStorage.controller;

import com.example.mes.WorkshopStorage.para.WorkshopUpdatePara;
import com.example.mes.WorkshopStorage.service.LineService;
import com.example.mes.WorkshopStorage.service.StationService;
import com.example.mes.WorkshopStorage.service.WorkshopService;
import com.example.mes.WorkshopStorage.vo.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workshop")
public class WorkshopController {
    @Autowired
    private WorkshopService workshopService;
    @Autowired
    private LineService lineService;
    @Autowired
    private StationService stationService;

    @GetMapping(value = "/mainInfo")
    public Result<WorkshopVo> mainInfo(String workshopId) {
        Result<WorkshopVo> result = new Result<>();
        try {
            result = workshopService.getById(workshopId);
//            workshopRe.setId(
        }catch (Exception e){
            e.printStackTrace();
            LoggerFactory.getLogger(this.getClass()).error(""+e.getMessage());
            result.error500(e.getMessage());
        }
        return result;
    }
    @GetMapping(value = "delete")
    public Result<?> deleteWorkshop(String workshopId){
        try {
            workshopService.deleteWorkshop(workshopId);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("申请提交失败",e.getMessage());
            return Result.error("申请提交失败!");
        }
        return Result.ok("申请提交成功!");
    }
    @GetMapping(value = "update")
    public Result<?> updateHead(WorkshopUpdatePara para){
        try {
            workshopService.updateHead(para);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("申请提交失败",e.getMessage());
            return Result.error("申请提交失败!");
        }
        return Result.ok("申请提交成功!");
    }
    @PostMapping(value = "create")
    public Result<?> create(@RequestBody WorkshopVo para){
        try {
            workshopService.create(para);
            for(LineVo line: para.getLines()){
                lineService.create(para.getId() ,line, "create");
                for(StationVo station : line.getStations()){
                    stationService.create(para.getId(), line.getId(), station, "create");
                }
            }

        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("申请提交失败",e.getMessage());
            return Result.error("申请提交失败!");
        }
        return Result.ok("申请提交成功!");
    }
    @GetMapping(value = "searchWorkshop")
    public Result<WorkshopVo> search(String info) {
        Result<WorkshopVo> result = new Result<>();
        try {
            result = workshopService.search(info);
        }catch (Exception e){
            e.printStackTrace();
            LoggerFactory.getLogger(this.getClass()).error(""+e.getMessage());
            result.error500(e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "workshopItem")
    public Result<PageVo<WorkshopVo>> workshopItem(String currentPage, String pagesize) {
        Result<PageVo<WorkshopVo>> result = new Result<>();
        try {
            result = workshopService.workshopItem(currentPage, pagesize);
        }catch (Exception e){
            e.printStackTrace();
            LoggerFactory.getLogger(this.getClass()).error(""+e.getMessage());
            result.error500(e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "applyWorkshop")
    public Result<PageVo<StationVo>> applyWorkshop(String current, String pages){
        Result<PageVo<StationVo>> result = new Result<>();
        try {
            result = workshopService.applyWorkshop(current, pages);
        }catch (Exception e){
            e.printStackTrace();
            LoggerFactory.getLogger(this.getClass()).error(""+e.getMessage());
            result.error500(e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "createInfo")
    public Result<WorkshopVo> createInfo(String workshop_id, String line_id, String id){
        Result<WorkshopVo> result = new Result<>();
        try {
            result = workshopService.createInfo(workshop_id);
        }catch (Exception e){
            e.printStackTrace();
            LoggerFactory.getLogger(this.getClass()).error(""+e.getMessage());
            result.error500(e.getMessage());
        }
        return result;
    }
    @GetMapping(value = "update_delete_Info")
    public Result<List<WorkshopVo>> update_delete_Info(String workshopId, String lineId, String stationId){
        Result<List<WorkshopVo>> result = new Result<>();
        try {
            result = workshopService.update_delete_Info(workshopId, lineId, stationId);
        }catch (Exception e){
            e.printStackTrace();
            LoggerFactory.getLogger(this.getClass()).error(""+e.getMessage());
            result.error500(e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "confirm")
    public Result<?> confirm(String user, String info, String workshop_id, String line_id, String id){
        //TODO:权限判断
        try {
            workshopService.confirm(user, info, workshop_id, line_id, id);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("申请失败",e.getMessage());
            return Result.error("申请失败!");
        }
        return Result.ok("申请成功!");
    }
}
