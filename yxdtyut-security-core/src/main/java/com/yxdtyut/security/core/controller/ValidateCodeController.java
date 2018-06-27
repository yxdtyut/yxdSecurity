package com.yxdtyut.security.core.controller;

import com.yxdtyut.security.core.validator.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :   生成图形验证码的controller
 * @Date : 上午10:02 2018/6/26
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private Map<String, ValidateCodeProcessor> processorMap;


    @GetMapping("/code/{type}")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        processorMap.get(type + "CodeProcessor").process(new ServletWebRequest(request, response));
    }

}
