package com.example.mes.WorkshopStorage.mapper;

import com.example.mes.WorkshopStorage.entity.Workshop;
import com.example.mes.WorkshopStorage.vo.WorkshopVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface WorkshopMapper {
    List<WorkshopVo> selectAll (@Param("start") long start, @Param("num") long num);
    List<WorkshopVo> selectApplyAll (@Param("start") long start, @Param("num") long num);
    List<WorkshopVo> all_workshop();
    WorkshopVo getById(@Param("workshopId") String workshopId);
    WorkshopVo getApplyById(@Param("workshopId") String workshopId);
    WorkshopVo getByName(@Param("workshopName") String workshopName);
    WorkshopVo createInfo(@Param("workshopId") String workshopId);
    Integer getCount();
    Integer getApplyCount();
    void insert(Workshop params);
    void setNormal(@Param("workshopId") String workshopId, @Param("modified_time")Timestamp modified_time, @Param("user") String user);
    void setNormalDelete(@Param("workshopId") String workshopId, @Param("modified_time")Timestamp modified_time, @Param("user") String user);
    void setDelete(@Param("workshopId") String workshopId, @Param("modified_time")Timestamp modified_time, @Param("user") String user);
    WorkshopVo checkById(@Param("workshopId") String workshopId);
    WorkshopVo checkByPre_id(@Param("workshopId") String workshopId);
//TODO :add modified user
    void deleteWorkshop(@Param("workshopId") String workshopId, @Param("modified_time")Timestamp modified_time);
}
