package com.yxdtyut.security.core.social.qq.config;

import com.yxdtyut.security.core.properties.SecurityProperties;
import com.yxdtyut.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author : yangxudong
 * @Description :   QQ登陆自动配置类,创建ConnectionFactory
 * @Date : 下午4:32 2018/7/2
 */
@Configuration
@ConditionalOnProperty(prefix = "yxdtyut.security.social.qq", name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        String providerId = securityProperties.getSocial().getQq().getProviderId();
        String appId = securityProperties.getSocial().getQq().getAppId();
        String appSecret = securityProperties.getSocial().getQq().getAppSecret();
        return new QQConnectionFactory(providerId, appId, appSecret);
    }
}
