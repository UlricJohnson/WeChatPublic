package com.casaba.service;

import com.casaba.entity.User;

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
    User login(User loginUser);

    /**
     * 根据微信用户的openid查询电梯用户
     *
     * @author casaba-u
     * @date 2018/8/17
     */
    User findByWcOpenId(String openId);
}
