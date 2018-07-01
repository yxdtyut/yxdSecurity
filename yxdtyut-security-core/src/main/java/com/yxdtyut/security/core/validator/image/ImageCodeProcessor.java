package com.yxdtyut.security.core.validator.image;

import com.yxdtyut.security.core.validator.AbstractValidateCodeProcessor;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:11 2018/6/27
 */
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /** session操作工具.*/
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void sendCode(ServletWebRequest request, ImageCode validateCode) throws Exception {
            ImageCode imageCode = (ImageCode) validateCode;
            ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

}
