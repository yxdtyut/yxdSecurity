package com.yxdtyut.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午11:09 2018/7/5
 */
@Slf4j
@Data
public class AbstractSessionStrategy {

    /**
     * 跳转的url
     */
    private String destinationUrl;

    /**
     * 跳转前是否创建新的session
     */
    private Boolean createNewSession = true;

    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param invalidSessionUrl
     * @param invalidSessionHtmlUrl
     */
    public AbstractSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    protected void SessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (createNewSession) {
            request.getSession();
        }

        String targetUrl = null;
        if (StringUtils.endsWithIgnoreCase(request.getRequestURI(), ".html")) {
            targetUrl = destinationUrl;
            log.info("session失效，跳转到:{}", targetUrl);
            redirectStrategy.sendRedirect(request,response,targetUrl);
        } else {
            Object result = buildResponseContent(request);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }
    }

    private Object buildResponseContent(HttpServletRequest request) {
        String message = "session失效";
        if (isConcurrency()) {
            message = message + "有可能是并发登陆导致的";
        }
        return message;
    }

    /**
     * session失效是否是并发导致的
     * @return
     */
    public boolean isConcurrency() {
        return false;
    }
}
