package com.bookshop.test;

import com.bookshop.pojo.User;
import com.bookshop.service.UserService;
import com.bookshop.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"abj134","666666","abj134@qq.com"));
        userService.registUser(new User(null,"bb134","666666","bbj134@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"abj134","666666",null)));
    }

    @Test
    public void existUserName() {
        System.out.println(userService.existUserName("abj134"));
        System.out.println(userService.existUserName("asd2"));
    }
    @Test
    public void parsenull(){
        Map<String,String> map = new HashMap<>();
        map.put("zero","0");
        System.out.println(map.get("oo"));
    }
}