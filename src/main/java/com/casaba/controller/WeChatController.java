package com.casaba.controller;

import com.casaba.entity.Elevator;
import com.casaba.entity.JsapiTicket;
import com.casaba.entity.User;
import com.casaba.entity.WeChatUser;
import com.casaba.service.IUserService;
import com.casaba.service.IWeChatService;
import com.casaba.util.CommonUtil;
import com.casaba.util.WeChatConst;
import com.casaba.util.WeChatSignUtil;
import com.casaba.util.WeChatUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.*;

/**
 * created by Ulric on 2018/7/25
 */

@Controller
@RequestMapping("/wechat")
public class WeChatController {

    private static final Log LOGGER = LogFactory.getLog(WeChatController.class);

    @Resource
    private IUserService iUserService;

    @Resource
    private IWeChatService iWeChatService;

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
        LOGGER.info("=====接收到的参数：url=" + url);

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

        LOGGER.info("=====返回的数据：" + resultMap);

        return resultMap;
    }

    /**
     * 进行微信授权登录
     *
     * @author casaba-u
     * @date 2018/8/17
     */
    @RequestMapping("/wclogin")
    public void wcLogin(HttpServletRequest request,
                        HttpServletResponse response
            /*@ModelAttribute("toJsp") String toJsp,*/ /* 授权完成之后要跳到的页面 */
            /*@ModelAttribute("paramMap") Map paramMap*/) {
        // 回调地址
        String redirectUri = WeChatConst.DOMAIN + "/wechat/wcCallback";

        // 授权完毕后跳转的页面
//        String page = mv.getViewName();

//        Map<String, Object> rediAttrsMap = (HashMap<String, Object>) rediAttr.getFlashAttributes();
//        StringBuffer stateParamBuf = new StringBuffer("");
//        if (!rediAttrsMap.isEmpty()) { // 有携带参数
//            Set<Map.Entry<String, Object>> entrySet = rediAttrsMap.entrySet();
//            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, Object> entry = iterator.next();
//
//            }
//        }

        try {
            LOGGER.info("=====进行微信登录网页授权");

            // 1、先进行 snsapi_base 默认授权，获取微信用户的openid，查询是否已经绑定电梯用户，如果已经绑定则直接登录

            // 2、微信用户未绑定电梯用户，拉取微信用户信息并弹出授权页面进行绑定

//            WeChatUtil.oAuth(redirectUri, false, null, response); // response 对象用于重定向请求

            /**
             * 由于授权操作安全等级较高，所以在发起授权请求时，微信会对授权链接做正则强匹配校验，
             * 如果链接的参数顺序不对，授权页面将无法正常访问
             */
            String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                    "appid=" + WeChatConst.APP_ID +
                    "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") +
                    "&response_type=code" +
                    "&scope=snsapi_userinfo" +
//                    stateParamBuf.toString() + // state 参数是为了防止数据被拦截？可以不用
                    "#wechat_redirect";

            LOGGER.info("=====完整的请求URL：" + requestUrl);

//            rediAttr.addAttribute("toJsp", toJsp);
//            rediAttr.addAttribute("paramMap", paramMap);

//            HttpSession session = request.getSession();
//            session.setAttribute("toJsp", toJsp);
//            session.setAttribute("paramMap", paramMap);
//            session.setMaxInactiveInterval(600); // 配置会话有效时长为10分钟

            // 用重定向的方式进行请求
            response.sendRedirect(requestUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 微信网页授权回调：通过微信传回的 code 获取网页授权的 access_token
     *
     * @author casaba-u
     * @date 2018/8/17
     */
    @RequestMapping("/wcCallback")
    public ModelAndView wcCallback(HttpServletRequest request
//                                   @ModelAttribute("toJsp") String toJsp, /* 授权完成之后要跳到的页面 */
//                                   @ModelAttribute("paramMap") Map paramMap,/* 携带到那个页面的参数 */
            /*RedirectAttributes rediAttr*/) throws IOException {
//        LOGGER.info("=====");

        ModelAndView mv = new ModelAndView();

        // 获取微信传回的 code
        String code = request.getParameter("code");

        // 获取 授权完成之后要跳到的页面 toJsp，以及携带到那个页面的参数
        HttpSession session = request.getSession();
        String toJsp = (String) session.getAttribute("toJsp");
        Map paramMap = (Map) session.getAttribute("paramMap");

        /*
         * 1、获取code后，请求以下链接获取access_token：
         * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
         */
        String urlReqAccessToken = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + WeChatConst.APP_ID +
                "&secret=" + WeChatConst.APP_SECRET +
                "&code=" + code +
                "&grant_type=authorization_code";

        // 进行get请求，然后获取参数
        JsonObject respJsonObj = CommonUtil.doGetJson(urlReqAccessToken);

        /*
         * 返回的参数有：
         * access_token	    网页授权接口调用凭证，有效期7200秒，注意：此access_token与基础支持的access_token不同
         * expires_in	    access_token接口调用凭证超时时间，单位（秒）
         * refresh_token	用户刷新access_token，有效期30天
         * openid	        用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
         * scope	        用户授权的作用域，使用逗号（,）分隔
         */
        // 获取微信用户的open id
        String openId1 = respJsonObj.get("openid").getAsString();

        // 获取网页授权的 access_token，不同于公众号全局的access_token
        String authAccessToken = respJsonObj.get("access_token").getAsString();

        /*
         * 2、根据openid和网页授权的access_token获取微信用户信息
         */
        String urlGetWcInfo = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + authAccessToken +
                "&openid=" + openId1 +
                "&lang=zh_CN";
        JsonObject wcInfoJson = CommonUtil.doGetJson(urlGetWcInfo);

        /**
         * 返回 JSON 如下：
         * {
         * "openid":" OPENID",
         * "nickname": NICKNAME,
         * "sex":"1",
         * "province":"PROVINCE"
         * "city":"CITY",
         * "country":"COUNTRY",
         * "headimgurl": "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
         * "privilege":[ "PRIVILEGE1" "PRIVILEGE2" ],
         * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
         * }
         */
        LOGGER.info("=====微信用户信息：\n\t" + wcInfoJson);

        String openId2 = wcInfoJson.get("openid").getAsString();

        /*
         * 根据openid查询电梯用户，如果可以查到则说明绑定了微信用户，
         * 然后核对数据库中的微信用户的openid和返回JSON中的openid是否一致，如果一致则直接使用电梯用户登录
         */
        User eleUser = iUserService.findByWcOpenId(openId2);

        // ==============1、根据openid可以查询到电梯用户，说明已经绑定微信用户
        if (eleUser != null) {
            LOGGER.info("=====已经绑定微信用户");

            // 使用电梯用户登录
            mv.setViewName(toJsp);
//            mv.addObject("eleUser", eleUser);
//            mv.addObject("paramMap", paramMap);
            paramMap.put("eleUser", eleUser);

            session.setAttribute("paramMap", paramMap);
//            if (toJsp.equals("complaint")) { // 如果要跳转到投诉页面 complaint.jsp，则携带的参数为 elevator
//                Elevator elevator = (Elevator) paramMap.get("elevator");
//                mv.addObject("elevator", elevator);
//            }
            return mv;
        }

        // ==========2、未绑定微信用户
        LOGGER.info("=====未绑定微信用户");

        WeChatUser wcUser = new WeChatUser();
        wcUser.setOpenId(wcInfoJson.get("openid").getAsString());
        wcUser.setNickname(wcInfoJson.get("nickname").getAsString());
        wcUser.setSex(wcInfoJson.get("sex").getAsInt());
//        wcInfoJson.get("language").getAsString();
        wcUser.setProvince(wcInfoJson.get("province").getAsString());
        wcUser.setCity(wcInfoJson.get("city").getAsString());
        wcUser.setCountry(wcInfoJson.get("country").getAsString());
        wcUser.setHeadImgUrl(wcInfoJson.get("headimgurl").getAsString());

        JsonArray privileges = wcInfoJson.get("privilege").getAsJsonArray();
        StringBuffer strBuf = new StringBuffer("");
        for (int i = 0; i < privileges.size(); i++) {
            strBuf.append(privileges.get(i).getAsString() + ";");
        }

        // unionid 要绑定开放平台才会有
        if (wcInfoJson.has("unionid")) {
            wcUser.setUnionId(wcInfoJson.get("unionid").getAsString());
        }

        try {
            // 如果微信用户在数据库中不存在，则把ta添加到数据库
            if (!iWeChatService.isExist(wcUser.getOpenId())) {
                iWeChatService.addWcUser(wcUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 跳到登录页面，使用账号和手机号登录，然后进行电梯用户绑定微信用户的操作
        mv.setViewName("login");
        paramMap.put("wcUser", wcUser);

//        mv.addObject("toJsp", toJsp);
//        mv.addObject("wcUser", wcUser);
//        mv.addObject("paramMap", paramMap);

        session.setAttribute("toJsp", toJsp);
        session.setAttribute("paramMap", paramMap);

        return mv;
    }


}
