package com.yxdtyut.security.controller;

import com.yxdtyut.security.async.DeferredResultHandler;
import com.yxdtyut.security.async.MessageQueue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author : yangxudong
 * @Description :   测试异步controller
 * @Date : 上午11:33 2018/6/23
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private MessageQueue messageQueue;

    @Autowired
    private DeferredResultHandler deferredResultHandler;

    @GetMapping
    public DeferredResult<String> createOrder() {
        log.info("主线程下单开始");
//        Callable<String> callable = () -> {
//            log.info("副线程下单开始");
//            Thread.sleep(1000);
//            log.info("副线程下单结束");
//            return "success";
//        };
        String orderId = RandomStringUtils.randomNumeric(10);
        messageQueue.setOrderId(orderId);
        DeferredResult result = new DeferredResult();
        deferredResultHandler.getMap().put(orderId, result);

        log.info("主线程下单结束");
        return result;
    }
}
