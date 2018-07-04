package com.yxdtyut.security.core.social.qq.connect;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Author : yangxudong
 * @Description :   QQ的OAuth2Operations自定义实现类
 * @Date : 上午10:07 2018/7/3
 */

public class QQOAuth2Template extends OAuth2Template {


    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String authenticateUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, authenticateUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    /**
     * AccessGrant是accessToken的一个封装，自定义处理发送获取accessToken.
     */
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String resultStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(resultStr, "&");
        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
        String refreshToken = StringUtils.substringAfterLast(items[2], "=");
        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }

    /**
     * 增加处理text/html的converter.
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
