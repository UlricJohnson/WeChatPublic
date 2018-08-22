package com.casaba.controller;

import com.casaba.entity.Complaint;
import com.casaba.entity.Elevator;
import com.casaba.entity.User;
import com.casaba.entity.WeChatUser;
import com.casaba.service.IComplaintService;
import com.casaba.service.IElevatorService;
import com.casaba.service.IUserService;
import com.casaba.service.impl.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ModelAndView login(HttpServletRequest request, String username, String contactNum/*, String toJsp, String openId*/) {
        LOGGER.info("=====接收到的参数：\n\t#username: " + username +
                        "\n\t#contactNum: " + contactNum
//                "\n\t#toJsp: " + toJsp +
                /*"\n\t#openId: " + openId*/);

        ModelAndView mv = new ModelAndView();

        HttpSession session = request.getSession();
        String toJsp = (String) session.getAttribute("toJsp");
        Map paramMap = (Map) session.getAttribute("paramMap");

        User user = new User();
        user.setUsername(username);
        user.setContactNum(contactNum);

        WeChatUser wcUser = (WeChatUser) paramMap.get("wcUser");

        // 微信用户和电梯用户进行绑定
        if (wcUser.getOpenId() != null && (!"".equals(wcUser.getOpenId()))) {
            user.setWcOpenId(wcUser.getOpenId());
        }

        // 用户登录
//        User loginUser = iUserService.login(user.getUsername(), user.getContactNum());
        User loginUser = iUserService.login(user);

        LOGGER.info("=====用户登录：查询出来的用户：" + loginUser);

        if (loginUser != null) {
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

//            mv.addObject("loginUser", loginUser);
//            mv.addObject("complaintList", complaintList);

            paramMap.put("loginUser", loginUser);
            paramMap.put("complaintList", complaintList);

            if (toJsp != null && (!"".equals(toJsp))) {
                mv.setViewName(toJsp);
            }
//            mv.setViewName("my_complaint");
        } else {
//            mv.addObject("msg", "用户不存在");
//            mv.setViewName("error");

            // 用户不存在则注册
            iUserService.register(user.getUsername(), user.getContactNum());

            // 绑定微信用户和电梯用户
            boolean bindSuccess = iUserService.updateUserByName(user);

            mv.setViewName(toJsp); // 注册完之后跳转页面
//            mv.addObject("eleUser", user);
            paramMap.put("eleUser", user);
        }

        session.setAttribute("paramMap", paramMap);

        return mv;
    }

    /**
     * 用户注册
     *
     * @author casaba-u
     * @date 2018/8/20
     */
    @RequestMapping("/register")
    public ModelAndView register(String username, String contactNum, String toJsp) {
        ModelAndView mv = new ModelAndView();

        boolean success = iUserService.register(username, contactNum);

        Map paramMap = new HashMap();

        if (success) { // 注册成功，跳转到页面
            mv.setViewName(toJsp);
//            User loginUser = iUserService.login(username, contactNum);
            User user = new User();
            user.setUsername(username);
            user.setContactNum(contactNum);
            User loginUser = iUserService.login(user);
//            mv.addObject("user", loginUser);
            paramMap.put("user", loginUser);
            mv.addObject("paramMap", paramMap);
        } else {
            mv.setViewName("error");
            mv.addObject("msg", "注册失败");
        }

        return mv;
    }

    /**
     * @desciption
     * @author casaba-u
     * @date 2018/8/22
     */
    @RequestMapping("/myComplaint")
    public ModelAndView myComplaint(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        HttpSession session = request.getSession();
        session.setAttribute("toJsp", "my_complaint");

        mv.setViewName("redirect:/wechat/wclogin");

        return mv;
    }
}
