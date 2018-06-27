package com.yxdtyut.security.core.validator.image;

import com.yxdtyut.security.core.validator.sms.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author : yangxudong
 * @Description :   图形验证码对象
 * @Date : 上午9:58 2018/6/26
 */
@Data
public class ImageCode extends ValidateCode {
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, LocalDateTime expire) {
        super(code,expire);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, int expire) {
        super(code,expire);
        this.image = image;
    }

}
