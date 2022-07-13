package com.example.mes.dataAnalysis.Service;

import com.example.mes.dataAnalysis.Vo.MaterialStockChange;
import com.example.mes.plan.entity.Plan;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface IDataAnalysisService {

    //根据预计完成日期获得当天预计完成的客户订单号
    List<String> getDemandFormNosByDate(String date);



    HashMap<String,Object> getPlanByProductId(String product_id);

    //根据客户订单号获得订单项的完成情况
    HashMap<String,Object>getFinishInfoById(String no);
    //获得库存信息
    HashMap<String,Object> getMaterialStock(int pageOffset,int pageSize);
    //根据物料信息获取库存信息
    HashMap<String,Object> getMaterialStockByInfo(String name,String size);

    HashMap<String, Object> getMaterialStockChanges(String material_id);

    boolean addMaterialStockChanges(String date);


}
