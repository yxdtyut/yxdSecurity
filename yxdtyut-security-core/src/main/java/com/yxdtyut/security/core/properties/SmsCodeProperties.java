package com.yxdtyut.security.core.properties;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   短信验证码配置信息
 * @Date : 下午2:42 2018/6/26
 */
@Data
public class SmsCodeProperties {
    private Integer length=6;
    private Integer expireIn = 60;
    private String urls;
}
