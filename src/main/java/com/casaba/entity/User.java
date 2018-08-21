package com.casaba.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户（投诉人）
 * created by Ulric on 2018/7/16
 */
public class User implements Serializable {
    private Long id;    // 主键ID

    private String username;// 用户名

    private String contactNum;  // 联系电话

    private String wcOpenId; // 微信用户的 open id，用来指向tb_wechat_user 中的 OPEN_ID

    private WeChatUser weChatUser; // 微信用户

    private List<Complaint> complaintList;

    /*=======================*/

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username == null ? null : username.trim(); }

    public String getContactNum() { return contactNum; }

    public void setContactNum(String contactNum) { this.contactNum = contactNum == null ? null : contactNum.trim(); }

    public String getWcOpenId() { return wcOpenId; }

    public void setWcOpenId(String wcOpenId) { this.wcOpenId = wcOpenId; }

    public WeChatUser getWeChatUser() { return weChatUser; }

    public void setWeChatUser(WeChatUser weChatUser) { this.weChatUser = weChatUser; }

    public List<Complaint> getComplaintList() { return complaintList; }

    public void setComplaintList(List<Complaint> complaintList) { this.complaintList = complaintList; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", contactNum='" + contactNum + '\'' +
                ", wcOpenId='" + wcOpenId + '\'' +
                ", weChatUser=" + weChatUser +
                ", complaintList=" + complaintList +
                '}';
    }
}