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

    // 域名，要跳转页面的话在后面加上 /toJsp/xxx
    public static final String DOMAIN = "http://www.fsnhtx.net";// 阿里云服务器
//    public static final String DOMAIN = "http://nhtx.natapp1.cc"; // 本地测试

    /*** ============access_token================== ***/

    // 获取 access_token 的 url（没有拼接参数）
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    // 获取 access_token 参数串
    public static final String GET_ACCESS_TOKEN_PARAMS = "grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;

    // 获取 access_token 的完整url（拼接参数）
    public static final String GET_ACCESS_TOKEN_URL_FULL = GET_ACCESS_TOKEN_URL + "?" + GET_ACCESS_TOKEN_PARAMS;

    /*** ==============jsapi_ticket================= ***/

    // 获取 jsapi_ticket 的 url
    public static final String GET_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    // 获取 jsapi_ticket 的参数串（使用的时候在最后拼接上 access_token 就行了）
    public static final String GET_JASPI_TICKET_PARAMS_MISSING = "type=jsapi&access_token=";
}
