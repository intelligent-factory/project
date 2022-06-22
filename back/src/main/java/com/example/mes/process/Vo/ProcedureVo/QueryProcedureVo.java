package com.example.mes.process.Vo.ProcedureVo;

public class QueryProcedureVo {

    //工序id
    String procedure_id;
    //工序名称
    String name;
    //工序状态
    String status;
    //工序描述
    String comments;
    //耗费
    double cost;

    public QueryProcedureVo() {
    }

    public QueryProcedureVo(String procedure_id, String name, String status, String comments, double cost) {
        this.procedure_id = procedure_id;
        this.name = name;
        this.status = status;
        this.comments = comments;
        this.cost = cost;
    }

    public String getProcedure_id() {
        return procedure_id;
    }

    public void setProcedure_id(String procedure_id) {
        this.procedure_id = procedure_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "QueryProcedureVo{" +
                "procedure_id='" + procedure_id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                ", cost=" + cost +
                '}';
    }
}
