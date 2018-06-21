package com.yxdtyut.security.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:26 2018/6/21
 */
@Constraint(validatedBy = {UserMustBeLiBaiConstraintValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface UserMustBeLiBai {
    String message() default "{用户必须是李白}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
