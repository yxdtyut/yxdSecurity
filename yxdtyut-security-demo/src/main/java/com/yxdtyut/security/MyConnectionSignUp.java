package com.yxdtyut.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/**
 * @Author : yangxudong
 * @Description :   自动创建用户并绑定社交账号
 * @Date : 下午3:32 2018/7/3
 */
//@Component
public class MyConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回唯一标识
        return connection.getDisplayName();
    }
}
