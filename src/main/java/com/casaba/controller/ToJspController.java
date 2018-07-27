package com.casaba.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String toJsp(@PathVariable String jspName) {
        LOGGER.info("=====准备跳转页面：" + jspName);
        return jspName;
    }

}
