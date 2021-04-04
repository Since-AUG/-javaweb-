package com.bookshop.dao;

import com.bookshop.pojo.Order;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);
}
