package com.casaba.controller;

import com.casaba.entity.JsapiTicket;
import com.casaba.util.WeChatConst;
import com.casaba.util.WeChatSignUtil;
import com.casaba.util.WeChatUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * created by Ulric on 2018/7/25
 */

@Controller
@RequestMapping("/wechat")
public class WeChatController {

    private static final Log LOGGER = LogFactory.getLog(WeChatController.class);

    /**
     * @description
     * @author Ulric
     * @date 2018/7/25
     */
    @RequestMapping("/checkSignature")
//    public String checkSignature(String signature, String timestamp, String nonce, String echostr) {
    public void checkSignature(HttpServletResponse response, String signature, String timestamp, String nonce, String
            echostr) {
//        LOGGER.info("=====接收到的参数：\n\t#signature：" + signature +
//                "\n\t#timestamp：" + timestamp +
//                "\n\t#nonce：" + nonce +
//                "\n\t#echostr：" + echostr);

        // 验证请求是否来自微信服务器
        boolean isReqFromWc = WeChatUtil.isReqFromWc(signature, timestamp, nonce);

        LOGGER.info("=====请求是否来自微信服务器：" + isReqFromWc);

        PrintWriter printWriter = null;

        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                if (isReqFromWc) {
                    LOGGER.info("=====返回echostr");
//            return echostr;
                    printWriter.write(echostr);
                } else {
                    printWriter.write("");
                }
            }
        }
//        return null;
    }


    /**
     * 配置微信的 JS-SDK
     *
     * @author Ulric
     * @date 2018/7/25
     */
    @RequestMapping("/configJsSdk")
    @ResponseBody
    public Map configJsSdk(String url) {
//        LOGGER.info("=====接收到的参数：url=" + url);

        Map resultMap = new HashMap();
//        ModelAndView mv = new ModelAndView();

        /** === 1、获取 jsapi_ticket === **/
        // 获取当前项目的web应用域对象
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        JsapiTicket jsapiTicketObj = (JsapiTicket) servletContext.getAttribute("jsapi_ticket_obj");

        String jsapiTicket = null;  // ticket 字符串
        long expiryTime = -1;       // jsapi_ticket 的过期时间
        if (jsapiTicketObj != null) {
            if (jsapiTicketObj.getExpiryTime() > System.currentTimeMillis()) { // jsapi_ticket 还没过期
                LOGGER.info("=====获取缓存的jsapi_ticket，过期时间：" + jsapiTicketObj.getExpiryTime() +
                        "##当前时间戳：" + System.currentTimeMillis());
                jsapiTicket = jsapiTicketObj.getTicket();
            }
        }

        // 缓存中没有 jsapi_ticket，或者已经过期，则向微信服务器获取
        if (jsapiTicketObj == null || jsapiTicketObj.getExpiryTime() < System.currentTimeMillis()) {
            // getAccessToken() 中会检查 access_token 是否已经缓存，并且 getJsapiTicket() 中会将新获取的 jsapi_ticket进行缓存
            jsapiTicket = WeChatUtil.getJsapiTicket(WeChatUtil.getAccessToken());
        }

        /** === 2、生成签名signature === **/
        SortedMap<Object, Object> genSignParamMap = new TreeMap<>(); // 存放生成 signature 需要的参数
        String noncestr = WeChatSignUtil.genNonceStr();
        String timestamp = WeChatSignUtil.genTimestamp();
        genSignParamMap.put("noncestr", noncestr);
        genSignParamMap.put("jsapi_ticket", jsapiTicket);
        genSignParamMap.put("timestamp", timestamp);
        // 判断页面URL是否含有#，如果有则截断不要，
        if (url.contains("#")) {
            String temp = url.substring(0, url.indexOf("#"));
            url = temp;
        }
        genSignParamMap.put("url", url); // 需要配置JS-SDK的页面URL，不包含#及后面的内容
        // 调用方法生成 signature
//        String signature = WeChatSignUtil.genSignature(jsapiTicket, timestamp, noncestr, url);
        String signature = WeChatSignUtil.genSignature(genSignParamMap);

//        return mv;

        // wx.config() 需要的参数有：appId、timestamp、noncestr、signature
        resultMap.put("appId", WeChatConst.APP_ID);
        resultMap.put("timestamp", timestamp);
        resultMap.put("noncestr", noncestr);
        resultMap.put("signature", signature);

        return resultMap;
    }

}
