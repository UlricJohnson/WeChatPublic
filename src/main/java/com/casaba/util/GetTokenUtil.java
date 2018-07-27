package com.casaba.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 获取access_token
 * <p>
 * created by Ulric on 2018/7/19
 */

public final class GetTokenUtil {

    public static final String APP_ID = "wx7fdb9ee9b03c7239";
    public static final String APP_SECRET = "698b110c15a07f274aa829c0a4b93874";
    //    public static final String GET_TOKEN_URL = "https://api.weixin.qq" + "
    // .com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

//    // 获取token
//    public static String getAccessToken(String apiUrl, String appId, String appSecret) {
//        String turl = String.format("%s?grant_type=client_credential&appid=%s&secret=%s", apiUrl, appId, appSecret);
//        HttpClient client = new DefaultHttpClient();
//        HttpGet get = new HttpGet(turl);
//        JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
////        ObjectMapper objectMapper = new ObjectMapper();
//        String result = null;
//        try {
//            HttpResponse res = client.execute(get);
//            String responseContent = null; // 响应内容
//            HttpEntity entity = res.getEntity();
//            responseContent = EntityUtils.toString(entity, "UTF-8");
//
//            // 将json字符串转换为json对象
//            JsonObject json = jsonparer.parse(responseContent).getAsJsonObject();
//
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                if (json.get("errcode") != null) {
//                    // 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
//                    return null;
//                } else {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
//                    result = json.get("access_token").getAsString();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接 ,释放资源
//            client.getConnectionManager().shutdown();
//            return result;
//        }
//    }

    public static void main(String[] args) {
//        String accessToken = GetTokenUtil.getAccessToken(GET_TOKEN_URL, APP_ID, APP_SECRET);
//        System.out.println(accessToken);
    }
}
