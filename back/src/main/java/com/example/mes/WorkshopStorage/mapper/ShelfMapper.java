package com.example.mes.WorkshopStorage.mapper;

import com.example.mes.WorkshopStorage.vo.ShelfVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ShelfMapper {
    Integer getShelfNumByStorageId(@Param("storage_id") String storage_id);
    Integer getCount();
    ShelfVo getById(@Param("storage_id")String storage_id, @Param("id") String id);
    Integer getCountById(@Param("storage_id") String storage_id);
    void insert(ShelfVo shelfVo);
    void delete(String storage_id, String id, String user, Timestamp modified_time);
    List<ShelfVo> shelfById(@Param("storage_id") String storage_id);
    List<ShelfVo> selectAll (@Param("start") long start, @Param("num") long num);
    List<ShelfVo> selectAllById (@Param("storage_id") String storage_id, @Param("start") long start, @Param("num") long num);
}
