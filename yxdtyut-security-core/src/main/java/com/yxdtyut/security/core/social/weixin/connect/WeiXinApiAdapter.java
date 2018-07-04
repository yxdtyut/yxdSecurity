package com.yxdtyut.security.core.social.weixin.connect;

import com.yxdtyut.security.core.social.weixin.api.WeiXin;
import com.yxdtyut.security.core.social.weixin.api.WeiXinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午10:48 2018/7/4
 */

public class WeiXinApiAdapter implements ApiAdapter<WeiXin> {

    private String openId;

    @Override
    public boolean test(WeiXin api) {
        return true;
    }

    public WeiXinApiAdapter() {
    }

    public WeiXinApiAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public void setConnectionValues(WeiXin api, ConnectionValues values) {
        WeiXinUserInfo profile = api.getWeiXinUserInfo(openId);
        values.setProviderUserId(profile.getOpenid());
        values.setDisplayName(profile.getNickname());
        values.setImageUrl(profile.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(WeiXin api) {
        return null;
    }

    @Override
    public void updateStatus(WeiXin api, String message) {

    }
}
