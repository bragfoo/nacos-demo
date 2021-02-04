package com.liuhx.nacos.common.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import com.liuhx.nacos.common.entity.po.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByPhone(String phone);
    List<User> findByDisplay(int i);
}
