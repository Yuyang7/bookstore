package com.yuyang.book.service.impl;

import com.yuyang.book.mapper.CartItemMapper;
import com.yuyang.book.pojo.CartItem;
import com.yuyang.book.pojo.User;
import com.yuyang.book.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/31 -22:39
 */
@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public List<CartItem> getCartItem(User user) {
        return cartItemMapper.getCartItem(user);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemMapper.updateCartItem(cartItem);
    }

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemMapper.addCartItem(cartItem);
    }

    @Override
    public void deleteCart(List<Integer> ids) {
        if (ids != null && ids.size() > 0) {
            cartItemMapper.deleteCartItem(ids);
        }
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        cartItemMapper.deleteCartItem2(cartItem);
    }

}
