package com.yxdtyut.security.browser;

import com.yxdtyut.security.core.authentication.AbstractChannelSecurityConfig;
import com.yxdtyut.security.core.authentication.mobile.SmsAuthenticationConfig;
import com.yxdtyut.security.core.properties.SecurityConstants;
import com.yxdtyut.security.core.properties.SecurityProperties;
import com.yxdtyut.security.core.validator.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Author : yangxudong
 * @Description :   浏览器安全认证配置
 * @Date : 下午4:45 2018/6/24
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer yxdSpringSocialConfigurer;


    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        http.apply(validateCodeSecurityConfig)
            .and()
                .apply(smsAuthenticationConfig)
            .and()
                .apply(yxdSpringSocialConfigurer)
            .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
            .and()
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .maxSessionsPreventsLogin(true)
                .and()
            .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
            .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        securityProperties.getBrowser().getLoginUrl(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        "/social/user","/user/regist",
                        securityProperties.getBrowser().getSession().getInvalidSessionUrl(),
                        securityProperties.getBrowser().getLoginUrl()).permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();

    }
}
