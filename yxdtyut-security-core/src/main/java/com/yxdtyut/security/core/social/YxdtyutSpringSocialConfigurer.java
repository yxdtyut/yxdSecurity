package com.yxdtyut.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author : yangxudong
 * @Description :   自定义SpringSocialConfigurer
 *                  用来改写SocialAuthenticationFilter默认拦截路径
 * @Date : 下午5:59 2018/7/2
 */

public class YxdtyutSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public YxdtyutSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);;
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
