package com.example.mes.WorkshopStorage.mapper;

import com.example.mes.WorkshopStorage.vo.StorageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StorageMapper {
    Integer getCount();
    void create(StorageVo storageVo);
    List<StorageVo> selectAll (@Param("start") long start, @Param("num") long num);
    List<StorageVo> all_storage();
    StorageVo search(@Param("storage_id") String storage_id);
    void delete(@Param("storage_id") String storage_id, @Param("user") String user, @Param("modified_time") Timestamp modified_time);
}
