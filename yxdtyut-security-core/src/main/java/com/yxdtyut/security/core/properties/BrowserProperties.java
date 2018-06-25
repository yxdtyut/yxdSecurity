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
    private String loginUrl = "/yxdtyut-signIn.html";
    private LoginTypeEnum loginType = LoginTypeEnum.JSON;
}