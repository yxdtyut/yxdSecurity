package com.yxdtyut.security.core.authentication.mobile;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午4:05 2018/6/27
 */
@Data
public class SmsAuthenticationProvider  implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmSAuthenticationToken smSAuthenticationToken = (SmSAuthenticationToken)authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) smSAuthenticationToken.getPrincipal());
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmSAuthenticationToken smSAuthenticationTokenResult = new SmSAuthenticationToken(user, user.getAuthorities());
        smSAuthenticationTokenResult.setDetails(smSAuthenticationToken.getDetails());
        return smSAuthenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmSAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
