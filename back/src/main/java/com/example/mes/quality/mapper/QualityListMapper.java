package com.example.mes.quality.mapper;

import com.example.mes.quality.bean.QualityListBean;
import com.example.mes.quality.bean.Vo.QualityListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QualityListMapper {

    //查找所有质检任务
    List<QualityListBean> selectAll();

    //根据状态查询质检任务
    List<QualityListBean> selectListByStatus();

    //根据产线查询质检任务
    List<QualityListBean> selectListByLine();

    //根据车间查询质检任务
    List<QualityListBean> selectListByWorkShop();

    //某质检任务生产的产品数量
    Integer selectListNum();

    //下面这4个方法是查询所需的User
    List<QualityListBean> queryQualityList(@Param("qualityListVo") QualityListVo qualityListVo, @Param("numStart") int numStart, @Param("numEnd") int numEnd);

    Integer getLastCount();

    //根据list_id查询质检任务
    QualityListBean queryQualityByListID(@Param("list_id") String list_id);

    //修改状态
    void updateQualityListStatus(String list_id);

    //查询所有质检任务中的车间
    List<String> getAllWorkshop();

    //查询所有质检任务中的产线
    List<String> getAllLine();

    //查询车间中的产线
    List<String> getAllLinesByWorkshop(@Param("workshop_id") String workshop_id);
}
