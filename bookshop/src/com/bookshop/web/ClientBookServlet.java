package com.bookshop.web;

import com.bookshop.pojo.Book;
import com.bookshop.pojo.Page;
import com.bookshop.service.BookService;
import com.bookshop.service.impl.BookServiceImpl;
import com.bookshop.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 pageNo和pageSize
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/bookservlet?action=page");
        //保存Page对象到request域中
        req.setAttribute("page",page);
        //请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);

    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 pageNo和pageSize
        Integer min = WebUtils.parseInt(req.getParameter("min"),0);
        Integer max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        if(min > max){
            int tmp = min;
            min = max;
            max = tmp;
        }
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        StringBuilder url = new StringBuilder("client/bookservlet?action=pageByPrice");
        if(req.getParameter("min") != null){
            url.append("&min=" + req.getParameter("min"));
        }
        if(req.getParameter("max")!=null){
            url.append("&max=" + req.getParameter("max"));
        }
        page.setUrl(url.toString());
        //保存Page对象到request域中
        req.setAttribute("page",page);
        //请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);

    }
}
