package com.bookshop.dao.impl;

import com.bookshop.dao.BookDao;
import com.bookshop.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDAO<Book> implements BookDao {
    @Override
    public int update(String sql, Object... args) {
        return super.update(sql, args);
    }

    @Override
    public Book queryForOne(String sql, Object... args) {
        return super.queryForOne(sql, args);
    }

    @Override
    public List<Book> queryForList(String sql, Object... args) {
        return super.queryForList(sql, args);
    }

    @Override
    public Object queryForSingleValue(String sql, Object... args) {
        return super.queryForSingleValue(sql, args);
    }

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book( `name` , `author` , `price` , `sales` , `stock` , `img_path`) " +
                "values(?,?,?,?,?,?);";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id=?";
        return queryForOne(sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book ";
        return queryForList(sql);
    }

    @Override
    public int queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number number = (Number)queryForSingleValue(sql);
        return number.intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int size) {
        String sql = "select `id`, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
        return queryForList(sql,begin,size);
    }

    @Override
    public int queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number number = (Number)queryForSingleValue(sql,min,max);
        return number.intValue();
    }

    @Override
    public List<Book> queryForItemsByPrice(int begin, int size, int min, int max) {
        String sql = "select `id`, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath " +
                "from t_book where `price` between ? and ? order by `price` limit ?,?";
        return queryForList(sql,min,max,begin,size);
    }
}
