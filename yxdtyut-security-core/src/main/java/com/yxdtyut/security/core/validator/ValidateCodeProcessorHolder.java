package com.yxdtyut.security.core.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :   验证流程选择器
 * @Date : 下午3:46 2018/7/1
 */
@Component
public class ValidateCodeProcessorHolder {
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName().substring("validate".length());
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorMap.get(name);
        if (validateCodeProcessor == null) {
            throw new ValidateException("验证码处理器" + name + "不存在");
        }
        return validateCodeProcessor;
    }
}
