package com.yxdtyut.security.core.social.weixin.connect;

import lombok.Getter;
import org.springframework.social.oauth2.AccessGrant;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午10:15 2018/7/4
 */
@Getter
public class WeiXinAccessGrant extends AccessGrant {

    private String openId;

    public WeiXinAccessGrant(String accessToken, String openId) {
        super(accessToken);
        this.openId = openId;
    }

    public WeiXinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, String openId) {
        super(accessToken, scope, refreshToken, expiresIn);
        this.openId = openId;
    }
}
