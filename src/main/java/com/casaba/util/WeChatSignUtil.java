package com.casaba.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * 微信签名工具类，用于生成配置 JS-SDK 所需要的参数：
 * <p>
 * timestamp、nonceStr、signature（appId 在 WeChatConst 类中）
 * <p>
 * created by Ulric on 2018/7/27
 */

public final class WeChatSignUtil {

    private static final Log LOGGER = LogFactory.getLog(WeChatSignUtil.class);

    /**
     * 生成时间戳timestamp
     *
     * @author Ulric
     * @date 2018/7/27
     */
    public static String genTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 生成随机字符串 nonceStr
     *
     * @author Ulric
     * @date 2018/7/27
     */
    public static String genNonceStr() {
        Date today = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currTime = simpleDateFormat.format(today);

        // 获取时分秒，字符串
        String timeStr = currTime.substring(8, currTime.length());
        String randomStr = CommonUtil.genRandom(4) + "";

        return timeStr + randomStr;
    }

    /**
     * 生成签名signature
     *
     * @author Ulric
     * @date 2018/7/27
     */
    public static String genSignature(SortedMap<Object, Object> paramMap) {
//    public static String genSignature(String jsapiTicket, String timestamp, String noncestr, String url) {
        LOGGER.info("=====参数：" + paramMap);
//        LOGGER.info("=====参数：\n\t#jsapi_ticket：" + jsapiTicket +
//                "\n\t#timestamp：" + timestamp +
//                "\n\t#noncestr：" + noncestr +
//                "\n\t#url：" + url);

        // 将参数形成数组
//        String[] params = {jsapiTicket, timestamp, noncestr, url};

        /***===== 步骤1：将4个参数按照"字段名"，按照字典排序（TreeMap默认按照键升序排序），
         * 并使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串 =====***/
        StringBuffer strBuf = new StringBuffer();
        Set es = paramMap.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (value != null && !value.equals("")) {
                strBuf.append(key + "=" + value + "&");
            }
        }
        String splicedString = strBuf.toString().substring(0, strBuf.toString().length() - 1);

        /*** ===== 步骤2：对拼接好的字符串进行 SHA1 加密 ===== ***/
        String signature = CommonUtil.encryptInSha1(splicedString);

        return signature;
    }
}
