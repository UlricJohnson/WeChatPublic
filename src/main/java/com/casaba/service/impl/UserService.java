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

        User user = null;

        if (loginUser != null) {
            user = userMapper.selectUserByUserame$ContactNum(
                    loginUser.getUsername(), loginUser.getContactNum());
        }

        LOGGER.info("=====查询出来的用户user: " + user);

        return user;
    }

    /**
     * 根据微信用户的openid查询电梯用户
     *
     * @param openId
     * @author casaba-u
     * @date 2018/8/17
     */
    @Override
    public User findByWcOpenId(String openId) {
        LOGGER.info("=====接收到的参数：\n\t#openId：" + openId);



        return null;
    }
}
