package com.casaba.mapper;

import com.casaba.entity.WeChatUser;
import org.apache.ibatis.annotations.Param;

/**
 * created by casaba-u on 2018/8/15
 */

public interface WeChatUserMapper {

    /**
     * 根据 open id 查找微信用户
     *
     * @author casaba-u
     * @date 2018/8/15
     */
    WeChatUser selectByOpenId(@Param("openId") String openId);

}
