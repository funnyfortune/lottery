package com.tc.draw.demo.api.sys.model.dto;

import lombok.Data;

/**
 * 用户登录表单
 * @author TC
 * @date:   2019年1月1日 下午8:54:59
 */
@Data
public class UserLoginDTO {

    //账号
    private String account;

    //密码
    private String password;

    //验证码
    private String verify;

}
