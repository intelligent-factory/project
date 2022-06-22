package com.example.mes.dataAnalysis.Mapper;

import com.example.mes.dataAnalysis.Vo.IDPair;
import com.example.mes.dataAnalysis.Vo.MaterialStock;
import com.example.mes.dataAnalysis.Vo.MaterialStockChange;
import com.example.mes.dataAnalysis.Vo.ProductionSchedule;
import com.example.mes.process.Vo.PageVo.PageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataAnalysisMapper {


    //根据完成日期，获得客户需求单号
    List<String> getDemandFormNosByDate(@Param("date") String date);
    //跟据客户需求单号，获得需求单号，获得需求单中的产品合对应的生产订单
    List<IDPair> getIDPairs(@Param("no") String no);
    //根据需求单号，获得该需求单号的完成情况
    int getProducedQuantity(@Param("demand_form_id") String demand_form_id);

    int getDemandQuantity(@Param("demand_form_id") String demand_form_id);

    //
    String getProductNameByID(@Param("product_id") String product_id);


    //获得库存信息
    List<MaterialStock> getMaterialStock(@Param("pageVo")PageVo pageVo);
    int getCount();
    //根据物料id获得物料库存信息
    MaterialStock getMaterialStockByID(@Param("material_id") String material_id);
    //根据物料信息获取物料id
    String getMaterialIDByInfo(@Param("name") String name,@Param("size") String size,@Param("color") String color);
    //
    MaterialStock getMaterialInfoByID(@Param("material_id") String material_id);

    List<MaterialStockChange> getMaterialStockChanges(@Param("material_id") String material_id);

    boolean addMaterialStockChanges(@Param("materialStockChange") MaterialStockChange materialStockChange);

    List<String> getMaterialIDs();
}
