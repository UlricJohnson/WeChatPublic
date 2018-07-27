//package com.casaba.controller;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * created by Ulric on 2018/7/23
// */
//
//@RestController
//public class TestController {
//
//    private static final Log LOGGER = LogFactory.getLog(TestController.class);
//
//    /**
//     * @author Ulric
//     * @date 2018/7/23
//     */
//    public ModelMap test(String username, String password) {
//        LOGGER.info("=====username：" + username + "\tpassword：" + password);
//
//        ModelMap mm = new ModelMap();
//
//        if (username != null && password != null) {
//            if (!username.equals("ulric")) {
//                mm.put("msg", "用户名错误");
//                return mm;
//            }
//            if (!password.equals("123456")) {
//                mm.put("msg", "密码错误");
//                return mm;
//            }
//            mm.put("username", username);
//        } else {
//            mm.put("msg", "请输入用户名或密码");
//        }
//        return mm;
//    }
//
//}
