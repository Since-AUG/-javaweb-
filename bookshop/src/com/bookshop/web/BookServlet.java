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
import java.lang.reflect.Method;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = WebUtils.parseInt(req.getParameter("id"),1);
        Book book = bookService.queryBookById(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0)+1;
        //获取请求的参数，封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用BookService.addBook()
        bookService.addBook(book);
        //重定向到图书列表页面/manager/bookservlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookservlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数id,
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用BookService.deleteBook()
        bookService.deleteBookById(id);
        //重定向到页面/manager/bookservlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookservlet?action=page");

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用BookService.updateBook(book)修改图书
        bookService.updateBook(book);
        //重定向到页面/manager/bookservlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookservlet?action=page&pageNo="+req.getParameter("pageNo"));

    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //把全部图书保存到Request中
        req.setAttribute("books", books);
        //请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
    /*处理分页功能*/
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 pageNo和pageSize
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookservlet?action=page");
        //保存Page对象到request域中
        req.setAttribute("page",page);
        //请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }
}
