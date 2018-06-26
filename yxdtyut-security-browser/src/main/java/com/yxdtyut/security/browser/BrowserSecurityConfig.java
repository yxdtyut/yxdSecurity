package com.yxdtyut.security.browser;

import com.yxdtyut.security.core.properties.SecurityProperties;
import com.yxdtyut.security.core.validator.image.ImageCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Author : yangxudong
 * @Description :   浏览器安全认证配置
 * @Date : 下午4:45 2018/6/24
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler yxdtyutAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler yxdtyutAuthenticationFailureHandler;

    @Autowired
    private ImageCodeFilter imageCodeFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

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

//        ImageCodeFilter imageCodeFilter = new ImageCodeFilter();
//        imageCodeFilter.setYxdtyutAuthenticationFailureHandler(yxdtyutAuthenticationFailureHandler);

        http.addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginPage("/authentication/require")
                    .loginProcessingUrl("/authorization/login")
                    .successHandler(yxdtyutAuthenticationSuccessHandler)
                    .failureHandler(yxdtyutAuthenticationFailureHandler)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginUrl(),"/image/code").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }
}
