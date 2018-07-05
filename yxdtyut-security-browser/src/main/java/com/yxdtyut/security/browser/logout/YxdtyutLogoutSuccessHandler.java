package com.yxdtyut.security.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxdtyut.security.browser.domain.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : yangxudong
 * @Description :   自定义退出成功处理逻辑
 * @Date : 下午3:33 2018/7/5
 */
@Slf4j
public class YxdtyutLogoutSuccessHandler implements LogoutSuccessHandler {

    private String logoutUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    public YxdtyutLogoutSuccessHandler(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("退出成功");
        if (StringUtils.isBlank(logoutUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
        } else {
            response.sendRedirect(logoutUrl);
        }
    }
}
