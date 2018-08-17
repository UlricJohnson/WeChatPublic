package com.casaba.util;

import com.casaba.entity.AccessToken;
import com.casaba.entity.JsapiTicket;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * created by Ulric on 2018/7/25
 */

public final class WeChatUtil {

    private static Log LOGGER = LogFactory.getLog(WeChatUtil.class);

    /**
     * 检查微信服务器传过来的 signature 参数，从而验证请求是否来自微信服务器
     *
     * @author Ulric
     * @date 2018/7/17
     */
    public static boolean isReqFromWc(String signature, String timestamp, String nonce) {
        //1.定义数组存放tooken，timestamp,nonce
        String[] arr = {WeChatConst.TOKEN, timestamp, nonce};

        //2.对数组进行排序
        Arrays.sort(arr);

        //3.生成字符串
        StringBuffer stringBuffer = new StringBuffer();

        for (String s : arr) {
            stringBuffer.append(s);
        }

        //4.sha1加密,网上均有现成代码
        String temp = CommonUtil.encryptInSha1(stringBuffer.toString());

        LOGGER.info("=====token、timestamp、nonce加密后的字符串：" + temp);

        //5.将加密后的字符串，与微信传来的加密签名比较，返回结果
        return temp.equals(signature);
    }

    /**
     * 获取 access_token
     *
     * @author Ulric
     * @date 2018/7/26
     */
    public static String getAccessToken() {
        Object lock = new Object();
        synchronized (lock) {
            // 获取当前项目的web应用域对象
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            ServletContext servletContext = webApplicationContext.getServletContext();
            AccessToken accessTokenObj = (AccessToken) servletContext.getAttribute("access_token_obj");
            String accessToken = null;  // token 字符串
            long expiryTime = -1;       // access_token 的过期时间
            if (accessTokenObj != null) {
                if (accessTokenObj.getExpiryTime() > System.currentTimeMillis()) { // access_token 还没过期
                    LOGGER.info("=====获取缓存的access_token：" + accessTokenObj.getTokenString() +
                            "##过期时间：" + accessTokenObj.getExpiryTime());
                    return accessTokenObj.getTokenString();
                }
            }

            /*** ==============没有缓存，或缓存已过期，需要重新获取================ ***/

            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(WeChatConst.GET_ACCESS_TOKEN_URL_FULL);
            JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
            try {
                HttpResponse res = client.execute(get);
                String responseContent = null; // 响应内容
                HttpEntity entity = res.getEntity();
                responseContent = EntityUtils.toString(entity, "UTF-8");

                // 将json字符串转换为json对象
                JsonObject json = jsonparer.parse(responseContent).getAsJsonObject();

                if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    if (json.get("errcode") != null) {
                        // 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                        return null;
                    } else { // 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                        accessToken = json.get("access_token").getAsString();
                    }
                }

                // 将 access_token 缓存到 ServletContext 域，expires_in 的单位为秒，所以需要转换成毫秒
                expiryTime = System.currentTimeMillis() + Long.parseLong(json.get("expires_in").getAsString()) * 1000;
                servletContext.setAttribute("access_token_obj", new AccessToken(accessToken, expiryTime));

                LOGGER.info("=====新获取的access_token：" + accessToken + "##过期时间：" + expiryTime);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 关闭连接 ,释放资源
                client.getConnectionManager().shutdown();
                return accessToken;
            }
        }
    }

    /**
     * 获取 jsapi_ticket，当 ServletContext 域中缓存已过期才调用这个方法
     *
     * @author Ulric
     * @date 2018/7/27
     */
    public static String getJsapiTicket(String accessToken) {
        Object lock = new Object();
        synchronized (lock) {
//            // 获取当前项目的web应用域对象
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            ServletContext servletContext = webApplicationContext.getServletContext();

//            JsapiTicket jsapiTicketObj = (JsapiTicket) servletContext.getAttribute("jsapi_ticket_obj");
//
            String jsapiTicket = null;  // ticket 字符串
            long expiryTime = -1;       // jsapi_ticket 的过期时间
//            if (jsapiTicketObj != null) {
//                if (jsapiTicketObj.getExpiryTime() > System.currentTimeMillis()) { // jsapi_ticket 还没过期
//                    return jsapiTicketObj.getTicket();
//                }
//            }
            // ==================== ServletContext 域中没有 jsapi_ticket 数据，或已经过期，需要重新获取

            // 完整的请求 jsapi_ticket 的路径
            String requestUrlFull = WeChatConst.GET_JSAPI_TICKET_URL + "?" +
                    WeChatConst.GET_JASPI_TICKET_PARAMS_MISSING + accessToken;

            LOGGER.info("=====请求 jsapi_ticket 的完整URL：" + requestUrlFull);

            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(requestUrlFull);

            JsonParser jsonParser = new JsonParser();
            try {
                // 执行 get 请求，并接收返回的JSON数据
            /* 返回JSON数据示例：
            {
            "errcode":0,
            "errmsg":"ok",
            "ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA",
            "expires_in":7200
            }
             */
                HttpResponse response = client.execute(get);
                HttpEntity entity = response.getEntity();
                // 将返回数据转成JSON字符串
                String responseStr = EntityUtils.toString(entity, "UTF-8");
                // 将JSON字符串转成JSON对象
                JsonObject respJsonObj = jsonParser.parse(responseStr).getAsJsonObject();

                LOGGER.info("=====请求返回的数据：\n\t#responseStr:" + responseStr);

                // 查看请求返回状态码，如果请求成功（状态码为200）
                if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    jsapiTicket = respJsonObj.get("ticket").getAsString();
                    // 设置过期时间
                    expiryTime = System.currentTimeMillis() + Long.valueOf(respJsonObj.get("expires_in").getAsString()) * 1000;
                }

                LOGGER.info("=====新获取的jsapi_ticket：" + jsapiTicket + "##过期时间：" + expiryTime);

                // 将 jsapi_ticket 缓存到 ServletContext 域
                servletContext.setAttribute("jsapi_ticket_obj", new JsapiTicket(jsapiTicket, expiryTime));

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return jsapiTicket;
            }
        }
    }

    /**
     * 网页授权
     *
     * @param redirectUri 授权后重定向的回调链接地址， 要使用 urlEncode 对链接进行处理（MIME编码）
     * @param isBase      授权作用域：是否为静默授权： snsapi_base（不弹出授权页面），是则为true，否则（snsapi_userinfo）为false
     * @param state       跳转到回调页面携带的参数，要拼接成url参数形式（p1=v1&p2=v2）
     * @author casaba-u
     * @date 2018/8/16
     */
//    public static void oAuth(String redirectUri, boolean isBase, String state, HttpServletResponse response) throws IOException {
////        Map<String, Object> resultMap = new HashMap();
//
//        String scope = null;
//        if (isBase) {
//            scope = "snsapi_base";
//        } else {
//            scope = "snsapi_userinfo";
//        }
//
//        // 重定向页面需要携带参数
//        String statePram = "";
//        if (state != null && state != "") {
//            statePram = "&state=" + state;
//        }
//
//        /**
//         * 由于授权操作安全等级较高，所以在发起授权请求时，微信会对授权链接做正则强匹配校验，
//         * 如果链接的参数顺序不对，授权页面将无法正常访问
//         */
//        String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
//                "appid=" + WeChatConst.APP_ID +
//                "&redirect_uri=" + URLEncoder.encode(redirectUri) +
//                "&response_type=code" +
//                "&scope=" + scope +
//                statePram +
//                "#wechat_redirect";
//
//        LOGGER.info("=====完整的请求URL：" + requestUrl);
//
////        JsonObject respJsonObj = null;
////        try {
////            respJsonObj = CommonUtil.doGetJson(requestUrl);
////            if (respJsonObj != null) {
////                resultMap.put("success", true);
////                resultMap.put("json", respJsonObj);
////            }
////        } catch (IOException e) {
////            resultMap.put("success", false);
////            e.printStackTrace();
////        } finally {
////            return resultMap;
////        }
//        // 用重定向的方式进行请求
//        response.sendRedirect(requestUrl);
//    }
}
