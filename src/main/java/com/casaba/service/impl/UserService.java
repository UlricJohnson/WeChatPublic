package com.casaba.service.impl;

import com.casaba.entity.Complaint;
import com.casaba.entity.User;
import com.casaba.entity.WeChatUser;
import com.casaba.mapper.ComplaintMapper;
import com.casaba.mapper.UserMapper;
import com.casaba.mapper.WeChatUserMapper;
import com.casaba.service.IUserService;
import com.casaba.util.CommonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by Ulric on 2018/7/24
 */

@Service
public class UserService implements IUserService {

    private static final Log LOGGER = LogFactory.getLog(UserService.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private ComplaintMapper complaintMapper;

    @Resource
    private WeChatUserMapper weChatUserMapper;

    /**
     * 用户登录
     *
     * @author casaba-u
     * @date 2018/8/3
     */
    @Override
    public User login(User user) {
//    public User login(String username, String contactNum) {
        LOGGER.info("=====接收到的参数：\n\t#User: " + user);
//        LOGGER.info("=====接收到的参数：\n\t#username: " + username +
//                "\n\t#contactNum：" + contactNum);

        User loginUser = null;

        if (user != null) {
//            loginUser = userMapper.selectUserByUserame$ContactNum(
//                    loginUser.getUsername(), loginUser.getContactNum());

//            loginUser = userMapper.selectAllInfo(user);

            loginUser = userMapper.selectByUsername(user.getUsername());

            WeChatUser wcUser = weChatUserMapper.selectByOpenId(user.getWcOpenId());

            List<Complaint> complaintList = complaintMapper.selectComplaintsByUser(loginUser);

            loginUser.setWeChatUser(wcUser);
            loginUser.setComplaintList(complaintList);
        }

//        if ((username != null && !username.equals("")) && (contactNum != null && !contactNum.equals(""))) {
//            loginUser = userMapper.selectByUserame$ContactNum(username, contactNum);
//        }

        LOGGER.info("=====查询出来的用户user: " + loginUser);

        return loginUser;
    }

    /**
     * 用户注册
     *
     * @author casaba-u
     * @date 2018/8/20
     */
    @Override
//    public boolean register(String username, String contactNum) {
    public boolean register(User user) {
//        User user = new User();
//        user.setUsername(username);
//        user.setContactNum(contactNum);

        boolean success = false;

        if (user != null && (!CommonUtil.isStringBlank(user.getUsername()) && !CommonUtil.isStringBlank(user.getContactNum()))) {
            success = userMapper.insertUser(user);
        }

        return success;
    }

    /**
     * 根据用户名查询
     *
     * @param username
     * @author casaba-u
     * @date 2018/9/12
     */
    @Override
    public User findByUsername(String username) {
        User user = userMapper.selectByUsername(username);
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

    /**
     * 判断用户是否存在
     *
     * @author casaba-u
     * @date 2018/8/24
     */
    @Override
    public boolean isExist(User user) {
        if (user == null || null == user.getUsername() || user.getUsername().equals("")) {
            return false;
        }

        User user1 = userMapper.selectByUsername(user.getUsername());

        return user1 != null;
    }
}
