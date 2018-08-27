package com.casaba.entity;

/**
 * created by casaba-u on 2018/8/27
 */
public class Data {
    private Long id; // 主键ID
    private String eventCode;
    private String eventData;
    private String eventStatus;
    private String eventTime;
    private String msgSeq;
    private String regCode;
    private String tdSerial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getMsgSeq() {
        return msgSeq;
    }

    public void setMsgSeq(String msgSeq) {
        this.msgSeq = msgSeq;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getTdSerial() {
        return tdSerial;
    }

    public void setTdSerial(String tdSerial) {
        this.tdSerial = tdSerial;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", eventCode='" + eventCode + '\'' +
                ", eventData='" + eventData + '\'' +
                ", eventStatus='" + eventStatus + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", msgSeq='" + msgSeq + '\'' +
                ", regCode='" + regCode + '\'' +
                ", tdSerial='" + tdSerial + '\'' +
                '}';
    }
}
