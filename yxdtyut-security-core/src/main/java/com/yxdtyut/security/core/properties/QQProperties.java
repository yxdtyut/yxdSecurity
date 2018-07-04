package com.yxdtyut.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author : yangxudong
 * @Description :   QQ相关的属性
 * @Date : 下午4:34 2018/7/2
 */
@Data
public class QQProperties extends SocialProperties{
    /** 服务提供商标识.*/
    private String providerId = "qq";
}
