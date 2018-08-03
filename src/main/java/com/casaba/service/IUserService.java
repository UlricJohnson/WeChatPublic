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
}
