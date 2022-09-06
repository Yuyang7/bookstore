package com.yuyang.book.controller;

import com.yuyang.book.pojo.Cart;
import com.yuyang.book.pojo.CartItem;
import com.yuyang.book.pojo.User;
import com.yuyang.book.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author yu yang
 * @date 2022/8/31 -16:20
 */
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    //获取购物车
    @RequestMapping(value = "/cart/get", method = RequestMethod.GET)
    @ResponseBody
    public Cart getCart(HttpSession session) {
        User curUser = (User) session.getAttribute("curUser");
        Cart cart = cartService.getCart(curUser);
        cart.getTotalMoney();
        cart.getTotalCount();
        cart.getTotalBookCount();
        curUser.setCart(cart);
        session.setAttribute("curUser",curUser);
        return cart;
    }
    //清空购物车
    @RequestMapping(value = "/cart/delete", method = RequestMethod.DELETE)
    public String deleteCart(HttpSession session) {
        User user = (User) session.getAttribute("curUser");
        if (user.getCart() != null) {
            cartService.deleteCart(user);
            session.setAttribute("curUser",user);
            return "/cart/cart";
        }
        return "redirect:/index";
    }
    //修改购物车项
    @RequestMapping(value = "/cart/put/{cartItemId}/{buyCount}",method = RequestMethod.PUT)
    @ResponseBody
    public String editCart(@PathVariable("cartItemId")Integer cartItemId, @PathVariable("buyCount") Integer buyCount){
        cartService.editCart(cartItemId,buyCount);
        return null;
    }
    //结账
    @RequestMapping(value = "/cart/checkout",method = RequestMethod.GET)
    public String checkout(HttpSession session){
        User user = (User) session.getAttribute("curUser");
        if (user.getCart() != null) {
            cartService.checkout(user);
            session.setAttribute("curUser",user);

        }
        return "redirect:/index";

    }
    //删除一条购物车项

    @RequestMapping(value = "/cart/deleteCartItem/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCartItem(@PathVariable("id") Integer id) {
        cartService.deleteCartItem(new CartItem(id));
        return null;
    }
}
