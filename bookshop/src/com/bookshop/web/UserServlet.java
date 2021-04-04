package com.bookshop.web;

import com.bookshop.pojo.User;
import com.bookshop.service.UserService;
import com.bookshop.service.impl.UserServiceImpl;
import com.bookshop.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        User userFromDB;
        if ( (userFromDB = userService.login(user)) != null) {
            System.out.println("用户[" + username + "]登录成功！");
            req.getSession().setAttribute("user", userFromDB);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            System.out.println("用户名或密码错误!");
            req.setAttribute("msg", "用户名或密码错误!");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean exist = userService.existUserName(username);
        Map<String,Object> resuleMap = new HashMap<>();
        resuleMap.put("exist",exist);
        Gson gson = new Gson();
        resp.getWriter().write(gson.toJson(resuleMap));
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if(token!=null && token.equalsIgnoreCase(code)){
            if(userService.existUserName(username)){
                System.out.println("用户名["+username+"]已存在！");
                req.setAttribute("msg","用户名已存在!");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                System.out.println("用户" + username + "注册成功！");
                userService.registUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            System.out.println("验证码["+code+"]错误!");
            req.setAttribute("msg","验证码错误!");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);

        }
    }
        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req,resp);
    }
}
