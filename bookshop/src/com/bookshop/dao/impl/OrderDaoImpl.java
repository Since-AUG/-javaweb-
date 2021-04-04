package com.bookshop.dao.impl;

import com.bookshop.dao.OrderDao;
import com.bookshop.pojo.Order;

public class OrderDaoImpl extends BaseDAO<Order> implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)" +
                "values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
