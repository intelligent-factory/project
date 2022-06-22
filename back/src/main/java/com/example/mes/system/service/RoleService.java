package com.example.mes.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mes.system.entity.Role;
import com.example.mes.system.entity.Vo.RoleSelectVo;
import com.example.mes.system.entity.Vo.RoleUpdateVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService extends IService<Role> {
    public List<Role> queryRoleList(RoleSelectVo roleSelectVo);

    public Integer getLastCount();

    public List<String> queryAllPermissionName();

    public void roleDelete(Role role, int Handler);

    public Role roleFind(RoleUpdateVo roleUpdateVo);

    public void roleUpdate(RoleUpdateVo roleUpdateVo);

    void roleInsert(RoleUpdateVo roleUpdateVo);
}
