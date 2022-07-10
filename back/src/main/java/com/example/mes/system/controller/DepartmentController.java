package com.example.mes.system.controller;

import com.example.mes.system.entity.Department;
import com.example.mes.system.entity.Vo.*;
import com.example.mes.system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/data/departmentManagement/")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/departmentList")
    public Object queryRoleList(
            @RequestBody DepartmentSelectVo departmentSelectVo
    ) {
        HashMap<String, Object> res = new HashMap<>();
        List<Department> departmentList = departmentService.queryDepartmentList(departmentSelectVo);
        int departmentListCount = departmentService.getLastCount();
        List<DepartmentCountVo> departmentCountVos = departmentService.getDepartmentCount();
        res.put("departmentCount", departmentCountVos);
        res.put("departmentList", departmentList);
        res.put("total", departmentListCount);
        MyUtils.success(res);
        return res;
    }

    @PostMapping("/departmentDelete")
    public Object departmentDelete(
            @RequestBody DepartmentDeleteVo departmentDeleteVo
            ) {
        HashMap<String, Object> res = new HashMap<>();
        List<Department> departmentList = departmentDeleteVo.getDepartmentList();
        for (Department department : departmentList) {
            departmentService.departmentDelete(department,departmentDeleteVo.getUser().getId());
        }
        MyUtils.success(res);
        return res;
    }

    @PostMapping("/departmentUpdate")
    public Object departmentUpdate(
            @RequestBody newDepartmentUpdateVo newdepartmentUpdateVo
    ) {
        HashMap<String, Object> res = new HashMap<>();
//        Department department = departmentService.departmentFind(newdepartmentUpdateVo);
        Department department = departmentService.newdepartmentFind(newdepartmentUpdateVo);
        if (newdepartmentUpdateVo.getRequest().equals("update")) {
            if (department == null) {
                MyUtils.fail(res, "该部门不存在,无法更新");
            } else {
//                departmentService.departmentUpdate(newdepartmentUpdateVo);
                departmentService.newDepartmentUpdate(newdepartmentUpdateVo);
                MyUtils.success(res);
            }
        } else if (newdepartmentUpdateVo.getRequest().equals("insert")) {
            //增加部门
            if (department != null) {
                MyUtils.fail(res, "该部门已经存在，请勿重复创建");
            } else {
//                departmentService.departmentAdd(newdepartmentUpdateVo);
                departmentService.newDepartmentAdd(newdepartmentUpdateVo);
                MyUtils.success(res);
            }
        }


        return res;
    }


}
