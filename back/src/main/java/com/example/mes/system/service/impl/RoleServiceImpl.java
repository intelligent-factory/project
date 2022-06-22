package com.example.mes.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mes.system.entity.Role;
import com.example.mes.system.entity.Vo.RoleSelectVo;
import com.example.mes.system.entity.Vo.RoleUpdateVo;
import com.example.mes.system.mapper.RoleMapper;
import com.example.mes.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryRoleList(RoleSelectVo roleSelectVo) {
        int numStart = MyImplUtils.getNumStart(roleSelectVo.pageNum, roleSelectVo.pageSize);
        int numEnd = MyImplUtils.getNumEnd(roleSelectVo.pageSize);
        return roleMapper.queryRoleList(roleSelectVo, numStart, numEnd);
    }

    @Override
    public Integer getLastCount() {
        return roleMapper.getLastCount();
    }

    @Override
    public List<String> queryAllPermissionName() {
        return roleMapper.queryAllPermissionName();
    }

    @Override
    public void roleDelete(Role role, int handler) {
        role.setModified_by(Integer.toString(handler));
        role.setModified_time(MyImplUtils.getCurrentTime());
        roleMapper.roleDelete(role);

    }

    @Override
    public Role roleFind(RoleUpdateVo roleUpdateVo) {
        return roleMapper.roleFind(roleUpdateVo.getRole_name());
    }

    @Override
    public void roleUpdate(RoleUpdateVo roleUpdateVo) {
        roleUpdateVo.setModified_by(Integer.toString(roleUpdateVo.getUser().getId()));
        roleUpdateVo.setModified_time(MyImplUtils.getCurrentTime());
        roleMapper.roleUpdate(roleUpdateVo);
    }

    @Override
    public void roleInsert(RoleUpdateVo roleUpdateVo) {
        roleUpdateVo.setCreated_time(MyImplUtils.getCurrentTime());
        roleUpdateVo.setModified_time(roleUpdateVo.getCreated_time());
        roleUpdateVo.setCreated_by(Integer.toString(roleUpdateVo.getUser().getId()));
        roleUpdateVo.setModified_by(roleUpdateVo.getCreated_by());
        roleUpdateVo.setStatus("0");
        roleUpdateVo.setIs_deleted("0");
        roleMapper.roleInsert(roleUpdateVo);
    }
}
