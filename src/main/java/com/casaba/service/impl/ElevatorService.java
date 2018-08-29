package com.casaba.service.impl;

import com.casaba.entity.Complaint;
import com.casaba.entity.Elevator;
import com.casaba.mapper.ElevatorMapper;
import com.casaba.service.IElevatorService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * created by Ulric on 2018/7/16
 */

//@WebService
@Service
@Component
public class ElevatorService implements IElevatorService {

    private static final Log LOGGER = LogFactory.getLog(ElevatorService.class);

    @Resource
    private ElevatorMapper elevatorMapper;

    /**
     * 根据使用证编号(CERTIFICATE_OF_USE)查找电梯
     *
     * @param certificate
     * @author Ulric
     * @date 2018/7/17
     */
    @Override
    public Elevator queryByCertificate(String certificate) {
        LOGGER.info("=====接收到的certificate为：" + certificate);

        Elevator elevator = elevatorMapper.selectByCertificate(certificate);

        return elevator;
    }

    /**
     * 根据使用证编号准确查询 或 根据使用单位地址模糊查询
     *
     * @author casaba-u
     * @date 2018/8/28
     */
    @Override
    public List<Elevator> queryElevator(String certificate, String addressOfUse) {
//        System.out.println("=====ElevatorService--findByCertificate()");
        LOGGER.info("=====接收到的参数：\n\t#certificate: " + certificate
                + "\n\t#addressOfUse: " + addressOfUse);

        List<Elevator> elevatorList = new LinkedList<>();
        // 根据使用证编号查找
        if (!StringUtils.isBlank(certificate)) {
            elevatorList = elevatorMapper.selectByCertificateFuzzy(certificate);
            /*if(elevator==null){
                return null;
            }
            elevatorList.add(elevator);*/
        } else if (!StringUtils.isBlank(addressOfUse)) {
            // 根据使用单位地址模糊查找
            String[] strings = addressOfUse.trim().split("\\s+"); // 根据一个或多个空白符号切割
            elevatorList = elevatorMapper.selectByAddressOfUse(strings);
        } else {
            return null;
        }

        // 转换日期格式
//        Date nextYearlyInspection = elevator.getNextYearlyInspection();
//        LOGGER.info("=====日期转换前：" + nextYearlyInspection);
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm E");
//        String format = simpleDateFormat.format(nextYearlyInspection);
//        Date parsedDate = null;
//        try {
//            parsedDate = simpleDateFormat.parse(format);
//            elevator.setNextYearlyInspection(parsedDate);
//            LOGGER.info("=====日期转换后：" + parsedDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } finally {
//            return elevator;
//        }

        return elevatorList;
    }

    /**
     * 根据投诉单查询相关的电梯
     *
     * @param complaintList
     * @author casaba-u
     * @date 2018/8/3
     */
    @Override
    public List<Elevator> findElevatorsByComplaints(List<Complaint> complaintList) {
        LOGGER.info("=====接收到的参数：\n\t#complaintList: " + complaintList);

        List<Elevator> elevatorList = elevatorMapper.selectByComplaints(complaintList);

        LOGGER.info("=====返回的数据：" + elevatorList);

        return elevatorList;
    }
}
