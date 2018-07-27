package com.casaba.util;

/**
 * created by Ulric on 2018/7/26
 */

public final class WeChatConst {
    // 在微信公众平台后台 -> 服务器配置 中设置的开发者自定义的Token
    public static final String TOKEN = "gdfsnhtx";

    // appid
    public static final String APP_ID = "wx7fdb9ee9b03c7239";

    // AppSecret
    public static final String APP_SECRET = "698b110c15a07f274aa829c0a4b93874";

    // 获取 access_token 的 url（没有拼接参数）
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    // 参数串
    public static final String GET_ACCESS_TOKEN_PARAMS = "grant_type=client_credential&appid=" + APP_ID + "&secret="
            + APP_SECRET;

    // 获取 access_token 的完整url（拼接参数）
    public static final String GET_ACCESS_TOKEN_URL_FULL = GET_ACCESS_TOKEN_URL + "?" + GET_ACCESS_TOKEN_PARAMS;

    // 缓存 access_token 的文件名（没有后缀名）
    public static final String ACCESS_TOKEN_FILE_NAME = "accessTokenCache";
}
