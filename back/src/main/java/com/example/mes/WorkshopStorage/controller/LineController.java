package com.example.mes.WorkshopStorage.controller;

import com.example.mes.WorkshopStorage.service.LineService;
import com.example.mes.WorkshopStorage.vo.LineVo;
import com.example.mes.WorkshopStorage.vo.PageVo;
import com.example.mes.WorkshopStorage.vo.Result;
import com.example.mes.WorkshopStorage.vo.StationVo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/line")
public class LineController {
    @Autowired
    private LineService lineService;

    @GetMapping(value = "delete")
    public Result<?> deleteWorkshop(String workshopId, String lineId){
        try {
            lineService.delete(workshopId, lineId);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("申请提交失败",e.getMessage());
            return Result.error("申请提交失败!");
        }
        return Result.ok("申请提交成功!");
    }

    @GetMapping(value = "create")
    public Result<?> create(String workshopId, String lineId, String lineName, String routing){
        try {
            LineVo line = new LineVo();
            line.setId(lineId);
            line.setName(lineName);
            line.setRouting(routing);
            lineService.create(workshopId, line, "insert");
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("申请提交失败",e.getMessage());
            return Result.error("申请提交失败!");
        }
        return Result.ok("申请提交成功!");
    }

    @GetMapping(value = "update")
    public Result<?> update(String workshopId, String lineId, String newName,String routing){
        try {
            lineService.update(workshopId, lineId, newName, routing);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error("申请提交失败",e.getMessage());
            return Result.error("申请提交失败!");
        }
        return Result.ok("申请提交成功!");
    }


    @GetMapping(value = "searchLineItem")
    public Result<PageVo<LineVo>> searchLineItem(String currentPage, String pagesize) {
        Result<PageVo<LineVo>> result = new Result<>();
        try {
            result = lineService.searchLineItem(currentPage, pagesize);
        }catch (Exception e){
            e.printStackTrace();
            LoggerFactory.getLogger(this.getClass()).error(""+e.getMessage());
            result.error500(e.getMessage());
        }
        return result;
    }
    @GetMapping("applyLine")
    public Result<PageVo<StationVo>> applyWorkshop(String current, String pages){
        Result<PageVo<StationVo>> result = new Result<>();
        try {
            result = lineService.applyLine(current, pages);
        }catch (Exception e){
            e.printStackTrace();
            LoggerFactory.getLogger(this.getClass()).error(""+e.getMessage());
            result.error500(e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "searchLine")
    public Result<LineVo> searchLine(String Id, String info){
        Result<LineVo> result = new Result<>();
        try {
            result = lineService.searchLine(Id, info);
        }catch (Exception e){
            e.printStackTrace();
            LoggerFactory.getLogger(this.getClass()).error(""+e.getMessage());
            result.error500(e.getMessage());
        }
        return result;
    }






}
