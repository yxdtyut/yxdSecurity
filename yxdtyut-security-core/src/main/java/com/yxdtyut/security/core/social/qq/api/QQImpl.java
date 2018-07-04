package com.yxdtyut.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @Author : yangxudong
 * @Description :   获取QQ用户信息实现类
 * @Date : 下午3:38 2018/7/2
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private String appId;

    private String openId;

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?&oauth_consumer_key=%s&openid=%s";

    private ObjectMapper objectMapper = new ObjectMapper();


    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("获取openid返回结果:{}", result);
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"","\"}");
    }

    @Override
    public QQUserInfo getQQUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("获取QQ用户信息:{}", result);
        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result,QQUserInfo.class);
            userInfo.setOpenId(openId);
        } catch (IOException e) {
            throw new RuntimeException("获取QQ用户信息失败", e);
        }
        return userInfo;
    }
}
