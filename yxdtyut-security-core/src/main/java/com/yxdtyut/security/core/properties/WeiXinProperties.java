package com.yxdtyut.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author : yangxudong
 * @Description :   weixin相关的属性
 * @Date : 下午4:34 2018/7/2
 */
@Data
public class WeiXinProperties extends SocialProperties{
    /** 服务提供商标识.*/
    private String providerId = "weixin";
}
