package com.yxdtyut.security.core.validator.image;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author : yangxudong
 * @Description :   图形验证码对象
 * @Date : 上午9:58 2018/6/26
 */
@Data
public class ImageCode {
    private BufferedImage image;
    private String code;
    private LocalDateTime expire;

    public ImageCode(BufferedImage image, String code, LocalDateTime expire) {
        this.image = image;
        this.code = code;
        this.expire = expire;
    }

    public ImageCode(BufferedImage image, String code, int expire) {
        this.image = image;
        this.code = code;
        this.expire = LocalDateTime.now().plusSeconds(expire);
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expire);
    }


}
