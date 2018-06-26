package com.yxdtyut.security.core.validator;

import com.yxdtyut.security.core.properties.SecurityProperties;
import com.yxdtyut.security.core.validator.image.ImageCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午3:14 2018/6/26
 */
@Configuration
public class ValidateCodeConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }
}
