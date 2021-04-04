package com.bookshop.dao.impl;

import com.bookshop.dao.UserDao;
import com.bookshop.pojo.User;

import java.util.List;

public class UserDAOImpl extends BaseDAO<User> implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=?";
        return queryForOne(sql,username);

    }

    @Override
    public int saveUser(User user) {
        String sql="insert into t_user(`username`,`password`,`email`) value(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql="select `id`,`username`,`password`,`email` from t_user where username=? and password=?";
        return queryForOne(sql,username,password);
    }

    @Override
    public int update(String sql, Object... args) {
        return super.update(sql, args);
    }

    @Override
    public User queryForOne(String sql, Object... args) {
        return super.queryForOne(sql, args);
    }

    @Override
    public List<User> queryForList(String sql, Object... args) {
        return super.queryForList(sql, args);
    }

    @Override
    public Object queryForSingleValue(String sql, Object... args) {
        return super.queryForSingleValue(sql, args);
    }
}
