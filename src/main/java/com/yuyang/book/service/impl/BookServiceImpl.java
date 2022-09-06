package com.yuyang.book.service.impl;

import com.yuyang.book.mapper.BookMapper;
import com.yuyang.book.pojo.Book;
import com.yuyang.book.pojo.Cart;
import com.yuyang.book.pojo.CartItem;
import com.yuyang.book.pojo.User;
import com.yuyang.book.service.BookService;
import com.yuyang.book.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu yang
 * @date 2022/8/30 -19:06
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CartItemService cartItemService;

    @Override
    public List<Book> getAllBook() {
        List<Book> bookList = bookMapper.getAllBook();
        return bookList;
    }

    @Override
    public void addOrUpdate(Integer bookId, User user) {
        //购物车存在该图书 cartItem.bookCount+1 不存在 向购物测添加一条数据
        Cart cart = user.getCart();
        Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
        if (cartItemMap == null){
            cartItemMap = new HashMap<>();
        }
        if (cartItemMap.containsKey(bookId)) {
            CartItem cartItem = cartItemMap.get(bookId);
            cartItem.setBuyCount(cartItem.getBuyCount() + 1);
            cartItemService.updateCartItem(cartItem);
        } else {
            CartItem cartItem = new CartItem(new Book(bookId), 1, user);
            cartItemService.addCartItem(cartItem);
            cartItemMap.put(bookId, cartItem);
        }
        cart.setCartItemMap(cartItemMap);
        user.setCart(cart);
    }

    @Override
    public Book getBookById(Integer id) {
        Book book = bookMapper.getBookById(id);
        return book;

    }
}
