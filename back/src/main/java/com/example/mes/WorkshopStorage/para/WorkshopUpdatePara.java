package com.example.mes.WorkshopStorage.para;

public class WorkshopUpdatePara {
    public String getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
    }

    public String getNewWorkshopId() {
        return newWorkshopId;
    }

    public void setNewWorkshopId(String newWorkshopId) {
        this.newWorkshopId = newWorkshopId;
    }

    public String getNewWorkshopName() {
        return newWorkshopName;
    }

    public void setNewWorkshopName(String newWorkshopName) {
        this.newWorkshopName = newWorkshopName;
    }

    public String getNewFactory() {
        return newFactory;
    }

    public void setNewFactory(String newFactory) {
        this.newFactory = newFactory;
    }

    /**
     *车间编号
     */
    private String workshopId;

    /**
     *新车间编号
     */
    private String newWorkshopId;
    /**
     *新车间名
     */
    private String newWorkshopName;
    /**
     *新工厂编号
     */
    private String newFactory;

}
