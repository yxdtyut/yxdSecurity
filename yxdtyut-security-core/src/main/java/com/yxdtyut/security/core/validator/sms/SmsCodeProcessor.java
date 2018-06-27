package com.yxdtyut.security.core.validator.sms;

import com.yxdtyut.security.core.properties.SecurityProperties;
import com.yxdtyut.security.core.validator.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:13 2018/6/27
 */
@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor {

    @Autowired
    private SmsCodeSender sender;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void sendCode(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        sender.send(ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"mobile"),validateCode.getCode());
    }
}
