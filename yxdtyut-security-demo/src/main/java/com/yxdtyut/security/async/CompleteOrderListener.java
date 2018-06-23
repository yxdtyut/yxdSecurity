package com.yxdtyut.security.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午12:19 2018/6/23
 */
@Component
@Slf4j
public class CompleteOrderListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MessageQueue messageQueue;

    @Autowired
    private DeferredResultHandler deferredResultHandler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(() -> {
            while(true) {
                if (StringUtils.isNotBlank(messageQueue.getCompleteId())) {
                    log.info("返回订单处理结果:{}" , messageQueue.getCompleteId());
                    deferredResultHandler.getMap().get(messageQueue.getCompleteId()).setResult("下单成功:" + messageQueue.getCompleteId());
                    messageQueue.setCompleteId(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
