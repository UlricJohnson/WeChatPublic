package com.casaba.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用于跳转页面，因为页面在WEB-INF文件夹下，不能直接跳转
 * <p>
 * created by Ulric on 2018/7/22
 */

@Controller
@RequestMapping("/toJsp")
public class ToJspController {

    private static final Log LOGGER = LogFactory.getLog(ToJspController.class);

    /**
     * 跳到 JSP 页面
     *
     * @author Ulric
     * @date 2018/7/22
     */
    @RequestMapping("/{jspName}")
//    public ModelAndView toJsp(@PathVariable String jspName) {
    public String toJsp(@PathVariable String jspName) {
//        ModelAndView mv = new ModelAndView();

        LOGGER.info("=====准备跳转页面：" + jspName);

        // 跳转到电梯用户的登录页面前先进行微信用户登录
//        if (jspName != null && jspName.equals("login")) {
//            LOGGER.info("=====重定向到微信用户登录");
//            return "redirect:/wechat/wclogin";
//        }

        // 判断url是否携带参数（split 切割问号?时需要采用以下形式，因为API使用了正则去匹配，而问号?是关键字）
//        String[] strs = jspName.split("[?]"); // 如：{"viewName" [, "param1=v1&param2=v2"]}

        // 没有携带参数，直接跳转

//        if (strs.length <= 1) {
//            mv.setViewName(jspName);
//        } else {
//            mv.setViewName(strs[0]);  // 第1个为跳转的页面，第2个为参数串
//            String[] params = strs[1].split("&"); // 如：{"param1=v1", "param2=v2"}
//            for (String paramPair : params) {
//                mv.addObject(paramPair.split("=")[0], paramPair.split("=")[1]);
//            }
//        }

        return jspName;
    }


}
