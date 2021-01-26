package com.liuhx.nacos.demo.springclouddemo.entity.po;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program data-cleansing
 * @description: 用户实体类
 * @author: liuhx
 * @create: 2020/07/09 18:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String phone;
    @JsonIgnore
    private String password;
    // 1 管理员 2 编辑者
    private int auth;
    private String name;
    // 1 正常 2 删除
    private int display = 1;

    @CreatedDate
    private LocalDateTime createDate;

}
