package com.casaba.mapper;

import com.casaba.entity.Elevator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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
}
