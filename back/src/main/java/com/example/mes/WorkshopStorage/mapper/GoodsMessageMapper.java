package com.example.mes.WorkshopStorage.mapper;

import com.example.mes.WorkshopStorage.vo.GoodsMessageVo;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface GoodsMessageMapper {
    List<GoodsMessageVo> getById(String storage_id, String shelf_id, String location);
    List<GoodsMessageVo> getByShelf(String storage_id, String shelf_id);
    List<GoodsMessageVo> getOrderByGoods(String id, String types);
    List<GoodsMessageVo> getByTime(Timestamp created_time);
    void delete(String uuid, Timestamp modified_time);
    void insert(GoodsMessageVo goodsMessageVo);
    void removeByTime(String id, Timestamp created_time, Timestamp modified_time, String user);
}
