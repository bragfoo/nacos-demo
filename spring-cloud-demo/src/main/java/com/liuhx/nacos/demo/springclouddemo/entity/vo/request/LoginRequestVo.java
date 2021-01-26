package com.liuhx.nacos.demo.springclouddemo.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @program data-cleansing
 * @description: 登录请求
 * @author: liuhx
 * @create: 2020/07/13 12:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestVo {
    @NotNull(message = "电话不能为空")
    private String phone;
    @NotNull(message = "密码不能为空")
    private String password;
}
