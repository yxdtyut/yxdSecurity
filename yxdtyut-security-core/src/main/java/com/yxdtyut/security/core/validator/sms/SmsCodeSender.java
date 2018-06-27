package com.yxdtyut.security.core.validator.sms;

/**
 * @Author : yangxudong
 * @Description :   短信发送接口
 * @Date : 下午5:50 2018/6/26
 */

public interface SmsCodeSender {
    void send(String mobile, String msg);
}
