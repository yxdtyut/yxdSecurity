package com.yxdtyut.security.core.validator.sms;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : yangxudong
 * @Description :   验证码对象
 * @Date : 上午9:58 2018/6/26
 */
@Data
public class ValidateCode {
    private String code;
    private LocalDateTime expire;

    public ValidateCode(String code, LocalDateTime expire) {
        this.code = code;
        this.expire = expire;
    }

    public ValidateCode(String code, int expire) {
        this.code = code;
        this.expire = LocalDateTime.now().plusSeconds(expire);
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expire);
    }


}
