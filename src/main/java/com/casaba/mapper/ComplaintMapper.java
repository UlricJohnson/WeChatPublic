package com.casaba.mapper;

import com.casaba.entity.Complaint;
import com.casaba.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create by Ulric on 2018/7/24
 */

@Component
public interface ComplaintMapper {

    /**
     * 添加一项投诉单
     *
     * @author Ulric
     * @date 2018/7/24
     */
    boolean insertComplaint(Complaint complaint);

    /**
     * 根据用户查找投诉单
     *
     * @author casaba-u
     * @date 2018/8/3
     */
    List<Complaint> selectComplaintsByUser(User user);

}
