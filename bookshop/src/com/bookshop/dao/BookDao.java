package com.bookshop.dao;

import com.bookshop.pojo.Book;

import javax.print.DocFlavor;
import java.util.List;

public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    public int queryForPageTotalCount();
    public List<Book> queryForItems(int begin, int size);
    public int queryForPageTotalCountByPrice(int min, int max);
    public List<Book> queryForItemsByPrice(int begin, int size, int min, int max);
}
