package com.casaba.service;

import com.casaba.entity.WeChatUser;

/**
 * created by casaba-u on 2018/8/17
 */

public interface IWeChatService {

    /**
     * @desciption
     * @author casaba-u
     * @date 2018/8/17
     */
    boolean addWcUser(WeChatUser wcUser);

    /**
     * 根据 open id 查询是否存在指定微信用户
     * @author casaba-u
     * @date 2018/8/20
     */
    boolean isExist(String openid);

}
