package com.casaba.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.MessageDigest;

/**
 * created by Ulric on 2018/7/27
 */

public final class CommonUtil {
    private static final Log LOGGER = LogFactory.getLog(CommonUtil.class);

    /**
     * 将字符串进行 sha1 加密
     *
     * @author Ulric
     * @date 2018/7/17
     */
    public static String encryptInSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成指定长度的随机正整数
     *
     * @param length 生成的随机数的长度
     * @author Ulric
     * @date 2018/7/27
     */
    public static int genRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 传入一个完整的请求URL，返回JSON数据
     *
     * @author casaba-u
     * @date 2018/8/16
     */
    public static JsonObject doGetJson(String url) throws IOException {
        LOGGER.info("=====请求URL：" + url);

        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);

        JsonParser jsonParser = new JsonParser();

        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        // 将返回数据转成JSON字符串
        String responseStr = EntityUtils.toString(entity, "UTF-8");

        // 将JSON字符串转成JSON对象
        JsonObject respJsonObj = jsonParser.parse(responseStr).getAsJsonObject();

        // 释放链接
        get.releaseConnection();

        return respJsonObj;
    }

    /**
     * 判断一个字符串是否为空或空串，若为空或空串则返回true
     *
     * @author casaba-u
     * @date 2018/8/24
     */
    public static boolean isStringBlank(String string) {
        if (string == null) {
            return true;
        }

        if (string.trim().equals("")) {
            return true;
        }

        return false;
    }
}
