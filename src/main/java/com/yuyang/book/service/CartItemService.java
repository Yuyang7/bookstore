package com.yuyang.book.service;

import com.yuyang.book.pojo.CartItem;
import com.yuyang.book.pojo.User;

import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/31 -13:59
 */
public interface CartItemService {
    //获得购物车项
    List<CartItem> getCartItem(User user);
    //更新购物项信息
    void updateCartItem(CartItem cartItem);
    //向购物车添加购物项
    void addCartItem(CartItem cartItem);
    //清空购物车
    void deleteCart(List<Integer> ids);
    //删除一条购物项
    void deleteCartItem(CartItem cartItem);
}
