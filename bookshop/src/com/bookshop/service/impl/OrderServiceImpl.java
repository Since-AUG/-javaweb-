package com.bookshop.service.impl;

import com.bookshop.dao.BookDao;
import com.bookshop.dao.OrderDao;
import com.bookshop.dao.OrderItemDao;
import com.bookshop.dao.impl.BookDaoImpl;
import com.bookshop.dao.impl.OrderDaoImpl;
import com.bookshop.dao.impl.OrderItemDaoImpl;
import com.bookshop.pojo.*;
import com.bookshop.service.OrderService;

import java.util.Date;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
        for(CartItem cartItem:cart.getItems().values()){
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
