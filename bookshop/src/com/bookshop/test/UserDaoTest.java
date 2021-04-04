package com.bookshop.test;

import com.bookshop.dao.UserDao;
import com.bookshop.dao.impl.UserDAOImpl;
import com.bookshop.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    private UserDao userDao = new UserDAOImpl();
    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"alice2","123456","admin@123.com")));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin1","admin") ==null){
            System.out.println("用户名或密码错误，登陆失败");
        }else{
            System.out.println("登录成功");
        }
    }
}