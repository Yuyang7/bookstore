package com.yuyang.book.service;

import com.github.pagehelper.PageInfo;
import com.yuyang.book.pojo.OrderBean;
import com.yuyang.book.pojo.User;

import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/31 -22:45
 */
public interface OrderService {
    // 添加一条订单
    OrderBean insert(User user);

    //  获取用户的订单
    PageInfo<OrderBean> getOrderList(User curUser, Integer pageNum);

    List<OrderBean> getOrderList(User curUser);
}
