package com.liuhx.nacos.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.liuhx.nacos.common.entity.po.User;
import com.liuhx.nacos.common.entity.vo.request.LoginRequestVo;
import com.liuhx.nacos.common.entity.vo.response.LoginResponseVo;
import com.liuhx.nacos.user.service.UserService;

import java.util.List;

/**
 * @program data-cleansing
 * @description: 用户controller
 * @author: liuhx
 * @create: 2020/07/13 14:34
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    HttpServletRequest request;
    @Resource
    UserService userService;

    @PostMapping("login")
    public LoginResponseVo login(@Valid LoginRequestVo loginRequestVo) {
        return userService.login(loginRequestVo);
    }

    @PostMapping("loginOut")
    public Boolean loginOut(){
        String userId = request.getAttribute("userId").toString();
        String token  = request.getHeader("token");
        return userService.loginOut(userId,token);
    }

    @PostMapping("insert")
    public User addUser(User user) {
        return userService.addUser(user);
    }

    @PostMapping("update")
    public int updateUser(User user) {
        return userService.updateUser(user);
    }

    @PostMapping("delete")
    public int deleteUser(String id) {
        String userId = request.getAttribute("userId").toString();
        return userService.deleteUser(id, userId);
    }

    @GetMapping("findAllUser")
    public List<User> findAllUser() {
        return userService.findUser();
    }

    @PostMapping("updatePassword")
    public int updatePassword(String oldPassword,String newPassword){
        String userId = request.getAttribute("userId").toString();
        return userService.updatePassword(userId,oldPassword,newPassword);
    }
}
