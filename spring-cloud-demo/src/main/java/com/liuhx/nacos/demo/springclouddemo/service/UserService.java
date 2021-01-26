package com.liuhx.nacos.demo.springclouddemo.service;

import java.util.List;

import com.liuhx.nacos.demo.springclouddemo.entity.po.User;
import com.liuhx.nacos.demo.springclouddemo.entity.vo.request.LoginRequestVo;
import com.liuhx.nacos.demo.springclouddemo.entity.vo.response.LoginResponseVo;

public interface UserService {
    LoginResponseVo login(LoginRequestVo loginRequestVo);

    Boolean loginOut(String userId,String token);

    User getToken(String token);

    User addUser(User user);

    int updateUser(User user);

    int deleteUser(String id,String userId);

    int updatePassword(String userId,String oldPassword,String newPassword);

    List<User> findUser();
}
