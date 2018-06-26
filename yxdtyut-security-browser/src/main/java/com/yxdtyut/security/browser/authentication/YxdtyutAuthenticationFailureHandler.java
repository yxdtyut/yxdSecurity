package com.yxdtyut.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxdtyut.security.browser.domain.SimpleResponse;
import com.yxdtyut.security.core.enums.LoginTypeEnum;
import com.yxdtyut.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : yangxudong
 * @Description :   自定义失败处理器
 * @Date : 下午4:37 2018/6/25
 */
@Component(value = "yxdtyutAuthenticationFailureHandler")
@Slf4j
public class YxdtyutAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登陆失败");
        if (LoginTypeEnum.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        } else {
            super.onAuthenticationFailure(request,response,exception);
        }


    }
}
