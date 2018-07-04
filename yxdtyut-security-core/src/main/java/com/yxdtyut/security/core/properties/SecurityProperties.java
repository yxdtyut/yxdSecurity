package com.yxdtyut.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author : yangxudong
 * @Description :   模块配置信息
 * @Date : 下午3:44 2018/6/25
 */
@ConfigurationProperties(prefix = "yxdtyut.security")
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties validate = new ValidateCodeProperties();
    private SocialProperties social = new SocialProperties();
}
