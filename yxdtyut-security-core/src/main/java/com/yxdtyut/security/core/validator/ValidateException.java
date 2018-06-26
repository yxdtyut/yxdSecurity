package com.yxdtyut.security.core.validator;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author : yangxudong
 * @Description :   自定义验证异常
 * @Date : 上午10:27 2018/6/26
 */

public class ValidateException extends AuthenticationException {

    public ValidateException(String msg) {
        super(msg);
    }
}
