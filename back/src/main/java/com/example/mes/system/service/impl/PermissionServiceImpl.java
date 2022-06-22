package com.example.mes.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mes.system.entity.Permission;
import com.example.mes.system.entity.Vo.PermissionSelectVo;
import com.example.mes.system.entity.Vo.PermissionUpdateVo;
import com.example.mes.system.mapper.PermissionMapper;
import com.example.mes.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<String> findByName(PermissionSelectVo permissionSelectVo) {
        int numStart = MyImplUtils.getNumStart(permissionSelectVo.getPageNum(), permissionSelectVo.getPageSize());
        int numEnd = MyImplUtils.getNumEnd(permissionSelectVo.getPageSize());

        return permissionMapper.findByName(permissionSelectVo, numStart, numEnd);
    }

    @Override
    public int getLastCount() {
        return permissionMapper.getLastCount();
    }

    @Override
    public List<String> findAccessName(String permission_name) {
        return permissionMapper.findAccessName(permission_name);
    }

    @Override
    public List<String> findAllAccessName() {
        return permissionMapper.FindAllAccessName();
    }

    @Override
    public void permissionDelete(String permissionName, int handler) {
        Timestamp currentTime = MyImplUtils.getCurrentTime();
        String modified_by = Integer.toString(handler);
        permissionMapper.permissionDelete(permissionName, currentTime, modified_by);
    }

    @Override
    public String permissionFind(PermissionUpdateVo permissionUpdateVo) {
        String permission_name = permissionUpdateVo.getPermission_name();
        return permissionMapper.permissionFind(permission_name);
    }

    @Override
    public void permissionUpdate(PermissionUpdateVo permissionUpdateVo) {
        Timestamp currentTime = MyImplUtils.getCurrentTime();
        String modified_by = Integer.toString(permissionUpdateVo.getUser().getId());
        String status = "0";
        String is_deleted = "0";
        List<String> accessList = permissionUpdateVo.getAccessList();
        String permissionName = permissionUpdateVo.getPermission_name();
        for (String accessName : accessList) {
            permissionMapper.permissionUpdate(permissionName, accessName, currentTime, modified_by, status, is_deleted);

        }

    }

    @Override
    public void permissionInsert(PermissionUpdateVo permissionUpdateVo) {
        Timestamp currentTime = MyImplUtils.getCurrentTime();
        String modified_by = Integer.toString(permissionUpdateVo.getUser().getId());
        String status = "0";
        String is_deleted = "0";
        List<String> accessList = permissionUpdateVo.getAccessList();
        String permissionName = permissionUpdateVo.getPermission_name();
        for (String accessName : accessList) {
            permissionMapper.permissionUpdate(permissionName, accessName, currentTime, modified_by, status, is_deleted);
        }
    }

    @Override
    public void permissionAllDelete(PermissionUpdateVo permissionUpdateVo) {
        String name = permissionUpdateVo.getPermission_name();
        permissionMapper.permissionAllDelete(name);
    }
}
