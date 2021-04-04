package com.bookshop.test;

import com.bookshop.service.BookService;
import com.bookshop.service.impl.BookServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1,4));
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
    }
}