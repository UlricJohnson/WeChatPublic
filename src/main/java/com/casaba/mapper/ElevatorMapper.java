package com.casaba.mapper;

import com.casaba.entity.Complaint;
import com.casaba.entity.Elevator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create by Ulric on 2018/7/17
 */

//@Mapper
@Component
public interface ElevatorMapper {
    /**
     * 根据使用证编号(CERTIFICATE_OF_USE)查找电梯
     *
     * @param certificate
     * @author Ulric
     * @date 2018/7/17
     */
    Elevator selectByCertificate(String certificate);

    /**
     * 根据投诉单查询相关的电梯
     *
     * @author Ulric
     * @date 2018/8/5
     */
    List<Elevator> selectByComplaints(List<Complaint> complaintList);

    /**
     * 根据（多个）使用单位地址模糊查找
     *
     * @author casaba-u
     * @date 2018/8/28
     */
    List<Elevator> selectByAddressOfUse(String[] addressOfUses);
}
