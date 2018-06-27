package com.yxdtyut.security.core.validator.sms;

import com.yxdtyut.security.core.properties.SecurityProperties;
import com.yxdtyut.security.core.validator.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : yangxudong
 * @Description :   图形验证码生成器
 * @Date : 下午3:09 2018/6/26
 */
@Data
@Component
public class SmsCodeGenerator implements ValidateCodeGenerator{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode createCode(HttpServletRequest request) {
        String number = RandomStringUtils.randomNumeric(6);
        return new ValidateCode(number,60);
    }
}
