package com.yxdtyut.security.core.social.weixin.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :   完成微信的OAuth2认证流程的模板类。国内厂商实现的OAuth2每个都不同, spring默认提供的OAuth2Template适应不了，只能针对每个厂商自己微调。
 * @Date : 上午10:02 2018/7/4
 */
@Slf4j
public class WeiXinOAuth2Template extends OAuth2Template {

    private String clientId;

    private String clientSecret;

    private String accessTokenUrl;

    private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    public WeiXinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
    }

    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {

        StringBuilder refreshTokenUrl = new StringBuilder(REFRESH_TOKEN_URL);

        refreshTokenUrl.append("?appid="+clientId);
        refreshTokenUrl.append("&grant_type=refresh_token");
        refreshTokenUrl.append("&refresh_token="+refreshToken);

        return getAccessToken(refreshTokenUrl);
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        StringBuilder sb = new StringBuilder(accessTokenUrl);
        sb.append("?appid=" + clientId);
        sb.append("&secret=" + clientSecret);
        sb.append("&code=" + authorizationCode);
        sb.append("&grant_type=authorization_code");
        return getAccessToken(sb);
    }

    private AccessGrant getAccessToken(StringBuilder sb) {
        log.info("获取accessToken请求的url:{}", sb.toString());
        String response = getRestTemplate().getForObject(sb.toString(), String.class);
        log.info("获取accessToken,结果:{}", response);
        Map<String, Object> result = null;
        try {
            result = new ObjectMapper().readValue(response, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))) {
            String errcode = MapUtils.getString(result, "errcode");
            String errmsg = MapUtils.getString(result, "errmsg");
            throw new RuntimeException("获取accessToken失败,errcode:"+ errcode +",errmsg:{}" + errmsg);
        }

        String access_token = MapUtils.getString(result, "access_token");
        Long expires_in = MapUtils.getLong(result, "expires_in");
        String refresh_token = MapUtils.getString(result, "refresh_token");
        String openid = MapUtils.getString(result, "openid");
        String scope = MapUtils.getString(result, "scope");
        WeiXinAccessGrant accessGrant = new WeiXinAccessGrant(access_token,scope,refresh_token,expires_in,openid);

        return accessGrant;
    }

    /**
     * 微信返回的contentType是html/text，添加相应的HttpMessageConverter来处理。
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    /**
     * 构建获取授权码的请求。也就是引导用户跳转到微信的地址。
     */
    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        String url = super.buildAuthenticateUrl(parameters);
        url = url + "&appid="+clientId+"&scope=snsapi_login";
        return url;
    }

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return buildAuthenticateUrl(parameters);
    }
}
