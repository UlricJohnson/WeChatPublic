//package com.casaba.controller;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * created by Ulric on 2018/7/23
// */
//
//@Controller
//@RequestMapping("/toHtml")
//public class ToHtmlController {
//
//    private static final Log LOGGER = LogFactory.getLog(ToHtmlController.class);
//
//    /**
//     * 跳到 HTML 页面
//     *
//     * @author Ulric
//     * @date 2018/7/22
//     */
//    @RequestMapping("/{htmlName}")
//    public String toHtml(@PathVariable String htmlName) {
//        LOGGER.info("=====准备跳转页面：" + htmlName);
//        return htmlName;
//    }
//
//}
