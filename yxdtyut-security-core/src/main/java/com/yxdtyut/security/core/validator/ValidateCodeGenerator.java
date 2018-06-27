package com.yxdtyut.security.core.validator;

import com.yxdtyut.security.core.validator.sms.ValidateCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : yangxudong
 * @Description :   验证码生成的接口
 * @Date : 下午3:09 2018/6/26
 */

public interface ValidateCodeGenerator {
    ValidateCode createCode(HttpServletRequest request);
}
