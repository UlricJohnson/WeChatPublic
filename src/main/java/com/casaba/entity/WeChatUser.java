package com.casaba.entity;

/**
 * 微信用户
 * created by Ulric on 2018/7/16
 */

public class WeChatUser {
    private Long id;    // 主键ID

    private String openId;// 微信用户的OpenID

    private Integer sex;//性别

    private User user; // 用户

    /*=========================*/

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getOpenId() { return openId; }

    public void setOpenId(String openId) { this.openId = openId; }

    public Integer getSex() { return sex; }

    public void setSex(Integer sex) { this.sex = sex; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "WeChatUser{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", sex=" + sex +
                ", user=" + user +
                '}';
    }
}