package com.casaba.entity;

/**
 * 投诉单，关联关系：用户（投诉人，一方），电梯（一方）
 * created by Ulric on 2018/7/16
 */

public class Complaint {
    private Long id;    // 主键ID

    private String details; // 投诉详情

    private String sketch; // 投诉简述（保存枚举类的数值）

    private String imgUrl; // 图片链接，有多个则用分号;隔开

//    private Elevator elevator;

//    private User user;

    private Long elevatorId;// 电梯表ID

    private Long userId;    // 用户（投诉人）表ID

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDetails() { return details; }

    public void setDetails(String details) { this.details = details == null ? null : details.trim(); }

    public String getSketch() { return sketch; }

    public void setSketch(String sketch) { this.sketch = sketch; }

    public String getImgUrl() { return imgUrl; }

    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    //    public Elevator getElevator() { return elevator; }

//    public void setElevator(Elevator elevator) { this.elevator = elevator; }

//    public User getUser() { return user; }

//    public void setUser(User user) { this.user = user; }

    public Long getElevatorId() { return elevatorId; }

    public void setElevatorId(Long elevatorId) { this.elevatorId = elevatorId; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", details='" + details + '\'' +
                ", sketch='" + sketch + '\'' +
                ", elevatorId='" + elevatorId + '\'' +
                ", userId='" + userId + '\'' +
//                ", elevator=" + elevator +
//                ", user=" + user +
                '}';
    }
}