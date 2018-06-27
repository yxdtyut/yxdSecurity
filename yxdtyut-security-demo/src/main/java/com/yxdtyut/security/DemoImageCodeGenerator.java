package com.yxdtyut.security;

import com.yxdtyut.security.core.validator.ValidateCodeGenerator;
import com.yxdtyut.security.core.validator.image.ImageCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午3:25 2018/6/26
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator{

    @Override
    public ImageCode createCode(HttpServletRequest request) {
        System.out.println("更高级的图形验证码生成逻辑");
        return null;
    }
}
