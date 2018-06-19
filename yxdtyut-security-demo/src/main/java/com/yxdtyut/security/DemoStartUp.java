package com.yxdtyut.security;

import com.sun.glass.ui.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:32 2018/6/19
 */
@SpringBootApplication
@RestController
public class DemoStartUp {

    public static void main(String[] args) {
        SpringApplication.run(DemoStartUp.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }
}
