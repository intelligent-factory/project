package com.example.mes.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mes.system.entity.Company;
import com.example.mes.system.entity.Vo.CompanySelectVo;
import com.example.mes.system.entity.Vo.CompanyUpdateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CompanyMapper extends BaseMapper<Company> {
    List<Company> queryCompanyList(@Param("companySelectVo") CompanySelectVo companySelectVo, @Param("numStart") int numStart, @Param("numEnd") int numEnd);

    Integer getLastCount();

    List<String> queryAllPermissionName();

    void companyDelete(@Param("company") Company company);

    Company companyFind(String company_name);

    void companyUpdate(@Param("n") CompanyUpdateVo companyUpdateVo);

    void companyInsert(@Param("n") CompanyUpdateVo companyUpdateVo);
}
