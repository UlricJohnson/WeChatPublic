package com.casaba.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * created by casaba-u on 2018/9/10
 */
@Controller
@RequestMapping("/redirect")
public class RedirectController {
    private static final Log LOGGER = LogFactory.getLog(RedirectController.class);

    /**
     * 重定向到调查问卷
     *
     * @author casaba-u
     * @date 2018/9/10
     */
    @RequestMapping("/investigation")
    public String toInvestigation() {
        LOGGER.info("=====跳转到调查问卷页面");

        String url = "https://www.wjx.cn/m/27872200.aspx"; // 物业公司对社区电梯使用情况的调查问卷

        return "redirect:" + url;
    }

}
