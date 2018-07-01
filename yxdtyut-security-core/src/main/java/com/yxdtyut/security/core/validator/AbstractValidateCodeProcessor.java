package com.yxdtyut.security.core.validator;

import com.yxdtyut.security.core.validator.sms.ValidateCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午10:20 2018/6/27
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor{


    @Autowired
    private Map<String,ValidateCodeGenerator> validateCodeGeneratorMap;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void process(ServletWebRequest request) throws Exception {
        //1.生成验证码
        C validateCode = createCode(request);
        //2.验证码保存至session
        saveCode(request,validateCode);
        //3.响应出去
        sendCode(request, validateCode);

    }

    /** 发送验证码，具体由子类完成.*/
    protected abstract void sendCode(ServletWebRequest request, C validateCode) throws IOException, Exception;

    /** 保存验证码到session.*/
    protected void saveCode(ServletWebRequest request, C validateCode){
        sessionStrategy.setAttribute(request,SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
    };

    /** 生成验证码.*/
    protected C createCode(ServletWebRequest request){
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(type + "CodeGenerator");
        return (C) validateCodeGenerator.createCode(request.getRequest());
    }

    /** 根据url获取验证码类型.*/
    protected  String getProcessorType(ServletWebRequest request){
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    };

    /** 获取.*/
    public ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /** 获取session中的key.*/
    public String getSessionKey(HttpServletRequest request) {
        return ValidateCodeProcessor.SESSION_KEY_PREFIX + getValidateCodeType(request);
    }

    @Override
    public void validate(HttpServletRequest request){
        String sessionKey = getSessionKey(request);
        //从session中获取图形验证码，并判断
        C CodeInSession = (C) sessionStrategy.getAttribute(new ServletWebRequest(request), sessionKey);
        //从请求中获取code值
        String codeInRequest = null;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request, getValidateCodeType(request).getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateException("获取验证码值失败");
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateException("验证码值为空");
        }
        if (CodeInSession == null) {
            throw new ValidateException("验证码不存在");
        }
        if (CodeInSession.isExpire()) {
            sessionStrategy.removeAttribute(new ServletWebRequest(request),sessionKey);
            throw new ValidateException("验证码已经过期");
        }
        if (!StringUtils.equals(CodeInSession.getCode(), codeInRequest)) {
            throw new ValidateException("验证码错误");
        }
        sessionStrategy.removeAttribute(new ServletWebRequest(request),sessionKey);
    }
}
