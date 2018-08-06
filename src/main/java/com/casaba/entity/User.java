package com.casaba.entity;

import java.util.List;

/**
 * 用户（投诉人）
 * created by Ulric on 2018/7/16
 */
public class User {
    private Long id;    // 主键ID

    private String username;// 用户名

    private String contactNum;  // 联系电话

    private List<Complaint> complaintList;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username == null ? null : username.trim(); }

    public String getContactNum() { return contactNum; }

    public void setContactNum(String contactNum) { this.contactNum = contactNum == null ? null : contactNum.trim(); }

    public List<Complaint> getComplaintList() { return complaintList; }

    public void setComplaintList(List<Complaint> complaintList) { this.complaintList = complaintList; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", contactNum='" + contactNum + '\'' +
                ", complaintList=" + complaintList +
                '}';
    }
}