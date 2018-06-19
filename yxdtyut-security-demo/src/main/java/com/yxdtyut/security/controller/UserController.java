package com.yxdtyut.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yxdtyut.security.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午5:32 2018/6/19
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping
    @JsonView(User.SimpleView.class)
    public List<User> findUserList(@RequestParam(required = false,defaultValue = "嘿嘿") String username
                ,@PageableDefault(page = 1,size = 10,sort = "username",direction = Sort.Direction.DESC) Pageable pageable) {
//        System.out.println(username);
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());
        List<User> list = new ArrayList<>();
        list.add(new User("李白", "123456"));
        list.add(new User("杜甫", "123456"));
        list.add(new User("白居易", "123456"));
        return list;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.DetailView.class)
    public User findSingleUser(@PathVariable String id) {
        User user = new User();
        user.setUsername("王昭君");
        user.setPassword(id);
        return user;
    }

}
