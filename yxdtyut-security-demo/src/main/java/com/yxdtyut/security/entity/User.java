package com.yxdtyut.security.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.yxdtyut.security.validator.UserMustBeLiBai;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午5:33 2018/6/19
 */
public class User {
    public interface SimpleView{};
    public interface DetailView extends SimpleView{};

    @UserMustBeLiBai
    private String username;

    @NotEmpty
    private String password;
    private String id;
    private Date birthday;

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

    @JsonView(SimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(DetailView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
