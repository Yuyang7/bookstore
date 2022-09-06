package com.yuyang.book.service.impl;

import com.yuyang.book.mapper.OrderItemMapper;
import com.yuyang.book.pojo.Book;
import com.yuyang.book.pojo.OrderBean;
import com.yuyang.book.pojo.OrderItem;
import com.yuyang.book.service.BookService;
import com.yuyang.book.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/31 -23:35
 */
@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private BookService bookService;

    @Override
    public void insert(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItemList(OrderBean orderBean) {
        List<OrderItem> orderItemList=orderItemMapper.getOrderItemList(orderBean);
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderBean(orderBean);
            Book book = bookService.getBookById(orderItem.getBook().getId());
            orderItem.setBook(book);
        }
        return orderItemList;
    }

}
