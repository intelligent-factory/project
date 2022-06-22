package com.example.mes.WorkshopStorage.vo;

import java.sql.Timestamp;
import java.util.List;

public class WorkshopVo {
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * uuid
     */
    private String uuid;
    /**
     * id
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 车间名称
     */
    private String name;


    public List<LineVo> getLines() {
        return lines;
    }

    public void setLines(List<LineVo> line) {
        this.lines = line;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    /**
     *产线
     */
    private List<LineVo> lines;
    /**
     *工厂名
     */
    private String factory_name;

    public String getFactory_name() {
        return factory_name;
    }

    public void setFactory_name(String factory_name) {
        this.factory_name = factory_name;
    }

    /**
     *产线数量
     */
    private int lineNum;

    public int getStationNum() {
        return StationNum;
    }

    public void setStationNum(int stationNum) {
        StationNum = stationNum;
    }

    /**
     *工位数量
     */
    private int StationNum;
    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    /**
     *审批信息
     */
    private String verify;

    private Timestamp created_time;

    private Timestamp modified_time;

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

    String pre_id;

    public String getPre_id() {
        return pre_id;
    }

    public void setPre_id(String pre_id) {
        this.pre_id = pre_id;
    }

    private String created_by;

    private String modified_by;

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
}
