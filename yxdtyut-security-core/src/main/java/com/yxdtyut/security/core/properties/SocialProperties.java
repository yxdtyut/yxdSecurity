package com.yxdtyut.security.core.properties;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   社交登陆相关属性
 * @Date : 下午4:35 2018/7/2
 */
@Data
public class SocialProperties {
    private QQProperties qq = new QQProperties();

    private WeiXinProperties weixin = new WeiXinProperties();

    /** SocialAuthenticationFilter默认拦截的路径.*/
    private String filterProcessesUrl = "/auth";
}
