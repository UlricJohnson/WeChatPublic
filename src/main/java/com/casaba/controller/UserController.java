package com.casaba.controller;

import com.casaba.entity.Complaint;
import com.casaba.entity.Elevator;
import com.casaba.entity.User;
import com.casaba.service.IComplaintService;
import com.casaba.service.IElevatorService;
import com.casaba.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

        LOGGER.info("=====用户登录：查询出来的用户：" + loginUser);

        if (user != null) {
            // 该用户的所有投诉单
//            List<Complaint> complaintList = iComplaintService.findComplaintsByUser(loginUser);
            List<Complaint> complaintList = loginUser.getComplaintList();

            // 根据投诉单查询相关的电梯
            List<Elevator> elevatorList = iElevatorService.findElevatorsByComplaints(complaintList);

            // 把电梯对象赋值到 complaintList 的对象中，这样在页面上就可以通过 complaint 对象获取电梯的使用证编号
            for (Complaint complaint : complaintList) {
                for (Elevator elevator : elevatorList) {
                    if (elevator.getId().equals(complaint.getElevatorId())) {
                        complaint.setElevator(elevator);
                    }
                }
            }

            LOGGER.info("=====返回页面的数据：\n\t#loginUser: " + loginUser +
                    "\n\t#complaintList: " + complaintList);
//                    "\n\t#elevatorList: " + elevatorList);

//            LOGGER.info("=====complaintList中的Elevator：");
//            for (int i = 0; i < complaintList.size(); i++) {
//                System.out.println("=====#" + (i + 1) + ": " + complaintList.get(i).getElevator());
//            }

            mv.addObject("loginUser", loginUser);
            mv.addObject("complaintList", complaintList);
//            mv.addObject("elevatorList", elevatorList);
            mv.setViewName("my_complaint");
        } else {
            mv.addObject("msg", "用户不存在");
            mv.setViewName("not_found");
        }

        return mv;
    }
}
