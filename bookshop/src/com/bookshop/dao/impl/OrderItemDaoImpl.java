package com.bookshop.dao.impl;

import com.bookshop.dao.OrderItemDao;
import com.bookshop.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDAO<OrderItem> implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`)" +
                "values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),
                orderItem.getOrderId());
    }
}
