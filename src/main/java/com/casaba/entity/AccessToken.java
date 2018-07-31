package com.casaba.entity;

/**
 * 用于缓存微信的 access_token，及其过期时间（单位为毫秒），官方说有效期为2小时
 *
 * created by Ulric on 2018/7/26
 */

public class AccessToken {
    private String tokenString; // access_token 字符串
    private long expiryTime;    // 过期时间（单位为毫秒）

    public AccessToken() {}

    public AccessToken(String tokenString, long expiryTime) {
        this.tokenString = tokenString;
        this.expiryTime = expiryTime;
    }

    public String getTokenString() { return tokenString; }

    public void setTokenString(String tokenString) { this.tokenString = tokenString; }

    public long getExpiryTime() { return expiryTime; }

    public void setExpiryTime(long expiryTime) { this.expiryTime = expiryTime; }

    @Override
    public String toString() {
        return "AccessToken{" +
                "tokenString='" + tokenString + '\'' +
                ", expiryTime=" + expiryTime +
                '}';
    }
}
