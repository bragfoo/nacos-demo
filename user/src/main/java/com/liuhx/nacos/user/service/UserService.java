package com.liuhx.nacos.user.service;

import java.util.List;

import com.liuhx.nacos.common.entity.po.User;
import com.liuhx.nacos.common.entity.vo.request.LoginRequestVo;
import com.liuhx.nacos.common.entity.vo.response.LoginResponseVo;

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
