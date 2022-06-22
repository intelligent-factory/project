package com.example.mes.plan.dao;

import com.example.mes.plan.common.MesBaseMapper;
import com.example.mes.plan.entity.MaterialApplication;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface MaterialApplicationMapper extends MesBaseMapper<MaterialApplication> {
	
	Set<MaterialApplication> selectByPlanId(@Param("id") String id);
	
	Set<String> selectMaterialIds(@Param("productId") String productId);
	
	Integer selectMaterialCount(@Param("productId") String productId,@Param("materialId") String materialId);
}
