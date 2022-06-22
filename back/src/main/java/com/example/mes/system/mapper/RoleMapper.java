package com.example.mes.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mes.system.entity.Role;
import com.example.mes.system.entity.Vo.RoleSelectVo;
import com.example.mes.system.entity.Vo.RoleUpdateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> queryRoleList(@Param("roleSelectVo") RoleSelectVo roleSelectVo, @Param("numStart") int numStart, @Param("numEnd") int numEnd);

    Integer getLastCount();

    List<String> queryAllPermissionName();

    void roleDelete(@Param("role") Role role);

    Role roleFind(String role_name);

    void roleUpdate(@Param("n") RoleUpdateVo roleUpdateVo);

    void roleInsert(@Param("n") RoleUpdateVo roleUpdateVo);
}
