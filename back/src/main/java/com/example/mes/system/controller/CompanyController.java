package com.example.mes.system.controller;

import com.example.mes.system.entity.Company;
import com.example.mes.system.entity.Role;
import com.example.mes.system.entity.Vo.CompanyDeleteVo;
import com.example.mes.system.entity.Vo.CompanySelectVo;
import com.example.mes.system.entity.Vo.CompanyUpdateVo;
import com.example.mes.system.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("data/companyManagement/")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/companyList")
    public Object queryRoleList(
            @RequestBody CompanySelectVo companySelectVo
    ) {
        HashMap<String, Object> res = new HashMap<>();
        List<Company> companyList = companyService.queryCompanyList(companySelectVo);
        int companyListCount = companyService.getLastCount();
        res.put("companyList", companyList);
        res.put("total", companyListCount);
        MyUtils.success(res);
        return res;
    }


    @PostMapping("/companyDelete")
    public Object companyDelete(
            @RequestBody CompanyDeleteVo companyDeleteVo
            ) {
        HashMap<String, Object> res = new HashMap<>();
        for (Company company : companyDeleteVo.getCompanyList()) {
            companyService.companyDelete(company,companyDeleteVo.getUser().getId());
        }
        MyUtils.success(res);
        return res;
    }

    @PostMapping("/companyUpdate")
    public Object companyUpdate(
            @RequestBody CompanyUpdateVo companyUpdateVo
    ) {
        HashMap<String, Object> res = new HashMap<>();
        Company company = companyService.companyFind(companyUpdateVo);
        if (companyUpdateVo.getRequest().equals("update")) {
            if (company == null) {
                MyUtils.fail(res, "该公司不存在");
            } else {
                companyService.companyUpdate(companyUpdateVo);
                MyUtils.success(res);
            }
        } else if (companyUpdateVo.getRequest().equals("insert")) {
            if (company != null) {
                MyUtils.fail(res, "该公司已经存在，请勿重复创建");
            } else {
                companyService.companyInsert(companyUpdateVo);
                MyUtils.success(res);
            }
        }

        return res;
    }
}
