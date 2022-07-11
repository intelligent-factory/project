package com.example.mes.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mes.system.entity.Apply;
import com.example.mes.system.entity.User;
import com.example.mes.system.entity.Vo.UserUpdateVo;
import com.example.mes.system.entity.Vo.UserVo;
import com.example.mes.system.mapper.UserMapper;
import com.example.mes.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findUserAll() {
        return userMapper.findUserAll();
    }

    @Override
    public List<User> queryUserList(UserVo userVo) {
        int numStart = MyImplUtils.getNumStart(userVo.pageNum, userVo.pageSize);
        int numEnd = MyImplUtils.getNumEnd(userVo.pageSize);
        return userMapper.queryUserList(userVo, numStart, numEnd);
    }


    @Override
    public List<String> queryAllRoleName(UserVo userVo) {
        return userMapper.queryAllRoleName(userVo);
    }

    @Override
    public Integer getLastCount() {
        return userMapper.getLastCount();
    }

    @Override
    public List<String> queryAllDepartmentName() {
        return userMapper.queryAllDepartmentName();
    }

    @Override
    public HashMap<String, Object> deleteUsers(User user,int handler) {
        HashMap<String, Object> x = new HashMap<>();
        user.setModified_by(Integer.toString(handler));
        user.setModified_time(MyImplUtils.getCurrentTime());
        int id = user.getId();
        Integer is_apply = userMapper.getApply(id);
        if (is_apply == null) {
            userMapper.deleteUsers(user);
            x.put("成功删除", id);
        } else {
            x.put("删除失败，已有申请", id);
        }
        return x;
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void userAdd(UserUpdateVo userUpdateVo) {
        userUpdateVo.modified_time = userUpdateVo.created_time = MyImplUtils.getCurrentTime();
        userUpdateVo.created_by = userUpdateVo.modified_by = Integer.toString(userUpdateVo.user.getId());
        userUpdateVo.status = "0";
        userUpdateVo.is_deleted = "0";
        userUpdateVo.company_id = userUpdateVo.user.getCompany_id();
        userMapper.userAdd(userUpdateVo);
    }

    @Override
    public void userUpdate(UserUpdateVo userUpdateVo) {
        userUpdateVo.modified_time = MyImplUtils.getCurrentTime();
        userUpdateVo.modified_by = Integer.toString(userUpdateVo.user.getId());
        userMapper.userUpdate(userUpdateVo);
    }

    @Override
    public void userApply(UserUpdateVo userUpdateVo) {
        Apply apply = new Apply();
        String toDepartment = userUpdateVo.department;
        String fromDepartment = userMapper.findUserDepartment(userUpdateVo.id);
        String status = "待审核";
        apply.setIs_deleted("0");
        apply.setFrom_department(fromDepartment);
        apply.setTo_department(toDepartment);
        apply.setStatus(status);
        apply.setCreated_time(MyImplUtils.getCurrentTime());
        apply.setCreated_by(userUpdateVo.user.getName());
        apply.setTransfer_id(userUpdateVo.id);
        userMapper.createApply(apply);
    }

    @Override
    public Apply findApply(UserUpdateVo userUpdateVo) {
        return userMapper.findApply(userUpdateVo);
    }

    @Override
    public String findUserDepartment(UserUpdateVo userUpdateVo) {
        return userMapper.findUserDepartment(userUpdateVo.id);
    }


}
