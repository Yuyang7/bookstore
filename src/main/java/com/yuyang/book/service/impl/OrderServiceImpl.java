package com.yuyang.book.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuyang.book.mapper.OrderMapper;
import com.yuyang.book.pojo.OrderBean;
import com.yuyang.book.pojo.User;
import com.yuyang.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author yu yang
 * @date 2022/8/31 -22:45
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderBean insert(User user) {
        OrderBean orderBean = new OrderBean();
        LocalDateTime now = LocalDateTime.now();
        orderBean.setOrderNo(UUID.randomUUID() + "_" + now.getYear() + now.getMonth() +
                now.getDayOfMonth() + now.getHour() + now.getMinute() + now.getSecond());

        orderBean.setOrderDate(Timestamp.valueOf(now));
        Double totalMoney = user.getCart().getTotalMoney();
        orderBean.setOrderMoney(totalMoney);
        orderBean.setOrderUser(user);
        Integer totalBookCount = user.getCart().getTotalBookCount();
        orderBean.setTotalBookCount(totalBookCount);
        orderBean.setOrderStatus(0);
        orderMapper.insert(orderBean);
        return orderBean;

    }

    @Override
    public PageInfo<OrderBean> getOrderList(User curUser, Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<OrderBean> orderList = orderMapper.getOrderList(curUser.getId());
        return new PageInfo<>(orderList,3);
    }

    @Override
    public List<OrderBean> getOrderList(User curUser) {
        List<OrderBean> orderList = orderMapper.getOrderList(curUser.getId());
        if (orderList !=null){
            for (OrderBean orderBean : orderList) {
                Date orderDate = orderBean.getOrderDate();

                Date date = orderDate;
                orderBean.setOrderDate(date);
            }
        }
        return orderList;
    }
}
