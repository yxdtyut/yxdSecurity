package com.yxdtyut.security.core.properties;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   图片验证码配置信息
 * @Date : 下午2:42 2018/6/26
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {
    private Integer width=67;
    private Integer height=23;

    public ImageCodeProperties() {
        setLength(4);
    }
}
