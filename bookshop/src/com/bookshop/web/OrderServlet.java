package com.bookshop.web;

import com.bookshop.dao.impl.BaseDAO;
import com.bookshop.pojo.Cart;
import com.bookshop.pojo.User;
import com.bookshop.service.OrderService;
import com.bookshop.service.impl.OrderServiceImpl;
import com.bookshop.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        User user= (User)req.getSession().getAttribute("user");
        if(user==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = user.getId();
        System.out.println(userId);
        String orderId = null;
        orderId = orderService.createOrder(cart,userId);

        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
