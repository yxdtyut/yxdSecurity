package com.yxdtyut.security.core.validator.sms.impl;

import com.yxdtyut.security.core.validator.sms.SmsCodeSender;

/**
 * @Author : yangxudong
 * @Description :   默认的短信发送处理器
 * @Date : 下午5:51 2018/6/26
 */

public class DefaultSmsCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobile, String msg) {
        System.out.println("向手机" + mobile + "发送短信:" + msg);
    }
}
