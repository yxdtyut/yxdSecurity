package com.yxdtyut.security.core.social.weixin.connect;

import com.yxdtyut.security.core.social.weixin.api.WeiXin;
import com.yxdtyut.security.core.social.weixin.api.WeiXinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Author : yangxudong
 * @Description :   微信的OAuth2流程处理器的提供器，供spring social的connect体系调用
 * @Date : 下午5:45 2018/7/3
 */

public class WeiXinServiceProvider extends AbstractOAuth2ServiceProvider<WeiXin> {

    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * Create a new {@link OAuth2ServiceProvider}.
     *
     * @param oauth2Operations the OAuth2Operations template for conducting the OAuth 2 flow with the provider.
     */
    public WeiXinServiceProvider(String appId, String appSecret) {
        super(new WeiXinOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public WeiXin getApi(String accessToken) {
        return new WeiXinImpl(accessToken);
    }
}
