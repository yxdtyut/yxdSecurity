package com.yxdtyut.security.browser.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午10:27 2018/7/5
 */
@Slf4j
public class YxdtyutInvalidSessionStrategy  extends AbstractSessionStrategy implements InvalidSessionStrategy {
    /**
     * @param invalidSessionUrl
     */
    public YxdtyutInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SessionInvalid(request,response);
    }
}
