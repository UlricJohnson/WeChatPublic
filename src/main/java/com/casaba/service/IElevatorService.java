package com.casaba.service;

import com.casaba.entity.Complaint;
import com.casaba.entity.Elevator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by Ulric on 2018/7/16
 */

//@Component
public interface IElevatorService {
    /**
     * 根据使用证编号(CERTIFICATE_OF_USE)查找电梯
     *
     * @author Ulric
     * @date 2018/7/17
     */
    Elevator findByCertificate(String certificate);

    /**
     * 根据投诉单查询相关的电梯
     *
     * @author casaba-u
     * @date 2018/8/3
     */
    List<Elevator> findElevatorsByComplaints(List<Complaint> complaintList);
}
