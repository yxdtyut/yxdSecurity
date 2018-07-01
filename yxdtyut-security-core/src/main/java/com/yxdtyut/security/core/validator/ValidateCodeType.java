package com.yxdtyut.security.core.validator;

import com.yxdtyut.security.core.properties.SecurityConstants;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午1:41 2018/7/1
 */

public enum ValidateCodeType {
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /** 校验时从请求中获取的参数的名字.*/
    public abstract String getParamNameOnValidate();
}
