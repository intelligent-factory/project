package com.example.mes.system.entity.Vo;

import lombok.Data;

@Data
public class DepartmentSelectVo {
    private String filter_department_name;
    private Integer pageSize;
    private Integer pageNum;
    private String sort;
    private String sortMethod;
}
