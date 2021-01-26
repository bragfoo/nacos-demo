package com.liuhx.nacos.demo.springclouddemo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import com.liuhx.nacos.demo.springclouddemo.entity.po.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByPhone(String phone);
    List<User> findByDisplay(int i);
}
