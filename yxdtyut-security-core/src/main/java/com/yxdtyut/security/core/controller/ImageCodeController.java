package com.yxdtyut.security.core.controller;

import com.yxdtyut.security.core.properties.SecurityProperties;
import com.yxdtyut.security.core.validator.ValidateCodeGenerator;
import com.yxdtyut.security.core.validator.image.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Author : yangxudong
 * @Description :   生成图形验证码的controller
 * @Date : 上午10:02 2018/6/26
 */
@RestController
@RequestMapping("/image/code")
public class ImageCodeController {

    public static final String SESSION_IMAGE_CODE_KEY = "session_image_code";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @GetMapping
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //生成图形验证码
        ImageCode imageCode =  imageCode(request);
        //保存到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_IMAGE_CODE_KEY, imageCode);
        //写出去
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    /**
     * @Author : yangxudong
     * @Description : 生成图形验证码
     * @param request
     * @Date : 上午10:04 2018/6/26
     */
    private ImageCode imageCode(HttpServletRequest request) {
        return imageCodeGenerator.imageCode(request);
    }




}
