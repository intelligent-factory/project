package com.example.mes.process.Vo.TransactionVo;

public class InsertRoutingProcedureVo {

    String id;
    String content;

    public InsertRoutingProcedureVo() {
    }

    public InsertRoutingProcedureVo(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "InsertRoutingProcedureVo{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
