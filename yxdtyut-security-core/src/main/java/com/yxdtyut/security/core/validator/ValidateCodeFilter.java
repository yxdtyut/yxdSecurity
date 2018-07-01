package com.yxdtyut.security.core.validator;

import com.yxdtyut.security.core.properties.SecurityProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :   验证码相关过滤器
 * @Date : 下午1:35 2018/7/1
 */
@Data
@Component("validateCodeFilter")
@Slf4j
public class ValidateCodeFilter  extends OncePerRequestFilter implements InitializingBean {

    /** session操作工具.*/
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /** 验证码失败处理器.*/
    @Autowired
    private AuthenticationFailureHandler yxdtyutAuthenticationFailureHandler;

    /** 安全相关配置.*/
    @Autowired
    private SecurityProperties securityProperties;

    /** 存放所有需要验证码的url.*/
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /** 验证请求url和配置的url是否匹配的工具类.*/
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        addUrlToMap(securityProperties.getValidate().getImage().getUrls(), ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getValidate().getSms().getUrls(), ValidateCodeType.SMS);
        urlMap.put("/authentication/login",ValidateCodeType.IMAGE);
        urlMap.put("/authentication/mobile",ValidateCodeType.SMS);
    }

    /**
     * @Author : yangxudong
     * @Description : 添加配置url到对应map中
     * @param
     * @Date : 下午1:40 2018/7/1
     */
    public void addUrlToMap(String url, ValidateCodeType type) {
        String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidate().getImage().getUrls(), ",");
        if (urls != null && urls.length > 0) {
            Arrays.stream(urls).forEach((x)->urlMap.put(x, type));
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ValidateCodeType type = getRequestValidateCodeType(request);

        if (type != null) {
            log.info("验证码的请求类型是:{}", type);
            ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorHolder.findValidateCodeProcessor(type.name());
            try {
                validateCodeProcessor.validate(request);
                log.info("验证码校验通过");
            } catch (ValidateException e) {
                yxdtyutAuthenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }

        filterChain.doFilter(request,response);
    }

    /**
     * @Author : yangxudong
     * @Description : 获取请求的验证码类型
     * @param request
     * @Date : 下午1:47 2018/7/1
     */
    private ValidateCodeType getRequestValidateCodeType(HttpServletRequest request) {
        ValidateCodeType type = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            for(String url: urlMap.keySet()) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    type =  urlMap.get(url);
                }
            }
        }
        return type;
    }


}