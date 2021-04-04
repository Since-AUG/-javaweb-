package com.bookshop.test;

import com.bookshop.dao.BookDao;
import com.bookshop.dao.impl.BookDaoImpl;
import com.bookshop.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"国哥好帅 ","191125",new BigDecimal(9999),1100000,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"国哥好帅aaa","国哥",new BigDecimal(9999),1100000,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for(Book book:bookDao.queryBooks()){
            System.out.println(book);
        }
    }
    @Test
    public void queryForPageTotalCount(){
        System.out.println(bookDao.queryForPageTotalCount());
    }
    @Test
    public void queryForItems(){
        for(Book book:bookDao.queryForItems(10,5)){
            System.out.println(book);
        }
    }
    @Test
    public void queryForPageTotalCountByPrice(){
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }
    @Test
    public void queryForItemsByPrice(){
        for(Book book:bookDao.queryForItemsByPrice(1,4,10,50)){
            System.out.println(book);
        }
    }
}