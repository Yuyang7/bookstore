package com.yuyang.book.service;

import com.yuyang.book.pojo.OrderBean;
import com.yuyang.book.pojo.OrderItem;

import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/31 -23:34
 */
public interface OrderItemService {

    void insert(OrderItem orderItem);


    List<OrderItem> getOrderItemList(OrderBean orderBean);
}
