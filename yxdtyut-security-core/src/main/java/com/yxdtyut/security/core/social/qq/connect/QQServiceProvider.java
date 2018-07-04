package com.yxdtyut.security.core.social.qq.connect;

import com.yxdtyut.security.core.social.qq.api.QQ;
import com.yxdtyut.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Author : yangxudong
 * @Description :   QQ服务提供商
 * @Date : 下午3:58 2018/7/2
 */

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private static final String authorizeUrl = "https://graph.qq.com/oauth2.0/authorize";

    private static final String accessTokenUrl = "https://graph.qq.com/oauth2.0/token";

    /**
     * Create a new {@link OAuth2ServiceProvider}.
     *
     * @param oauth2Operations the OAuth2Operations template for conducting the OAuth 2 flow with the provider.
     */
    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, authorizeUrl, accessTokenUrl));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
