package com.example.mes.WorkshopStorage.entity;

import java.sql.Timestamp;

public class Workshop {
    /**
     * uuid
     */
    private String uuid;
    /**
     * id
     */
    private String id;

    /**
     * 车间名称
     */
    private String name;

    /**
     * 工厂名称
     */
    private String factory_name;

    /**
     * 状态
     */
    private String status;
    /**
     * 审批状态
     */
    private String verify;

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getPre_uuid() {
        return pre_uuid;
    }

    public void setPre_uuid(String pre_uuid) {
        this.pre_uuid = pre_uuid;
    }

    /**
     * 过往uuid
     */
    private String pre_uuid;
    /**
     * 被删除
     */
    private String is_deleted;
    /**
     * 创建时间
     */
    private Timestamp created_time;
    /**
     * 创建人
     */
    private String created_by;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactory_name() {
        return factory_name;
    }

    public void setFactory_name(String factory_name) {
        this.factory_name = factory_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Timestamp getModified_time() {
        return modified_time;
    }

    public void setModified_time(Timestamp modified_time) {
        this.modified_time = modified_time;
    }



    /**
     * 修改时间
     */
    private Timestamp modified_time;

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    /**
     * 修改人
     */
    private String modified_by;

}
