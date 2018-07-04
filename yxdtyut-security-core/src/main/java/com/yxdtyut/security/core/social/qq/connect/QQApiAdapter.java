package com.yxdtyut.security.core.social.qq.connect;

import com.yxdtyut.security.core.social.qq.api.QQ;
import com.yxdtyut.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午4:11 2018/7/2
 */

public class QQApiAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getQQUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        /** 主页(类似微博主页) QQ没有主页.*/
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    /** 类似微博发说说更新状态.*/
    @Override
    public void updateStatus(QQ api, String message) {

    }
}
