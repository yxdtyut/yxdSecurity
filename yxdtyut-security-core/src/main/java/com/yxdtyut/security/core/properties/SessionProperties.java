package com.yxdtyut.security.core.properties;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午10:56 2018/7/5
 */
@Data
public class SessionProperties {
    private String invalidSessionUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;
    private Integer maximumSessions = 1;

}
