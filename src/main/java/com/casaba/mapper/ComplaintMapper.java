package com.casaba.mapper;

import com.casaba.entity.Complaint;
import org.springframework.stereotype.Component;

/**
 * create by Ulric on 2018/7/24
 */

@Component
public interface ComplaintMapper {

    /**
     * 添加一项投诉单
     * @author Ulric
     * @date 2018/7/24
     */
    boolean insertComplaint(Complaint complaint);

}
