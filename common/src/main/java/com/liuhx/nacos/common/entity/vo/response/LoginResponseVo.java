package com.liuhx.nacos.common.entity.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program data-cleansing
 * @description: 登录返回vo
 * @author: liuhx
 * @create: 2020/07/13 11:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseVo {
    private String token;
    private int auth;
    private String name;
}
