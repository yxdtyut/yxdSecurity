package com.yxdtyut.security.core.social.weixin.api;

/**
 * @Author : yangxudong
 * @Description :   微信api接口
 * @Date : 下午4:46 2018/7/3
 */

public interface WeiXin {
    WeiXinUserInfo getWeiXinUserInfo(String openId);
}
