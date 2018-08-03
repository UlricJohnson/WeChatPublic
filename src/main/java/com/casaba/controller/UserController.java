package com.casaba.controller;

import com.casaba.entity.Complaint;
import com.casaba.entity.User;
import com.casaba.service.IComplaintService;
import com.casaba.service.IElevatorService;
import com.casaba.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by casaba-u on 2018/8/3
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Log LOGGER = LogFactory.getLog(UserController.class);

    @Resource
    private IUserService iUserService;

    @Resource
    private IComplaintService iComplaintService;

    @Resource
    private IElevatorService iElevatorService;

    /**
     * 用户登录
     *
     * @author casaba-u
     * @date 2018/8/3
     */
    @RequestMapping("/login")

    public ModelAndView login(String username, String contactNum) {
        LOGGER.info("=====接收到的参数：\n\t#username: " + username +
                "\n\t#contactNum: " + contactNum);

        ModelAndView mv = new ModelAndView();

        User user = new User();
        user.setUsername(username);
        user.setContactNum(contactNum);

        // 用户登录
        User loginUser = iUserService.login(user);

        if (user != null) {
            // 查询该用户的所有投诉单
            List<Complaint> complaintList = iComplaintService.findComplaintsByUser(loginUser);

            // 根据投诉单查询相关的电梯


            mv.addObject("loginUser", loginUser);
            mv.addObject("complaintList", complaintList);
            mv.setView("my_complaint");
        } else {
            mv.addObject("msg", "用户不存在");
            mv.setView("not_found");
        }

        return mv;
    }
}
