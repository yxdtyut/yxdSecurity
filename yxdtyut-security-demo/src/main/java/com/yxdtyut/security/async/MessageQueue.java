package com.yxdtyut.security.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author : yangxudong
 * @Description :   模拟消息队列
 * @Date : 上午11:47 2018/6/23
 */
@Component
@Slf4j
public class MessageQueue {
    private String orderId;
    private String completeId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        new Thread(() -> {
            log.info("下单开始:" + orderId);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeId = orderId;
            log.info("下单完成:" + completeId);
        }).start();
    }

    public String getCompleteId() {
        return completeId;
    }

    public void setCompleteId(String completeId) {
        this.completeId = completeId;
    }
}
