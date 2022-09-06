package com.yuyang.book.service;

import com.yuyang.book.pojo.Book;
import com.yuyang.book.pojo.Cart;
import com.yuyang.book.pojo.User;

import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/30 -19:06
 */
public interface BookService {
    //获取所有图书信息
    List<Book> getAllBook();
    //向购物车中添加一本图书
    void addOrUpdate(Integer bookId, User user);
    //根据book的id查询book
    Book getBookById(Integer id);
}
