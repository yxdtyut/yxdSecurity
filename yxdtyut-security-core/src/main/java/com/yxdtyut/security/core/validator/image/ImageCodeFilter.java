package com.yxdtyut.security.core.validator.image;

import com.yxdtyut.security.core.properties.SecurityProperties;
import com.yxdtyut.security.core.validator.ValidateCodeProcessor;
import com.yxdtyut.security.core.validator.ValidateException;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author : yangxudong
 * @Description :   图形验证码的过滤器
 * @Date : 上午10:17 2018/6/26
 */
@Data
@Component
public class ImageCodeFilter extends OncePerRequestFilter implements InitializingBean{

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private AuthenticationFailureHandler yxdtyutAuthenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    private Set<String> urlSet = new HashSet<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidate().getImage().getUrls(), ",");
        if (urls != null && urls.length > 0) {
            Arrays.stream(urls).forEach((x)->urlSet.add(x));
        }
        urlSet.add("/authorization/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean matching = false;
        for (String url : urlSet) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                matching = true;
            }
        }

        if (matching) {

            try {
                validateImageCode(request);
            } catch (ValidateException e) {
                yxdtyutAuthenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }

        }

        filterChain.doFilter(request,response);
    }

    /**
     * @Author : yangxudong
     * @Description : 校验图形验证码
     * @param request
     * @Date : 上午10:24 2018/6/26
     */
    private void validateImageCode(HttpServletRequest request) throws ServletRequestBindingException {
        //从session中获取图形验证码，并判断
        ImageCode imageCode = (ImageCode) sessionStrategy.getAttribute(new ServletWebRequest(request), ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
        //从请求中获取code值
        String requestCode = ServletRequestUtils.getStringParameter(request, "imageCode");
        if (StringUtils.isBlank(requestCode)) {
            throw new ValidateException("验证码值为空");
        }
        if (imageCode == null) {
            throw new ValidateException("验证码不存在");
        }
        if (imageCode.isExpire()) {
            sessionStrategy.removeAttribute(new ServletWebRequest(request),ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
            throw new ValidateException("验证码已经过期");
        }
        if (!StringUtils.equals(imageCode.getCode(), requestCode)) {
            throw new ValidateException("验证码错误");
        }
        sessionStrategy.removeAttribute(new ServletWebRequest(request),ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
    }
}
