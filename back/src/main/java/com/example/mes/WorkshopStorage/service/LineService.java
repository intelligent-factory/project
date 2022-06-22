package com.example.mes.WorkshopStorage.service;

import com.example.mes.WorkshopStorage.entity.Line;
import com.example.mes.WorkshopStorage.mapper.LineMapper;
import com.example.mes.WorkshopStorage.mapper.StationMapper;
import com.example.mes.WorkshopStorage.mapper.WorkshopMapper;
import com.example.mes.WorkshopStorage.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class LineService {
    @Autowired
    private LineMapper lineMapper;
    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private WorkshopMapper workshopMapper;
    public void setTime(Line line){
        String uuid = UUID.randomUUID().toString().replace("-","");
        line.setUuid(uuid);
        line.setStatus("0");
        line.setIs_deleted("0");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        line.setCreated_by("1");
        line.setModified_by("1");
        line.setCreated_time(timestamp);
        line.setModified_time(timestamp);
    }
    public void create(String workshopId, LineVo params, String type) throws SQLException {
        //车间不存在
        if(!type.equals("create") && workshopMapper.getById(workshopId) == null){
            throw new SQLException();
        }
//        产线已经存在
        LineVo lineVo = lineMapper.checkById(workshopId, params.getId());
        if(lineVo!=null && (params.getPre_id()==null || params.getPre_id().equals(""))){
            throw new SQLException();
        }
        Line line = new Line();
        line.setId(params.getId());
        line.setWorkshop_id(workshopId);
        line.setName(params.getName());
        line.setVerify(type);
        line.setPre_id(params.getPre_id());
        line.setRouting(params.getRouting());
        setTime(line);
        lineMapper.insert(line);
    }

    public void delete(String workshopId, String lineId) throws SQLException {
        List<StationVo> stationlist = stationMapper.checkByLine(workshopId, lineId);
        if(stationlist.size() > 0){
            throw new SQLException();
        }
        if(lineMapper.checkByPre_id(workshopId, lineId) != null){
            throw new SQLException();
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        lineMapper.delete(workshopId, lineId, timestamp);
    }
    public void update(String workshopId, String lineId, String newName, String routing) throws SQLException {
        //要修改的车间不在数据库里或者已经有其他申请 除修改以外
        LineVo line_ = lineMapper.getById(workshopId, lineId);
        LineVo line = new LineVo();
        if(line_ == null || !line_.getVerify().equals("normal")){
            throw new SQLException();
        }
//        要修改的车间已经有修改申请
        LineVo line__ = lineMapper.getUpdate(line_.getId());
        if(line__ != null){
            throw new SQLException();
        }
        if(newName == null){
            newName = line_.getName();
        }
        if(routing == null){
            routing = line_.getRouting();
        }
        line.setId(lineId);
        line.setName(newName);
        line.setRouting(routing);
        line.setPre_id(line_.getId());
        create(workshopId, line, "insert");
//        Line line = new Line();
//        line.setId(lineId);
//        line.setWorkshop_id(workshopId);
//        line.setName(newName);
//        line.setVerify("insert");
//        lineMapper.(workshopId, lineId, timestamp);
    }
//    public Result<List<LineVo>> getByWorkshop(int workshop_id){
//
//        Result<List<LineVo>> result = new Result<List<LineVo>>();
//        List<LineVo> LineVoList = lineMapper.getByWorkshop(workshop_id);
//        result.setResult(LineVoList);
//        return result;
//    }
    public Result<PageVo<LineVo>> searchLineItem(String page, String page_size) throws SQLException {
        Result<PageVo<LineVo>> result = new Result<>();
        PageVo<LineVo> pageVo = new PageVo();
        Integer size = lineMapper.getCount();
        if(size == null)
            size = 0;
        pageVo.setTotal(size);
        pageVo.setSize((size - 1) / Integer.parseInt(page_size) + 1);
        if (pageVo.getSize() < 0) {
            pageVo.setSize(0);
        }
        if (Integer.parseInt(page) > pageVo.getSize()) {
            throw new SQLException();
        }
        if (Integer.parseInt(page) == pageVo.getSize()) {
            pageVo.setPages((size - 1) % Integer.parseInt(page_size) + 1);
        } else {
            pageVo.setPages(Integer.parseInt(page_size));
        }
        pageVo.setCurrent(Integer.parseInt(page));
        List<LineVo> currentlist = lineMapper.selectAll((pageVo.getCurrent() - 1) * Integer.parseInt(page_size), pageVo.getPages());
        for(LineVo line : currentlist){
            Integer cnt;
            cnt = stationMapper.getStationNumByLineId(line.getWorkshop_id(), line.getId());
            if(cnt != null){
                line.setStationNum(cnt);
            }
            cnt = stationMapper.getEquipNumByLineId(line.getWorkshop_id(), line.getId());
            if(cnt != null){
                line.setEquipNum(cnt);
            }
        }
        pageVo.setRecords(currentlist);
        result.setResult(pageVo);
        return result;
    }

    public Result<PageVo<StationVo>> applyLine(String page, String page_size) throws SQLException {
        Result<PageVo<StationVo>> result = new Result<>();
        PageVo<StationVo> pageVo = new PageVo();
        Integer size = lineMapper.getApplyCount();
        if(size == null)
            size = 0;
        pageVo.setTotal(size);
        pageVo.setSize((size - 1) / Integer.parseInt(page_size) + 1);
        if (pageVo.getSize() < 0) {
            pageVo.setSize(0);
        }
        if (Integer.parseInt(page) > pageVo.getSize()) {
            throw new SQLException();
        }
        if (Integer.parseInt(page) == pageVo.getSize()) {
            pageVo.setPages((size - 1) % Integer.parseInt(page_size) + 1);
        } else {
            pageVo.setPages(Integer.parseInt(page_size));
        }
        pageVo.setCurrent(Integer.parseInt(page));
        List<LineVo> currentlist = lineMapper.selectApplyAll((pageVo.getCurrent() - 1) * Integer.parseInt(page_size), pageVo.getPages());
        List<StationVo> resultlist = new LinkedList<>();
        for(LineVo line : currentlist){
            StationVo station = new StationVo();
            station.setWorkshop_id(line.getWorkshop_id());
            station.setLine_id(line.getId());
            if(line.getVerify().equals("delete")) {
                station.setCreated_time(line.getModified_time());
                station.setCreated_by(line.getModified_by());
            }
            else {
                station.setCreated_time(line.getCreated_time());
                station.setCreated_by(line.getCreated_by());
            }
            if(line.getVerify().equals("insert")){
                if(line.getPre_id() == null || line.getPre_id().equals("")){
                    line.setVerify("create");
                }else{
                    line.setVerify("update");
                }
            }
            station.setVerify(line.getVerify());
            resultlist.add(station);
        }
        pageVo.setRecords(resultlist);
        result.setResult(pageVo);
        return result;
    }
    public Result<LineVo> searchLine(String workshopId, String lineId) throws SQLException{
        Result<LineVo> result = new Result<>();
        WorkshopVo workshopVo = workshopMapper.getById(workshopId);
        if(workshopVo == null){
            workshopVo = workshopMapper.getByName(workshopId);
            workshopId = workshopVo.getId();
        }
        LineVo lineVo = lineMapper.getById(workshopId, lineId);
        if(lineVo == null){
            lineVo = lineMapper.getByName(workshopId, lineId);
        }
        lineVo.setEquipNum(stationMapper.getEquipNumByLineId(workshopId, lineVo.getId()));
        lineVo.setStationNum(stationMapper.getStationNumByLineId(workshopId, lineVo.getId()));
        result.setResult(lineVo);
        return result;
    }
}
