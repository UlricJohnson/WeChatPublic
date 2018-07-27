package com.casaba.service;

import com.casaba.entity.Elevator;
import org.springframework.stereotype.Component;

/**
 * create by Ulric on 2018/7/16
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
}
