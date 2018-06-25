package com.yxdtyut.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author : yangxudong
 * @Description :   校验用户名和密码
 * @Date : 下午5:02 2018/6/24
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户登陆的用户名是:{}", username);
        log.info("这里可以从数据库读取密码，读取失败可以抛出异常!");
        //模拟从数据库读取，读取出来的是加密后的密码，返回回去的时候，spring security默认调用
        //了passwordEncoder的matches方法进行比对
        String password = passwordEncoder.encode("123456");
        log.info("加密后的密码:{}", password);
        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
