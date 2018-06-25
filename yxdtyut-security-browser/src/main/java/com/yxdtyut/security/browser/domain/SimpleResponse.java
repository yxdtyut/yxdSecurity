package com.yxdtyut.security.browser.domain;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   简单的返回
 * @Date : 下午3:42 2018/6/25
 */
@Data
public class SimpleResponse {
    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }
}
