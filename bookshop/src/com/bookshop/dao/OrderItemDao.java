package com.bookshop.dao;

import com.bookshop.pojo.OrderItem;

public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);
}
