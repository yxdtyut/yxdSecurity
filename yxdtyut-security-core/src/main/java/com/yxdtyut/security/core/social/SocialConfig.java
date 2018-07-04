package com.yxdtyut.security.core.social;

import com.yxdtyut.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Author : yangxudong
 * @Description :   social相关配置类
 * @Date : 下午4:17 2018/7/2
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        /** 最后一个参数可以对敏感数据进行加密保存，这里为了方面观察，未做任何处理.*/
        JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        //usersConnectionRepository.setTablePrefix();   可以设置表前缀
        if (connectionSignUp != null) {
            usersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return usersConnectionRepository;
    }

    /**
     * @param null
     * @Author : yangxudong
     * @Description :  配置SpringSocialConfigurer，在其他安全配置中可以加入
     * SpringSocialConfigurer
     * 启动 SocialAuthenticationFilter
     * @Date : 下午4:43 2018/7/2
     */
    @Bean
    public SpringSocialConfigurer yxdSpringSocialConfigurer() {
        SpringSocialConfigurer springSocialConfigurer = new yxdtyutSpringSocialConfigurer(securityProperties.getSocial().getFilterProcessesUrl());
        springSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return springSocialConfigurer;
    }

    /**
     * 解决两个问题：
     * 1.注册页面过去spring social中的用户信息
     * 2.注册完毕后 把业务系统的id传给spring social.
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }
}
