package com.casaba.controller;

import com.casaba.util.WeChatUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
    public void checkSignature(HttpServletResponse response, String signature, String timestamp, String nonce, String echostr) {
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
        }finally {
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
//    @RequestMapping("/configJsSdk")
//    public Map configJsSdk( String url) {
//        LOGGER.info("=====接收到的参数：url=" + url);
//
//        Map resultMap = new HashMap();
//
//        return resultMap;
//    }

}
