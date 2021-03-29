package com.liuhx.nacos.user.service;

import com.liuhx.nacos.common.entity.po.User;

public interface UserBloomFilterService {
    void insert(String userId);
    Boolean checkUser(String userId);
}
