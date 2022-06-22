package com.example.mes.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mes.system.entity.Department;
import com.example.mes.system.entity.Vo.DepartmentCountVo;
import com.example.mes.system.entity.Vo.DepartmentSelectVo;
import com.example.mes.system.entity.Vo.DepartmentUpdateVo;
import com.example.mes.system.mapper.DepartmentMapper;
import com.example.mes.system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> queryDepartmentList(DepartmentSelectVo departmentSelectVo) {
        int numStart = MyImplUtils.getNumStart(departmentSelectVo.getPageNum(), departmentSelectVo.getPageSize());
        int numEnd = MyImplUtils.getNumEnd(departmentSelectVo.getPageSize());
        return departmentMapper.queryDepartmentList(departmentSelectVo, numStart, numEnd);
    }

    @Override
    public int getLastCount() {
        return departmentMapper.getLastCount();
    }

    @Override
    public void departmentDelete(Department department,int handler) {
        department.setModified_by(Integer.toString(handler));
        department.setModified_time(MyImplUtils.getCurrentTime());
        departmentMapper.departmentDelete(department);
    }

    @Override
    public Department departmentFind(DepartmentUpdateVo departmentUpdateVo) {
        return departmentMapper.departmentFind(departmentUpdateVo.getDepartment_name());
    }

    @Override
    public void departmentUpdate(DepartmentUpdateVo departmentUpdateVo) {
        departmentUpdateVo.setModified_by(Integer.toString(departmentUpdateVo.getUser().getId()));
        departmentUpdateVo.setModified_time(MyImplUtils.getCurrentTime());
        departmentMapper.departmentUpdate(departmentUpdateVo);
    }

    @Override
    public void departmentAdd(DepartmentUpdateVo departmentUpdateVo) {

        departmentUpdateVo.setCreated_time(MyImplUtils.getCurrentTime());
        departmentUpdateVo.setModified_time(departmentUpdateVo.getCreated_time());
        departmentUpdateVo.setCreated_by(Integer.toString(departmentUpdateVo.getUser().getId()));
        departmentUpdateVo.setModified_by(departmentUpdateVo.getCreated_by());
        departmentUpdateVo.setStatus("0");
        departmentUpdateVo.setIs_deleted("0");
        departmentMapper.departmentInsert(departmentUpdateVo);

    }

    @Override
    public List<DepartmentCountVo> getDepartmentCount() {
        return departmentMapper.getDepartmentCount();
    }
}
