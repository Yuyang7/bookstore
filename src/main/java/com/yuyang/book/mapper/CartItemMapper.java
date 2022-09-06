package com.yuyang.book.mapper;

import com.yuyang.book.pojo.CartItem;
import com.yuyang.book.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/31 -14:00
 */
@Mapper
public interface CartItemMapper {
    //获得购物车项
    List<CartItem> getCartItem(User user);

    //更新购物项信息
    void updateCartItem(CartItem cartItem);

    //    向购物车添加一条购物项
    void addCartItem(CartItem cartItem);

    //删除用户的购物项
    void deleteCartItem(@Param("ids") List<Integer> ids);
    //删除一条购物车项
    void deleteCartItem2(CartItem cartItem);
}
