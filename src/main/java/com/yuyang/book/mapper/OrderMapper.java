package com.yuyang.book.mapper;

import com.yuyang.book.pojo.OrderBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/31 -22:46
 */
@Mapper
public interface OrderMapper {
    //添加一条订单信息
    void insert(OrderBean orderBean);
    //获取用户的所有订单
    List<OrderBean> getOrderList(@Param("id") Integer id);
}
