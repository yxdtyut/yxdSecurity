package com.yxdtyut.security.browser;

import com.yxdtyut.security.browser.logout.YxdtyutLogoutSuccessHandler;
import com.yxdtyut.security.browser.session.YxdtyutExpiredSessionStrategy;
import com.yxdtyut.security.browser.session.YxdtyutInvalidSessionStrategy;
import com.yxdtyut.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午11:22 2018/7/5
 */
@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new YxdtyutInvalidSessionStrategy(securityProperties.getBrowser().getSession().getInvalidSessionUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new YxdtyutExpiredSessionStrategy(securityProperties.getBrowser().getSession().getInvalidSessionUrl());
    }


    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new YxdtyutLogoutSuccessHandler(securityProperties.getBrowser().getLogoutUrl());
    }


}
