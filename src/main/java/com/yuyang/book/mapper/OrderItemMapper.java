package com.yuyang.book.mapper;

import com.yuyang.book.pojo.OrderBean;
import com.yuyang.book.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/31 -23:36
 */
@Mapper
public interface OrderItemMapper {
    //添加一条订单项
    void insert(OrderItem orderItem);
    //根据订单获取订单项
    List<OrderItem> getOrderItemList(OrderBean orderBean);
    //获取订单的图书数量
    Integer getTotalBookCount(OrderBean orderBean);
}
