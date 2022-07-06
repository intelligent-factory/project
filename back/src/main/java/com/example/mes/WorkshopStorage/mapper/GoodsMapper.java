package com.example.mes.WorkshopStorage.mapper;

import com.example.mes.WorkshopStorage.vo.ApplyVo;
import com.example.mes.WorkshopStorage.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface GoodsMapper {
    GoodsVo getById (@Param("storage_id") String storage_id, @Param("shelf_id") String shelf_id, @Param("id")String id);
    List<GoodsVo> getByShelfId (@Param("storage_id") String storage_id, @Param("shelf_id") String shelf_id);
    Integer getGoodsNumById(@Param("storage_id") String storage_id, @Param("shelf_id") String shelf_id);
    void insert(GoodsVo params);
    void updateQuantity(String storage_id, String shelf_id, String id, int quantity, Timestamp modified_time);
    List<GoodsVo> getApplyByShelf(@Param("storage_id") String storage_id, @Param("shelf_id") String shelf_id);
    void removeById(String storage_id, String shelf_id, String id, int quantity, Timestamp modified_time, String user);
    void delete(String storage_id, String shelf_id, String id, Timestamp modified_time);
    String getMaterial(String id);
    String getEquipment(String id);
    void addApply(ApplyVo applyVo);
    List<ApplyVo> selectAll(long start,long num);
    List<ApplyVo> selectAll_search(long start,long num, String goods_id, String goods_name, Date time1, Date time2, String type);
    List<ApplyVo> selectsetAll(long start,long num);
    List<ApplyVo> selectConfirmAll(long start,long num);
    List<ApplyVo> selectMessageAll(long start,long num);
    List<ApplyVo> selectAddAll(long start,long num);
    List<GoodsVo> selectGoodsAll(long start,long num, String storage_id, String shelf_id);
    List<GoodsVo> selectLocationAll(long start,long num, String type, String id);
    List<ApplyVo> getOrderByTime(String uuid);
    Integer searchQuantity(String goods_id, String types);
    Integer getApplyCount();
    Integer getApplyCount_search(String goods_id, String goods_name, Date time1, Date time2, String type);
    Integer getsetApplyCount();
    Integer getConfirmCount();
    Integer getMessageCount();
    Integer getGoodsCount(String storage_id, String shelf_id);
    Integer getAddCount();
    Integer getLocationCount(String type, String id);
    void setApply(String uuid, String user, Timestamp timestamp);
    ApplyVo getByUuid(String uuid);
    void managerConfirm(String uuid, String message, String user, Timestamp timestamp);
    void checkDelete();
    void removeApply(String uuid, String user, Timestamp timestamp);
}
