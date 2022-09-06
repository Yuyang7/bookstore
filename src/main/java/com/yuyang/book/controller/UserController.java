package com.yuyang.book.controller;

import com.yuyang.book.pojo.Cart;
import com.yuyang.book.pojo.User;
import com.yuyang.book.service.CartService;
import com.yuyang.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yu yang
 * @date 2022/8/29 -15:53
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;



    //登录检查
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String loginCheck(String username, String password, HttpSession session) throws IOException {
        User user = userService.loginCheck(username, password);
        System.out.println("user = " + user);
        if (user == null) {
            return "user/login";
        }
        Cart cart = cartService.getCart(user);
        user.setCart(cart);
        session.setAttribute("curUser",user);
        return "redirect:/index";
    }

    //检查验证码
    @RequestMapping(value = "/user/ckVerifyCode/{verifyCode}", method = RequestMethod.GET)
    @ResponseBody
    public String regist(User user, HttpServletRequest request, @PathVariable("verifyCode") String verifyCode, HttpServletResponse response) throws IOException {
        //获取生成的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        return verifyCode != null && verifyCodeExpected.equals(verifyCode) ? "{message:'true'}" : null;
    }

    //检查用户名
    @RequestMapping(value = "/user/ckUname/{uname}", method = RequestMethod.GET)
    @ResponseBody
    public User ckUname(@PathVariable("uname") String uname) {
        User user = userService.ckUname(uname);
        return user;
    }
    //注册用户
    @RequestMapping(value = "/user/regist",method = RequestMethod.PUT)
    public String registUser(User user,Model model){
        userService.insertUser(user);
        System.out.println(user);
        return "/user/login";
    }
    //注销用户
    @RequestMapping(value = "/user/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "/user/login";
    }
}
