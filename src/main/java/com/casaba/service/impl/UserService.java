package com.casaba.service.impl;

import com.casaba.entity.User;
import com.casaba.mapper.UserMapper;
import com.casaba.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by Ulric on 2018/7/24
 */

@Service
public class UserService implements IUserService {

    private static final Log LOGGER = LogFactory.getLog(UserService.class);

    @Resource
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param loginUser
     * @author casaba-u
     * @date 2018/8/3
     */
    @Override
    public User login(User loginUser) {
        LOGGER.info("=====接收到的参数：\n\t#User: " + loginUser);

        if (loginUser != null) {
            return userMapper.selectUser(loginUser);
        }

        return null;
    }
}
