package com.casaba.service.impl;

import com.casaba.entity.WeChatUser;
import com.casaba.mapper.WeChatUserMapper;
import com.casaba.service.IWeChatService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by casaba-u on 2018/8/17
 */
@Service
public class WeChatService implements IWeChatService {

    private static final Log LOGGER = LogFactory.getLog(WeChatService.class);

    @Resource
    private WeChatUserMapper weChatUserMapper;

    /**
     * 添加一个微信用户
     *
     * @author casaba-u
     * @date 2018/8/17
     */
    @Override
    public boolean addWcUser(WeChatUser wcUser) {
        LOGGER.info("=====要添加的微信用户：" + wcUser);

        boolean insertSuccess = weChatUserMapper.insertWeChatUser(wcUser);

        return insertSuccess;
    }

    /**
     * 根据 open id 查询是否存在指定微信用户
     *
     * @param openid
     * @author casaba-u
     * @date 2018/8/20
     */
    @Override
    public boolean isExist(String openid) {
        LOGGER.info("=====接收到的参数：\n\t#openid：" + openid);

        WeChatUser wcUser = weChatUserMapper.selectByOpenId(openid);

        return wcUser != null;
    }
}
