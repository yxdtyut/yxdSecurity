package com.yxdtyut.security.core.properties;

import com.yxdtyut.security.core.enums.LoginTypeEnum;
import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   浏览器相关的配置
 * @Date : 下午3:45 2018/6/25
 */
@Data
public class BrowserProperties {
    private String loginUrl = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
    private LoginTypeEnum loginType = LoginTypeEnum.JSON;
    private Integer rememberMeSeconds=3600*24;
}
