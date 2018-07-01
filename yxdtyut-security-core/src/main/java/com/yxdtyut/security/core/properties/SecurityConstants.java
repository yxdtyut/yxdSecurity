package com.yxdtyut.security.core.properties;

/**
 * @Author : yangxudong
 * @Description :   安全常量类
 * @Date : 下午3:22 2018/7/1
 */

public class SecurityConstants {

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 当请求需要认证的时候，默认跳转的url.
     */
    public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/login";
    /**
     * 默认的手机验证码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 默认登录页面
     *
     * @see SecurityController
     */
    public static final String DEFAULT_LOGIN_PAGE_URL = "/yxdtyut-signIn.html";

    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

}
