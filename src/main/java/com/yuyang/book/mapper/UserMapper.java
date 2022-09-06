package com.yuyang.book.mapper;

import com.yuyang.book.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yu yang
 * @date 2022/8/29 -15:42
 */
@Mapper
public interface UserMapper {
    //根据用户名和密码检查登录信息
    User loginCheck(@Param("uname") String username, @Param("pwd")String password);
    //添加一条用户信息
    void insertUser(User user);
    //检查用户名
    User ckUname(String uname);
}
