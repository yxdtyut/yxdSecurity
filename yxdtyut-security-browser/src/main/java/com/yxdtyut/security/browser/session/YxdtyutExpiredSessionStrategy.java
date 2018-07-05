package com.yxdtyut.security.browser.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午10:27 2018/7/5
 */
@Slf4j
public class YxdtyutExpiredSessionStrategy  extends AbstractSessionStrategy  implements SessionInformationExpiredStrategy {
    /**
     * @param invalidSessionUrl
     */
    public YxdtyutExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent eventØ) throws IOException, ServletException {
        SessionInvalid(eventØ.getRequest(),eventØ.getResponse());
    }

    @Override
    public boolean isConcurrency() {
        return true;
    }
}
