package com.casaba.service.impl;

import com.casaba.entity.WeChatUser;
import com.casaba.service.IWeChatService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * created by casaba-u on 2018/8/17
 */
@Service
public class WeChatService implements IWeChatService {

    private static final Log LOGGER = LogFactory.getLog(WeChatService.class);

    /**
     * @param wcUser
     * @desciption
     * @author casaba-u
     * @date 2018/8/17
     */
    @Override
    public boolean addWcUser(WeChatUser wcUser) {
        LOGGER.info("=====要添加的微信用户：" + wcUser);



        return false;
    }
}
