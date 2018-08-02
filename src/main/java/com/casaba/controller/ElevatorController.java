package com.casaba.controller;

import com.casaba.entity.Elevator;
import com.casaba.service.IElevatorService;
import com.casaba.service.impl.ElevatorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 电梯
 * <p>
 * created by Ulric on 2018/7/17
 */

@RestController
@RequestMapping("/elevator")
public class ElevatorController {

    private static final Log LOGGER = LogFactory.getLog(ElevatorController.class);

    @Resource
    private IElevatorService iElevatorService;

    /**
     * 根据使用证编号(CERTIFICATE_OF_USE)查找电梯
     *
     * @author Ulric
     * @date 2018/7/17
     */
//    public Map<String, Object> findByCertificate(String certificate) {
    @RequestMapping("/findByCertificate")
    public ModelAndView findByCertificate(String certificate) {
//        System.out.println("=====ElevatorController--findByCertificate()");
        LOGGER.info("=====接收到的certificate为：" + certificate);

//        Map<String, Object> resultMap = new HashMap<>();

        ModelAndView mv = new ModelAndView();

        Elevator elevator = iElevatorService.findByCertificate(certificate);
//        Elevator elevator = elevatorService.findByCertificate(certificate);

        if (null == elevator) {
//            resultMap.put("success", false);
//            resultMap.put("message", "没有数据");
            mv.setViewName("not_found");
            mv.addObject("msg", "没有数据");
        } else {
//            resultMap.put("success", true);
//            resultMap.put("elevator", elevator);
            mv.setViewName("elevator_info");
            mv.addObject("elevator", elevator);
        }

        return mv;
//        return resultMap;
    }

//    private ElevatorService elevatorService;
}
