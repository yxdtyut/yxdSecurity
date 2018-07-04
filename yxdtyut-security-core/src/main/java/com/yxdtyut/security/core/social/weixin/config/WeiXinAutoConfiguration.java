package com.yxdtyut.security.core.social.weixin.config;

import com.yxdtyut.security.core.properties.SecurityProperties;
import com.yxdtyut.security.core.properties.WeiXinProperties;
import com.yxdtyut.security.core.social.YxdtyutConnectionView;
import com.yxdtyut.security.core.social.weixin.connect.WeiXinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * @Author : yangxudong
 * @Description : 微信登录配置
 * @Date : 上午11:00 2018/7/4
 */
@Configuration
@ConditionalOnProperty(prefix = "yxdtyut.security.social.weixin", name = "appId")
public class WeiXinAutoConfiguration extends SocialAutoConfigurerAdapter{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeiXinProperties weixin = securityProperties.getSocial().getWeixin();
        return new WeiXinConnectionFactory(weixin.getProviderId(),weixin.getAppId(),weixin.getAppSecret());
    }

    @Bean({"connect/weixinConnected","connect/weixinConnect"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weixinConnectedView() {
        return new YxdtyutConnectionView();
    }
}
