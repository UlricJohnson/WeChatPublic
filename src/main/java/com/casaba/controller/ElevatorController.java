package com.casaba.controller;

import com.casaba.entity.Elevator;
import com.casaba.service.IElevatorService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
    private IElevatorService elevatorService;

    /**
     * 根据使用证编号(CERTIFICATE_OF_USE)查找电梯
     *
     * @author Ulric
     * @date 2018/7/17
     */
//    public Map<String, Object> findByCertificate(String certificate) {
    @RequestMapping("/queryElevator")
    public ModelAndView queryElevator(String certificate/*, String addressOfUse*/) {
        LOGGER.info("=====接收到的参数\n\t#certificate：" + certificate
                /*+ "\n\t#addressOfUse: " + addressOfUse*/);

        Map<String, Object> paramMap = new HashMap<>();

        ModelAndView mv = new ModelAndView();

//        List<Elevator> elevatorList = elevatorService.queryElevator(certificate, addressOfUse);
        List<Elevator> elevatorList = elevatorService.queryElevator(certificate, null);

        if (null == elevatorList || elevatorList.isEmpty()) {
            mv.setViewName("error");
            mv.addObject("msg", "没有相关数据");
        } else {
            paramMap.put("elevatorList", elevatorList);
//            mv.setViewName("elevator_info");
            mv.setViewName("elevator_list");
            mv.addObject("paramMap", paramMap);
        }

        return mv;
    }

    /**
     * 确认电梯信息，跳转到提交投诉页面
     *
     * @author casaba-u
     * @date 2018/9/18
     */
    @RequestMapping("/checkEleMsg")
    public ModelAndView checkEleMsg(String certificateOfUse, String deviceAddress) {
        LOGGER.info("=====接收到的参数：\n\t#certificateOfUse: " + certificateOfUse
                + "\n\t#deviceAddress: " + deviceAddress);

        ModelAndView mv = new ModelAndView();



        return mv;
    }

}
