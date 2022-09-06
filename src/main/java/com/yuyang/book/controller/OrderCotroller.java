package com.yuyang.book.controller;

import com.github.pagehelper.PageInfo;
import com.yuyang.book.pojo.OrderBean;
import com.yuyang.book.pojo.OrderItem;
import com.yuyang.book.pojo.User;
import com.yuyang.book.service.OrderItemService;
import com.yuyang.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author yu yang
 * @date 2022/8/31 -23:59
 */
@Controller
public class OrderCotroller {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderService orderService;
    @GetMapping("/order/get")
    public String getOrderList(HttpSession session){
        User curUser = (User) session.getAttribute("curUser");
        List<OrderBean> orderBeans = orderService.getOrderList(curUser);
        for (OrderBean orderBean : orderBeans) {
            List<OrderItem> orderItemList = orderItemService.getOrderItemList(orderBean);
            orderBean.setOrderItemList(orderItemList);
            orderBean.setOrderUser(curUser);
        }
        curUser.setOrderBeans(orderBeans);
        session.setAttribute("curUser",curUser);
        return "/order/order";
    }
    //获取订单的分页信息
    @GetMapping("/order/get/{pageNum}")
    public String getOrderListPage(@PathVariable("pageNum")Integer pageNum, Model model, HttpSession session){
        User curUser = (User) session.getAttribute("curUser");
        PageInfo<OrderBean> orderBeanPageInfo = orderService.getOrderList(curUser,pageNum);
        for (OrderBean orderBean : orderBeanPageInfo.getList()) {
            List<OrderItem> orderItemList = orderItemService.getOrderItemList(orderBean);
            orderBean.setOrderItemList(orderItemList);
            orderBean.setOrderUser(curUser);
        }
        model.addAttribute("page",orderBeanPageInfo);
        return "/order/order";
    }
}
