package com.yuyang.book.service;

import com.yuyang.book.pojo.Cart;
import com.yuyang.book.pojo.CartItem;
import com.yuyang.book.pojo.User;

import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/31 -13:54
 */
public interface CartService {
    //创建购物车
    Cart getCart(User user);
    //删除购物车
    void deleteCart(User user);
    //编辑购物车项
    void editCart(Integer artItemId, Integer buyCount);
    //结账
    void checkout(User user);
    //删除一条购物车项
    void deleteCartItem(CartItem cartItem);
}
