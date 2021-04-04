package com.bookshop.service.impl;

import com.bookshop.dao.UserDao;
import com.bookshop.dao.impl.UserDAOImpl;
import com.bookshop.pojo.User;
import com.bookshop.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDAOImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUserName(String username) {
        if(userDao.queryUserByUsername(username)!=null){
            return true;
        }
        return false;
    }
}
