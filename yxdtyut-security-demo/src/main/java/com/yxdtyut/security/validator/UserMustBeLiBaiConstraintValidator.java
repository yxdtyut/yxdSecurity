package com.yxdtyut.security.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:28 2018/6/21
 */

public class UserMustBeLiBaiConstraintValidator implements ConstraintValidator<UserMustBeLiBai, String>{
    @Override
    public void initialize(UserMustBeLiBai constraintAnnotation) {
        System.out.println("自定义用户验证器初始化");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ("李白".equals(value)) {
            return true;
        }
        return false;
    }
}
