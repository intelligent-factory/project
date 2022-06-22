package com.example.mes.WorkshopStorage.mapper;

import com.example.mes.WorkshopStorage.entity.Line;
import com.example.mes.WorkshopStorage.vo.LineVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LineMapper {
    LineVo getByName(@Param("workshopId") String workshopId,@Param("lineName") String lineName);
    LineVo getById (@Param("workshopId") String workshopId,@Param("lineId") String lineId);
    LineVo getApplyById (@Param("workshopId") String workshopId,@Param("lineId") String lineId);
    List<LineVo> getByWorkshop(String workshopId);
    List<LineVo> createInfo(String workshopId);
    List<LineVo> checkByWorkshop(String workshopId);
    LineVo getUpdate(@Param("lineId") String lineId);
    LineVo checkById(@Param("workshopId") String workshop_id,@Param("lineId")String line_id);
    LineVo checkByPre_id(@Param("workshopId") String workshop_id,@Param("lineId")String line_id);
    void insert(Line line);
    void setNormal(@Param("workshopId") String workshopId,@Param("lineId") String lineId, @Param("modified_time")Timestamp modified_time, @Param("user") String user);
    void setNormalDelete(@Param("workshopId") String workshopId,@Param("lineId") String lineId, @Param("modified_time")Timestamp modified_time, @Param("user") String user);
    void setDelete(@Param("workshopId") String workshopId,@Param("lineId") String lineId, @Param("modified_time")Timestamp modified_time, @Param("user") String user);
    void setNormalByWorkshop(@Param("workshopId") String workshopId, @Param("modified_time")Timestamp modified_time, @Param("user") String user);
    void setDeleteByWorkshop(@Param("workshopId") String workshopId, @Param("modified_time")Timestamp modified_time, @Param("user") String user);
    void updateByWorkshop(@Param("workshopId") String workshopId, @Param("pre_id")String pre_id);
    //TODO :add modified user
    void delete(@Param("workshopId") String workshopId, @Param("lineId") String lineId, @Param("modified_time") Timestamp modified_time);
    Integer getLineNumById(@Param("workshopId") String workshopId);
    Integer getCount();
    Integer getApplyCount();
    List<LineVo> selectAll (@Param("start") long start, @Param("num") long num);
    List<LineVo> selectApplyAll (@Param("start") long start, @Param("num") long num);
}
