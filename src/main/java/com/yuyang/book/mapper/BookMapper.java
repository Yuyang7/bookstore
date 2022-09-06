package com.yuyang.book.mapper;

import com.yuyang.book.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/30 -19:07
 */
@Mapper
public interface BookMapper {
    //获取所有图书
    List<Book> getAllBook();
    Book getBookById(@Param("bookId") Integer id);

}
