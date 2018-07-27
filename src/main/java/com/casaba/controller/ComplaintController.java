package com.casaba.controller;

import com.casaba.entity.Complaint;
import com.casaba.entity.Elevator;
import com.casaba.entity.User;
import com.casaba.service.IComplaintService;
import com.casaba.service.IElevatorService;
import com.casaba.service.impl.ElevatorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.security.provider.certpath.CertId;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * created by Ulric on 2018/7/22
 */

@Controller
@RequestMapping("/complaint")
public class ComplaintController {

    private static final Log LOGGER = LogFactory.getLog(ComplaintController.class);

    @Resource
    private IElevatorService iElevatorService;

    @Resource
    private IComplaintService iComplaintService;


    /**
     * 从投诉电梯信息填写页跳转到投诉页面
     *
     * @author Ulric
     * @date 2018/7/23
     */
    @RequestMapping("/toComplaint_fillIn")
    public ModelAndView toComplaint_fillIn(String certificateOfUse) {
        LOGGER.info("=====您要投诉的电梯为：" + certificateOfUse);

        ModelAndView mv = new ModelAndView();

        mv.setViewName("complaint");

        // 查找出该电梯的设备地址
        Elevator elevator = iElevatorService.findByCertificate(certificateOfUse);
        mv.addObject("elevator", elevator);

        return mv;
    }

    /**
     * 从电梯信息列表页跳到投诉页面
     *
     * @author Ulric
     * @date 2018/7/23
     */
    @RequestMapping(value = "/toComplaint_eleInfo", method = RequestMethod.POST)
//    @ResponseBody   // @ResponseBody 注解表示返回的字符串不是视图名称，而是JSON字符串
//    public String toComplaintJsp(
    public ModelAndView toComplaint_eleInfo(
       /*@RequestBody 注解获取到JSON 数据后，将JSON数据 设置到对应的实体类的属性中*/
       /*@RequestBody Elevator elevator*/
       String certificateOfUse,String deviceAddress) {
//        LOGGER.info("=====将页面的JSON数据（String）封装为实体类对象：" + elevator);

        Elevator elevator = new Elevator();
        elevator.setCertificateOfUse(certificateOfUse);
        elevator.setDeviceAddress(deviceAddress);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("complaint");
        mv.addObject("elevator", elevator);

        return mv;
    }

    /**
     * 执行投诉操作
     *
     * @author Ulric
     * @date 2018/7/23
     */
    @RequestMapping("/doComplaint")
//    public void doComplaint(HttpSession session, HttpServletRequest request) {
    public ModelAndView doComplaint(
//            ModelMap modelmap,
//            HttpSession session,

            /*@RequestBody String certificate,
            @RequestBody String sketch,
            @RequestBody String username,
            @RequestBody String contactNum,
            @RequestBody String details*/

            String certificate,
            String sketch,
            String username,
            String contactNum,
            String details

            /*@RequestBody Elevator elevator,
            @RequestBody User user,
            @RequestBody Complaint complaint*/) {
//        String certificate = request.getParameter("certificate");
//        String sketch = request.getParameter("sketch");
//        String username = request.getParameter("username");
//        String contactNum = request.getParameter("contactNum");
//        String details = request.getParameter("details");

        /*LOGGER.info("=====接收到的参数：" +
                "\n\t#certificate：" + certificate +
                "\n\t#sketch：" + sketch +
                "\n\t#username：" + username +
                "\n\t#contactNum：" + contactNum +
                "\n\t#details：" + details);*/

        /*LOGGER.info("=====接收到的参数：\n\t#elevator：" + elevator +
                "\n\t#user：" + user +
                "\n\t#complaint: " + complaint);*/

        LOGGER.info("=====接收到的参数：\n\t#certificate：" + certificate +
                "\n\tsketch：" + sketch +
                "\n\tusername：" + username +
                "\n\tcontactNum：" + contactNum +
                "\n\tdetails：" + details);

//        session.setMaxInactiveInterval(5 * 60); // 设置 session 失效时间，单位为秒
//        session.setAttribute("user", user);
//        session.setAttribute("elevator", elevator);
//        session.setAttribute("certificate", certificate);
//        session.setAttribute("username", username);

        ModelAndView mv = new ModelAndView();
        mv.addObject("username", username);
        mv.addObject("certificate", certificate);

        // 将用户（投诉人）、投诉单、电梯信息保存到数据库
        try {
            boolean isSuccess =
                    iComplaintService.saveComplaintSheet(certificate, username, contactNum, sketch, details);
            if (!isSuccess) {
                throw new RuntimeException("保存数据失败");
            }
            mv.setViewName("complaint_success");
        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("complaint_fail");
        } finally {
            return mv;
        }
    }

}
