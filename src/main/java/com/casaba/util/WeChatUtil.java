package com.casaba.util;

import com.casaba.entity.AccessToken;
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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * created by Ulric on 2018/7/25
 */

public final class WeChatUtil {

    private static Log LOGGER = LogFactory.getLog(WeChatUtil.class);

    // 在微信公众平台后台 -> 服务器配置 中设置的开发者自定义的Token
//    public static final String TOKEN = "nhtx";

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

        for (String s : arr) { stringBuffer.append(s); }

        //4.sha1加密,网上均有现成代码
        String temp = getSha1(stringBuffer.toString());

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
//    public static String getAccessToken(String apiUrl, String appId, String appSecret) {
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
                    return accessTokenObj.getTokenString();
                }
            }
            /*** ============================== ***/

//        String result = "";
//        BufferedReader bufReader = null;
//        try {
//            String urlNameString = WeChatConst.GET_ACCESS_TOKEN_URL + "?" + WeChatConst.GET_ACCESS_TOKEN_PARAMS;
//            URL realUrl = new URL(urlNameString);
//
//            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
//
//            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//
//            // 建立实际的连接
//            connection.connect();
//
//            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//
//            LOGGER.info("=====返回的响应头：" + map);
//
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) { System.out.println(key + "--->" + map.get(key)); }
//
//            // 定义 BufferedReader输入流来读取URL的响应
//            bufReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            while ((line = bufReader.readLine()) != null) { result += line; }
//        } catch (Exception e) {
//            LOGGER.info("发送GET请求出现异常！" + e);
//            e.printStackTrace();
//        } finally {
//            try {
//                if (bufReader != null) { bufReader.close(); }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return result;

            /*** ============================== ***/

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

                // 将 access_token 缓存到 ServletContext 域
                expiryTime = System.currentTimeMillis() + Long.parseLong(json.get("expires_in").getAsString());
                servletContext.setAttribute("access_token_obj", new AccessToken(accessToken, expiryTime));

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 关闭连接 ,释放资源
                client.getConnectionManager().shutdown();
                return accessToken;
            }
        }
    }

    public static void main(String[] args) {
        // 测试获取 access_token
        String accessToken = getAccessToken();
        System.out.println("=====" + accessToken);
    }

    /**
     * 将字符串进行 sha1 加密
     *
     * @author Ulric
     * @date 2018/7/17
     */
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) { return null; }

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

}
