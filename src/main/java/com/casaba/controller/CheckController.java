package com.casaba.controller;

import com.casaba.util.WeChatUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 测试TOKEN
 * <p>
 * created by Ulric on 2018/7/17
 */

@RestController
@RequestMapping("/check")
public class CheckController {

    /**
     * @author Ulric
     * @date 2018/7/17
     */
    @RequestMapping("/checkSignature")
    public void checkSignature(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("=====CheckController--checkSignature()");

        // 微信服务器进行验证的时候会发过来的4个参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter writer = response.getWriter();
        if (WeChatUtil.isReqFromWc(signature, timestamp, nonce)) {
            writer.print(echostr);
        }
    }

}
