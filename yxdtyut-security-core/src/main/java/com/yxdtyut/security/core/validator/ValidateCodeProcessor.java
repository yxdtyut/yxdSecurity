package com.yxdtyut.security.core.validator;

import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : yangxudong
 * @Description :   验证码处理器
 * @Date : 上午10:19 2018/6/27
 */

public interface ValidateCodeProcessor {

    /** 验证码放入session时的前缀.*/
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    
    /**
     * @Author : yangxudong
     * @Description : 创建校验码
     * @param null
     * @Date : 上午11:14 2018/6/27
     */
    void process(ServletWebRequest request) throws Exception;

    /**
     * @Author : yangxudong
     * @Description : 校验图形验证码
     * @param null
     * @Date : 下午2:32 2018/7/1
     */
    void validate(HttpServletRequest request);
}
