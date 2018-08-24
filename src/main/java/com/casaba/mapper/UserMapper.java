package com.casaba.mapper;

import com.casaba.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * create by Ulric on 2018/7/24
 */

@Component
public interface UserMapper {
    /**
     * 根据用户名搜索该用户是否已经存在
     *
     * @author Ulric
     * @date 2018/7/24
     */
    User selectByUsername(String username);

    /**
     * 获取最新插入的主键ID
     *
     * @author Ulric
     * @date 2018/7/24
     */
    long selectMaxId();

    /**
     * 根据用户名和联系方式查询
     *
     * @author casaba-u
     * @date 2018/8/3
     */
    User selectByUserame$ContactNum(@Param("username") String username, @Param("contactNum") String contactNum);

    /**
     * 根据微信用户的openid查询
     *
     * @author casaba-u
     * @date 2018/8/17
     */
    User selectByWcOpenId(@Param("openId") String openId);

    /**
     * 根据用户的基本信息，关联查询所有数据
     *
     * @author casaba-u
     * @date 2018/8/22
     */
//    User selectAllInfo(User user);

    /**
     * 添加一个用户
     *
     * @author Ulric
     * @date 2018/7/24
     */
    boolean insertUser(User user);

    /**
     * 根据用户名，更新电梯用户信息
     *
     * @author casaba-u
     * @date 2018/8/20
     */
    boolean updateUserByName(User user);

    /**
     * 根据用户名判断用户是否存在
     * @author casaba-u
     * @date 2018/8/24
     */

}
