package com.example.mes.process.Service;

import com.example.mes.process.Vo.TransactionVo.InsertVo;
import com.example.mes.process.Vo.TransactionVo.QueryVo;
import com.example.mes.process.Vo.TransactionVo.UpdateVo;

public interface ITransactionService {

    //增加产品、工艺路线概述、工艺路线详细信息、物料清单
    String add(InsertVo insertVo);
    //修改产品、工艺路线概述、工艺路线详细信息、物料清单
    String update(UpdateVo updateVo);
    //根据产品id获得产品的工艺路线概述、工艺路线具体信息、物料清单信息
    QueryVo getInfosByID(String product_id);


}
