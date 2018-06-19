package com.yxdtyut.security.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午5:33 2018/6/19
 */
public class User {
    public interface SimpleView{};
    public interface DetailView extends SimpleView{};

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @JsonView(SimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(DetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
