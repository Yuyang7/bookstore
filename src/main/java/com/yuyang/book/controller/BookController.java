package com.yuyang.book.controller;

import com.yuyang.book.pojo.Book;
import com.yuyang.book.pojo.Cart;
import com.yuyang.book.pojo.User;
import com.yuyang.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/30 -19:06
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    //登录成功后获取所有图书信息
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String getAllBook(Model model){
        List<Book> bookList =bookService.getAllBook();
        model.addAttribute("bookList",bookList);
        return "index";
    }
    //添加图书到购物车
    @RequestMapping(value = "/book/add/{id}",method = RequestMethod.GET)
    public String addBook(@PathVariable("id") Integer bookId, HttpSession session){
        Object curUserObj = session.getAttribute("curUser");
        if (curUserObj ==null){
            return "/user/login";
        }
        User user = (User) curUserObj;
        bookService.addOrUpdate(bookId,user);
        session.setAttribute("curUser",user);
        return "redirect:/cart";
    }

}
