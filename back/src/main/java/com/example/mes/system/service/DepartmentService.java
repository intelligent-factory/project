package com.example.mes.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mes.system.entity.Department;
import com.example.mes.system.entity.Vo.DepartmentCountVo;
import com.example.mes.system.entity.Vo.DepartmentSelectVo;
import com.example.mes.system.entity.Vo.DepartmentUpdateVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService extends IService<Department> {
    List<Department> queryDepartmentList(DepartmentSelectVo departmentSelectVo);

    int getLastCount();

    void departmentDelete(Department department,int handler);

    Department departmentFind(DepartmentUpdateVo departmentUpdateVo);

    void departmentUpdate(DepartmentUpdateVo departmentUpdateVo);

    void departmentAdd(DepartmentUpdateVo departmentUpdateVo);


    List<DepartmentCountVo> getDepartmentCount();
}
