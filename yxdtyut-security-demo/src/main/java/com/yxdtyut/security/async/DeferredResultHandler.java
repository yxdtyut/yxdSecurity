package com.yxdtyut.security.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :   异步传递对象处理类
 * @Date : 上午11:50 2018/6/23
 */
@Component
@Data
public class DeferredResultHandler {
    private Map<String, DeferredResult<String>> map = new HashMap<>();
}
