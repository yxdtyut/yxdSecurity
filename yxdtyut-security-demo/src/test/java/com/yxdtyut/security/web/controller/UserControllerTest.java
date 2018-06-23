package com.yxdtyut.security.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午4:36 2018/6/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    /** 伪造服务，并不是真正的启动服务，比较快速.*/
    MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /** 测试获取user集合.*/
    @Test
    public void queryUserListWhenSuccess() throws Exception {
        String result = mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("username", "呵呵")
//                .param("size","15")
//                .param("page","1")
//                .param("sort","password,desc")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value("3"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /** 测试获取单个user对象.*/
    @Test
    public void querySingleUserWhenSuccess() throws Exception {
        String result = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("王昭君"))
                .andExpect(jsonPath("$.password").value(1))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /** 测试获取单个user对象正则.*/
    @Test
    public void querySingleuserWhenException() throws Exception {
        mockMvc.perform(get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    /** 创建用户测试.*/
    @Test
    public void createUserWhenSuccess() throws Exception {
        long birthday = new Date().getTime();
        String content = "{\"username\":\"王昭君\",\"password\":null,\"id\":null,\"birthday\":"+birthday+"}";
        String result = mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result:" + result);
    }

    /** 修改用户测试.*/
    @Test
    public void updateUserWhenSuccess() throws Exception {
        long birthday = new Date().getTime();
        String content = "{\"id\":\"1\",\"username\":\"李白\",\"password\":\"1\",\"birthday\":"+birthday+"}";
        String result = mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("result:" + result);
    }

    /** 删除用户测试.*/
    @Test
    public void deleteUserWhenSuccess() throws Exception {
        mockMvc.perform(delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    /** 上传测试.*/
    @Test
    public void uploadTest() throws Exception {
        mockMvc.perform(fileUpload("/user/uploadFile")
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
                .andExpect(status().isOk());
    }
}
