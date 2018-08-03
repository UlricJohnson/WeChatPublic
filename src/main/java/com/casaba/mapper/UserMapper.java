package com.casaba.mapper;

import com.casaba.entity.User;
import org.apache.ibatis.annotations.Mapper;
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
    User findUserByUsername(String username);

    /**
     * 添加一个用户
     *
     * @author Ulric
     * @date 2018/7/24
     */
    boolean addUser(User user);

    /**
     * 获取最新插入的主键ID
     *
     * @author Ulric
     * @date 2018/7/24
     */
    long selectMaxId();

    /**
     * 根据传入的对象查询
     *
     * @author casaba-u
     * @date 2018/8/3
     */
    User selectUser(User user);
}
