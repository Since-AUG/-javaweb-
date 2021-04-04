package com.bookshop.service;

import com.bookshop.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

}
