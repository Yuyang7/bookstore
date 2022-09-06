package com.yuyang.book.service.impl;


import com.yuyang.book.pojo.*;
import com.yuyang.book.service.CartItemService;
import com.yuyang.book.service.CartService;
import com.yuyang.book.service.OrderItemService;
import com.yuyang.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu yang
 * @date 2022/8/31 -13:54
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;


    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = cartItemService.getCartItem(user);
        Cart cart = new Cart();
        Map<Integer, CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList) {
            Integer id = cartItem.getBook().getId();
            cartItemMap.put(id, cartItem);
        }
        cart.setCartItemMap(cartItemMap);

        return cart;
    }

    @Override
    public void deleteCart(User user) {
        Cart cart = user.getCart();
        List<Integer> ids = new ArrayList<>();
        for (CartItem value : cart.getCartItemMap().values()) {
            if (value != null) {
                ids.add(value.getUserBean().getId());
            }
        }
        user.setCart(new Cart());
        cartItemService.deleteCart(ids);
    }

    @Override
    public void editCart(Integer cartItemId, Integer buyCount) {
        CartItem cartItem = new CartItem(cartItemId, buyCount);
        cartItemService.updateCartItem(cartItem);
    }

    @Override
    public void checkout(User user) {
        //清空购物车，生成一条订单 和订单项
        List<CartItem> cartItemList = cartItemService.getCartItem(user);
        if (cartItemList != null && cartItemList.size() > 0) {
            OrderBean orderBean = orderService.insert(user);
            for (CartItem cartItem : user.getCart().getCartItemMap().values()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setBook(cartItem.getBook());
                orderItem.setBuyCount(cartItem.getBuyCount());
                orderItem.setOrderBean(orderBean);
                orderItemService.insert(orderItem);
            }
            deleteCart(user);
        }
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        cartItemService.deleteCartItem(cartItem);
    }
}
