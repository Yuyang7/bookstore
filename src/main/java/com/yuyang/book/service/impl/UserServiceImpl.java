package com.yuyang.book.service.impl;

import com.yuyang.book.mapper.UserMapper;
import com.yuyang.book.pojo.User;
import com.yuyang.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yu yang
 * @date 2022/8/29 -16:10
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User loginCheck(String username, String password) {
        return userMapper.loginCheck(username, password);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User ckUname(String uname) {
        return userMapper.ckUname(uname);
    }
}
