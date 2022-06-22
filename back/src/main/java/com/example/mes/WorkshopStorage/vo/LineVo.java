package com.example.mes.WorkshopStorage.vo;

import java.sql.Timestamp;
import java.util.List;

public class LineVo {


    public String getWorkshop_id() {
        return workshop_id;
    }

    public void setWorkshop_id(String workshop_id) {
        this.workshop_id = workshop_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StationVo> getStations() {
        return stations;
    }

    public void setStations(List<StationVo> stations) {
        this.stations = stations;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getPre_id() {
        return pre_id;
    }

    public void setPre_id(String pre_id) {
        this.pre_id = pre_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * uuid
     */
    private String uuid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * id
     */
    private String id;

    /**
     * uuid
     */
    private String workshop_id;

    /**
     * 生产线名称
     */
    private String name;

    public int getStationNum() {
        return stationNum;
    }

    public void setStationNum(int stationNum) {
        this.stationNum = stationNum;
    }

    /**
     *工位
     */
    private List<StationVo> stations;

    /**
     *工位数量
     */
    private int stationNum;
    /**
     *工位数量
     */
    private int equipNum;

    public int getEquipNum() {
        return equipNum;
    }

    public void setEquipNum(int equipNum) {
        this.equipNum = equipNum;
    }


    /**
     * verify
     */
    private String verify;
    private String pre_id;
    private Timestamp created_time;

    public Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    public Timestamp getModified_time() {
        return modified_time;
    }

    public void setModified_time(Timestamp modified_time) {
        this.modified_time = modified_time;
    }

    private Timestamp modified_time;

    private String created_by;

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    private String modified_by;

    private String routing;

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }
}
