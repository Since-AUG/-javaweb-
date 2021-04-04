package com.bookshop.service.impl;

import com.bookshop.dao.BookDao;
import com.bookshop.dao.impl.BookDaoImpl;
import com.bookshop.pojo.Book;
import com.bookshop.pojo.Page;
import com.bookshop.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Page<Book> pageByPrice(Integer pageNo, Integer pageSize, Integer min, Integer max) {
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount%pageSize>0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = bookDao.queryForItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
}
