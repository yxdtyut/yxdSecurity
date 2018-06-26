package com.yxdtyut.security.core.properties;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   验证码配置信息
 * @Date : 下午2:42 2018/6/26
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
}
