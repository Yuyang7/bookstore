package com.yuyang.book.service;

import com.yuyang.book.mapper.UserMapper;
import com.yuyang.book.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yu yang
 * @date 2022/8/29 -16:09
 */

public interface UserService {
    //根据用户名和密码检查登录信息
    User loginCheck(String username, String password);
    //添加用户信息
    void insertUser(User user);
    //检查用户名
    User ckUname(String uname);
}
