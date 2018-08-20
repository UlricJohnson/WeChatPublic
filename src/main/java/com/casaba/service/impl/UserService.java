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
     * @author casaba-u
     * @date 2018/8/3
     */
    @Override
    public User login(String username, String contactNum) {
//        LOGGER.info("=====接收到的参数：\n\t#User: " + loginUser);
        LOGGER.info("=====接收到的参数：\n\t#username: " + username +
                "\n\t#contactNum：" + contactNum);

        User user = null;

//        if (loginUser != null) {
//            user = userMapper.selectUserByUserame$ContactNum(
//                    loginUser.getUsername(), loginUser.getContactNum());
//        }

        if ((username != null && !username.equals("")) && (contactNum != null && !contactNum.equals(""))) {
            user = userMapper.selectUserByUserame$ContactNum(username, username);
        }

        LOGGER.info("=====查询出来的用户user: " + user);

        return user;
    }

    /**
     * 用户注册
     *
     * @param username
     * @param contactNum
     * @author casaba-u
     * @date 2018/8/20
     */
    @Override
    public boolean register(String username, String contactNum) {
        User user = new User();
        user.setUsername(username);
        user.setContactNum(contactNum);

        boolean success = userMapper.insertUser(user);

        return success;
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

        User user = userMapper.selectByWcOpenId(openId);

        return user;
    }

    /**
     * 根据用户名，更新电梯用户信息
     *
     * @param user
     * @author casaba-u
     * @date 2018/8/20
     */
    @Override
    public boolean updateUserByName(User user) {
        boolean success = userMapper.updateUserByName(user);

        return success;
    }
}
