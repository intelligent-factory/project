package com.example.mes.WorkshopStorage.mapper;

import com.example.mes.WorkshopStorage.vo.newStationVo;
import com.example.mes.WorkshopStorage.vo.StationVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StationMapper {
    StationVo getUpdate(@Param("stationId") String stationId);
    List<StationVo> selectApplyAll(@Param("start") long start, @Param("num") long num);
    List<StationVo> createInfo(@Param("workshop_id") String workshop_id, @Param("line_id") String line_id);
    List<newStationVo> getByLine(@Param("workshop_id") String workshop_id, @Param("line_id") String line_id);
    List<StationVo> checkByLine(@Param("workshop_id") String workshop_id, @Param("line_id") String line_id);
    StationVo getApplyById(@Param("workshop_id") String workshop_id, @Param("line_id") String line_id, @Param("station_id") String station_id);
    StationVo getById(@Param("workshop_id") String workshop_id, @Param("line_id") String line_id, @Param("station_id") String station_id);
    StationVo checkById(@Param("workshop_id") String workshop_id, @Param("line_id") String line_id, @Param("station_id") String station_id);
    StationVo checkByPre_id(@Param("workshopId") String workshop_id, @Param("lineId") String line_id, @Param("stationId") String station_id);
    void insert(StationVo stationVo);
    void updateByWorkshop(@Param("workshopId") String workshopId, @Param("pre_id") String pre_id);
    void updateByLine(@Param("workshopId") String workshopId, @Param("lineId") String lineId, @Param("pre_id") String pre_id);
    void setNormalDelete(@Param("workshopId") String workshopId, @Param("lineId") String lineId, @Param("stationId") String stationId, @Param("modified_time") Timestamp modified_time, @Param("user") String user);
    void setNormal(@Param("workshopId") String workshopId, @Param("lineId") String lineId, @Param("stationId") String stationId, @Param("modified_time") Timestamp modified_time, @Param("user") String user);
    void setDelete(@Param("workshopId") String workshopId, @Param("lineId") String lineId, @Param("stationId") String stationId, @Param("modified_time") Timestamp modified_time, @Param("user") String user);
    void setNormalByWorkshop(@Param("workshopId") String workshopId, @Param("modified_time") Timestamp modified_time, @Param("user") String user);
    void setDeleteByWorkshop(@Param("workshopId") String workshopId, @Param("modified_time") Timestamp modified_time, @Param("user") String user);
    Integer getApplyCount();
    Integer getStationNumByworkshopId(@Param("workshopId") String workshopId);
    Integer getEquipNumByLineId(@Param("workshopId") String workshopId, @Param("lineId") String lineId);
    Integer getStationNumByLineId(@Param("workshopId") String workshopId, @Param("lineId") String lineId);
    void delete(@Param("workshopId") String workshopId, @Param("lineId") String lineId, @Param("stationId") String stationId, @Param("modified_time") Timestamp modified_time);
    List<StationVo> selectAllStation(@Param("workshopId") String workshopId, @Param("lineId") String lineId);

    void setNormalByWorkshop2(@Param("workshopId") String workshopId, @Param("modified_time") Timestamp modified_time, @Param("user") String user);

    void setNormal2(@Param("workshopId") String workshopId, @Param("lineId") String lineId, @Param("modified_time") Timestamp modified_time, @Param("user") String user);

    void insertStations(StationVo stationVo);
}
