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
    public ModelAndView queryElevator(String certificate, String addressOfUse) {
        LOGGER.info("=====接收到的参数\n\t#certificate：" + certificate
                + "\n\t#addressOfUse: " + addressOfUse);

        Map<String, Object> resultMap = new HashMap<>();

        ModelAndView mv = new ModelAndView();

        List<Elevator> elevatorList = elevatorService.queryElevator(certificate, addressOfUse);

        if (null == elevatorList || elevatorList.isEmpty()) {
//            resultMap.put("success", false);
//            resultMap.put("message", "没有数据");
            mv.setViewName("error");
            mv.addObject("msg", "没有相关数据");
        } else {
            resultMap.put("elevatorList", elevatorList);
            mv.setViewName("elevator_info");
            if (!StringUtils.isBlank(certificate)) {
                mv.addObject("queryByCertificate", true); // 在搜索结果页面显示编号
            } else if (!StringUtils.isBlank(addressOfUse)) {
                mv.addObject("queryByCertificate", false);
            }
            mv.addObject("paramMap", resultMap);
        }

        return mv;
    }

}
