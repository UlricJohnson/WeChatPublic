package com.casaba.entity;

/**
 * 保存 jaspi_ticket 及其过期时间（时间戳）
 *
 * created by Ulric on 2018/7/27
 */

public class JsapiTicket {
    String ticket;  // jsapi_ticket
    long expiryTime;// 过期时间（单位为毫秒）

    public JsapiTicket() {}

    public JsapiTicket(String ticket, long expiryTime) {
        this.ticket = ticket;
        this.expiryTime = expiryTime;
    }

    public String getTicket() { return ticket; }

    public void setTicket(String ticket) { this.ticket = ticket; }

    public long getExpiryTime() { return expiryTime; }

    public void setExpiryTime(long expiryTime) { this.expiryTime = expiryTime; }

    @Override
    public String toString() {
        return "JsapiTicket{" +
                "ticket='" + ticket + '\'' +
                ", expiryTime=" + expiryTime +
                '}';
    }
}
