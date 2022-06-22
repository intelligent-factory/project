package com.example.mes.system.entity.Vo;

import lombok.Data;

@Data
public class PermissionSelectVo {
    private String filter_permission_name;
    private Integer pageSize;
    private Integer pageNum;
    private String sort;
    private String sortMethod;
}
