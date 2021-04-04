package com.bookshop.service;

import com.bookshop.pojo.User;

public interface UserService {
    public void registUser(User user);
    public User login(User user);
    public boolean existUserName(String username);
}
