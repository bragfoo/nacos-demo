package com.liuhx.nacos.nacos.consumer.springcloudconsumer.entity.po;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class User {
    private String id;
    private String phone;
    @JsonIgnore
    private String password;
    // 1 管理员 2 编辑者
    private int auth;
    private String name;
    // 1 正常 2 删除
    private int display = 1;
    private LocalDateTime createDate;

}
