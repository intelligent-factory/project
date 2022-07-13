package com.example.mes.dataAnalysis.Service.Impl;

import com.example.mes.dataAnalysis.Mapper.DataAnalysisMapper;
import com.example.mes.dataAnalysis.Service.IDataAnalysisService;
import com.example.mes.dataAnalysis.Vo.IDPair;
import com.example.mes.dataAnalysis.Vo.MaterialStock;
import com.example.mes.dataAnalysis.Vo.MaterialStockChange;
import com.example.mes.dataAnalysis.Vo.ProductionSchedule;
import com.example.mes.process.Vo.PageVo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class DataAnalysisService implements IDataAnalysisService {

    @Autowired(required = false)
    DataAnalysisMapper mapper;
    @Override
    public List<String> getDemandFormNosByDate(String date) {
        try {
            return mapper.getDemandFormNosByDate(date);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service:根据预计完成日期获得预计完成客户订单号失败！");
            return null;
        }
    }

    @Override
    public HashMap<String, Object> getFinishInfoById(String no) {
        try {
            List<IDPair> idPairs = mapper.getIDPairs(no);
            ArrayList<String> categorys = new ArrayList<>();
            ArrayList<Integer> realCounts = new ArrayList<>();
            ArrayList<Integer> planCounts = new ArrayList<>();
            for(IDPair idPair:idPairs){
                String name = mapper.getProductNameByID(idPair.getProduct_id());
                ProductionSchedule productionSchedule = new ProductionSchedule(mapper.getDemandQuantity(idPair.getId()),mapper.getProducedQuantity(idPair.getId()));
                categorys.add(name);
                realCounts.add(productionSchedule.getProduced_quantity());
                planCounts.add(productionSchedule.getDemand_quantity());
            }
            HashMap<String,Object> data = new HashMap<>();
            data.put("categorys",categorys);
            data.put("realCounts",realCounts);
            data.put("planCounts",planCounts);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service:获取生产进度失败！");
            return null;
        }
    }

    @Override
    public HashMap<String,Object> getMaterialStock(int pageOffset, int pageSize) {
        try {
            HashMap<String,Object> data = new HashMap<>();
            List<MaterialStock> materials = mapper.getMaterialStock(new PageVo(pageOffset,pageSize));
            for(MaterialStock materialStock:materials){
                MaterialStock i = mapper.getMaterialInfoByID(materialStock.getMaterial_id());
                materialStock.setName(i.getName());
                materialStock.setColor(i.getColor());
                materialStock.setSize(i.getSize());
            }
            int count = mapper.getCount();
            data.put("materials",materials);
            data.put("count",count);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service:获取库存信息失败！");
            return null;
        }
    }

    @Override
    public HashMap<String, Object> getMaterialStockByInfo(String name, String size, String color) {
        try {
            String material_id = mapper.getMaterialIDByInfo(name,size,color);
            HashMap<String,Object> data = new HashMap<>();
            ArrayList<MaterialStock> materials = new ArrayList<>();
            MaterialStock materialStock = mapper.getMaterialStockByID(material_id);
            if (materialStock == null){
                materialStock = new MaterialStock(material_id,name,size,color,0);
            }else{
                materialStock.setName(name);
                materialStock.setSize(size);
                materialStock.setColor(color);
            }
            materials.add(materialStock);
            data.put("materials",materials);
            data.put("count",1);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service:根据物料信息获取库存信息失败！");
            return null;
        }
    }

    @Override
    public HashMap<String, Object> getMaterialStockChanges(String material_id) {
        try {
            List<MaterialStockChange> lists = mapper.getMaterialStockChanges(material_id);
            ArrayList<String> dates = new ArrayList<>();
            ArrayList<Integer> stocks = new ArrayList<>();
            for(MaterialStockChange item:lists){
                dates.add(item.getDate().substring(5,10));
                stocks.add(item.getStock());
            }
            Collections.reverse(dates);
            Collections.reverse(stocks);
            //获得当前的物料库存
            MaterialStock materialStock = mapper.getMaterialStockByID(material_id);
            int stock;
            if(materialStock==null)
                stock=0;
            else
                stock = mapper.getMaterialStockByID(material_id).getCount();
            dates.add("当前");
            stocks.add(stock);

            HashMap<String,Object> data = new HashMap<>();
            data.put("dates",dates);
            data.put("stocks",stocks);
            return data;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service:获取物料库存变化列表失败！");
            return null;
        }
    }


    //将指定日期的库存写入库存统计表
    @Override
    public boolean addMaterialStockChanges(String date) {
        try {
            List<String> lists = mapper.getMaterialIDs();
            for(String material_id:lists){
                MaterialStock materialStock = mapper.getMaterialStockByID(material_id);
                int stock;
                if(materialStock==null)
                    stock=0;
                else
                    stock = mapper.getMaterialStockByID(material_id).getCount();
                MaterialStockChange materialStockChange = new MaterialStockChange(date,stock);
                materialStockChange.setMaterial_id(material_id);
                mapper.addMaterialStockChanges(materialStockChange);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println();
            return false;
        }
    }

}
