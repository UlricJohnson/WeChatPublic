package com.casaba.service;

import com.casaba.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * created by Ulric on 2018/7/24
 */

public interface IUserService {
    /**
     * 用户登录
     *
     * @author casaba-u
     * @date 2018/8/3
     */
    User login(@Param("user") User user);
//    User login(String username, String contactNum);

    /**
     * 用户注册
     *
     * @author casaba-u
     * @date 2018/8/20
     */
//    boolean register(String username, String contactNum);
    boolean register(User user);

    /**
     * 根据用户名查询
     *
     * @author casaba-u
     * @date 2018/9/12
     */
    User findByUsername(String username);

    /**
     * 根据微信用户的openid查询电梯用户
     *
     * @author casaba-u
     * @date 2018/8/17
     */
    User findByWcOpenId(String openId);

    /**
     * 根据用户名，更新电梯用户信息
     *
     * @author casaba-u
     * @date 2018/8/20
     */
    boolean updateUserByName(User user);

    /**
     * 判断用户是否存在
     *
     * @author casaba-u
     * @date 2018/8/24
     */
    boolean isExist(User user);
}
