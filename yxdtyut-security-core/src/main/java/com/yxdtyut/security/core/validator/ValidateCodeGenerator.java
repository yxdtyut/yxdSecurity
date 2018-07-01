package com.yxdtyut.security.core.validator;

import com.yxdtyut.security.core.validator.sms.ValidateCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : yangxudong
 * @Description :   验证码生成的接口
 * @Date : 下午3:09 2018/6/26
 */

public interface ValidateCodeGenerator {
    /**
     * @Author : yangxudong
     * @Description : 生成验证码
     * @param null
     * @Date : 下午2:31 2018/7/1
     */
    ValidateCode createCode(HttpServletRequest request);


}
