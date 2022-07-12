package com.example.mes.system.controller;

import com.example.mes.system.entity.Apply;
import com.example.mes.system.entity.User;
import com.example.mes.system.entity.Vo.UserDeleteVo;
import com.example.mes.system.entity.Vo.UserUpdateVo;
import com.example.mes.system.entity.Vo.UserVo;
import com.example.mes.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/data/systemManagement")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping("/findUserAll")
    public List<User> findUserAll() {
        return userService.findUserAll();
    }

    @PostMapping("/userList")
    public Object queryUserList(
            @RequestBody UserVo userVo
    ) {
        List<User> userList = userService.queryUserList(userVo);
        int userListCount = userService.getLastCount(userVo.user.getCompany_id());
        List<String> roleName = userService.queryAllRoleName(userVo);
        List<String> departmentName = userService.queryAllDepartmentName(userVo);
        HashMap<String, Object> res = new HashMap<>();
        res.put("userList", userList);
        res.put("total", userListCount);
        res.put("roleList", roleName);
        res.put("departmentList", departmentName);
        MyUtils.success(res);
        return res;
    }

    @PostMapping("/userDelete")
    public Object deleteUser(
            @RequestBody UserDeleteVo userDeleteVo
            ) {
        HashMap<String, Object> temp = new HashMap<>();
        for (User user : userDeleteVo.getUserList()) {
            temp = userService.deleteUsers(user,userDeleteVo.getUser().getId());
        }
        HashMap<String, Object> res = new HashMap<>();
        MyUtils.success(res);
        return res;
    }

    @PostMapping("/userUpdate")
    public Object userUpdate(
            @RequestBody UserUpdateVo userUpdateVo
    ) {
        HashMap<String, Object> res = new HashMap<>();
        String Method = userUpdateVo.request;
        int id = userUpdateVo.id;
        User user = userService.findUserById(id,userUpdateVo.user.getCompany_id());
        if (Method.equals("insert")) {
            if (user != null) {
                MyUtils.fail(res, "该id已经占用，请勿重复创建");
            } else {
                userUpdateVo.company_id = userUpdateVo.user.getCompany_id();
                userService.userAdd(userUpdateVo);
                MyUtils.success(res);
            }
        }
        if (Method.equals("update")) {
            if (user == null) {

                MyUtils.fail(res, "该id未创建");
            } else {
                if (userUpdateVo.isChangeDepartment == 0) {
                    userUpdateVo.company_id = userUpdateVo.user.getCompany_id();
                    userService.userUpdate(userUpdateVo);
                    MyUtils.success(res);
                }
                if (userUpdateVo.isChangeDepartment == 1) {
                    userUpdateVo.company_id = userUpdateVo.user.getCompany_id();
                    Apply apply = userService.findApply(userUpdateVo);
                    if (apply != null) {
                        MyUtils.fail(res, "已经申请过，请勿重复申请");
                    } else {
                        userUpdateVo.company_id = userUpdateVo.user.getCompany_id();
                        userService.userApply(userUpdateVo);
                        userUpdateVo.department = userService.findUserDepartment(userUpdateVo);
                        userService.userUpdate(userUpdateVo);
                        MyUtils.successMsg(res, "申请成功");
                    }
                }
            }
        }
        return res;
    }

}
