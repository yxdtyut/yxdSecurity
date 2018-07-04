package com.yxdtyut.security.browser.domain;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :对social用户信息的封装
 * @Date : 下午2:48 2018/7/3
 */
@Data
public class SocialUserInfo {
    private String providerId;
    private String providerUserId;
    private String nickName;
    private String headimg;
}
