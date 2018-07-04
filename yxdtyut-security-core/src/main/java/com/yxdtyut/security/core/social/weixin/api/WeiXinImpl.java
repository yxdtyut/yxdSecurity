package com.yxdtyut.security.core.social.weixin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   微信api实现类，获取用户信息
 * @Date : 下午4:50 2018/7/3
 */
@Slf4j
public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {

    private final String URL_GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo?openid=%s";

    private ObjectMapper objectMapper = new ObjectMapper();

    public WeiXinImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converterList = super.getMessageConverters();
        converterList.remove(0);
        converterList.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return converterList;
    }

    @Override
    public WeiXinUserInfo getWeiXinUserInfo(String openId) {
        //获取用户信息
        String url = String.format(URL_GET_USERINFO, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("获取微信用户信息:{}", result);
        if (StringUtils.contains(result, "errcode")) {
            return null;
        }
        WeiXinUserInfo weiXinUserInfo = null;
        try {
            weiXinUserInfo = objectMapper.readValue(result, WeiXinUserInfo.class);
        } catch (IOException e) {
            throw new RuntimeException("获取微信用户信息失败", e);
        }
        return weiXinUserInfo;
    }
}
