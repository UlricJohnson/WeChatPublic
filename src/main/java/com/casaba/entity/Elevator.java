package com.casaba.entity;

import java.util.Date;
import java.util.List;

/**
 * 电梯
 * created by Ulric on 2018/7/16
 */

public class Elevator {
    private Long id;    // 主键ID

    private Integer totDevice;// 使用单位设备总数

    private String unitOfUse;// 使用单位名称

    private String addressOfUse; // 使用单位地址

    private String contact; // 使用单位联系人

    private String contactNumber;   // 联系电话（座机或手机）

    private String certificateOfUse;// 使用证编号（唯一）

    private String nextYearlyInspection;  // 下次年检日期

    private String deviceModel; // 设备型号

    private String deviceNumber;    // 设备编号

    private String deviceFactoryNumber; // 设备出厂编号

    private String deviceRegistrationNumber;    // 设备注册号

    private String deviceAddress;   // 设备地址

    private String town;    // （设备）所在镇街

    private String departmentAddress;    // 使用单位部门地址

    private String deviceType;  // 设备类型

    private String deviceState;    // 设备状态

    private String manufacturingUnit;   // 制造单位

    private String installationUnit;    // 安装单位

    private String maintenanceUnit; // 维保单位

    private String deviceDetails;   // 设备详情

    private List<Complaint> complaintList;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getTotDevice() { return totDevice; }

    public void setTotDevice(Integer totDevice) { this.totDevice = totDevice; }

    public String getUnitOfUse() { return unitOfUse; }

    public void setUnitOfUse(String unitOfUse) { this.unitOfUse = unitOfUse == null ? null : unitOfUse.trim(); }

    public String getContact() { return contact; }

    public void setContact(String contact) { this.contact = contact == null ? null : contact.trim(); }

    public String getContactNumber() { return contactNumber; }

    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber == null ? null : contactNumber.trim(); }

    public String getCertificateOfUse() { return certificateOfUse; }

    public void setCertificateOfUse(String certificateOfUse) { this.certificateOfUse = certificateOfUse == null ? null : certificateOfUse.trim(); }

    public String getNextYearlyInspection() { return nextYearlyInspection; }

    public void setNextYearlyInspection(String nextYearlyInspection) { this.nextYearlyInspection = nextYearlyInspection; }

    public String getDeviceModel() { return deviceModel; }

    public void setDeviceModel(String deviceModel) { this.deviceModel = deviceModel == null ? null : deviceModel.trim(); }

    public String getDeviceNumber() { return deviceNumber; }

    public void setDeviceNumber(String deviceNumber) { this.deviceNumber = deviceNumber == null ? null : deviceNumber.trim(); }

    public String getDeviceFactoryNumber() { return deviceFactoryNumber; }

    public void setDeviceFactoryNumber(String deviceFactoryNumber) { this.deviceFactoryNumber = deviceFactoryNumber == null ? null : deviceFactoryNumber.trim(); }

    public String getDeviceRegistrationNumber() { return deviceRegistrationNumber; }

    public void setDeviceRegistrationNumber(String deviceRegistrationNumber) { this.deviceRegistrationNumber = deviceRegistrationNumber == null ? null : deviceRegistrationNumber.trim(); }

    public String getDeviceAddress() { return deviceAddress; }

    public void setDeviceAddress(String deviceAddress) { this.deviceAddress = deviceAddress == null ? null : deviceAddress.trim(); }

    public String getTown() { return town; }

    public void setTown(String town) { this.town = town == null ? null : town.trim(); }

    public String getAddressOfUse() { return addressOfUse; }

    public void setAddressOfUse(String addressOfUse) { this.addressOfUse = addressOfUse == null ? null : addressOfUse.trim(); }

    public String getDeviceType() { return deviceType; }

    public void setDeviceType(String deviceType) { this.deviceType = deviceType == null ? null : deviceType.trim(); }

    public String getDeviceState() { return deviceState; }

    public void setDeviceState(String deviceState) { this.deviceState = deviceState; }

    public String getManufacturingUnit() { return manufacturingUnit; }

    public void setManufacturingUnit(String manufacturingUnit) { this.manufacturingUnit = manufacturingUnit == null ? null : manufacturingUnit.trim(); }

    public String getInstallationUnit() { return installationUnit; }

    public void setInstallationUnit(String installationUnit) { this.installationUnit = installationUnit == null ? null : installationUnit.trim(); }

    public String getMaintenanceUnit() { return maintenanceUnit; }

    public void setMaintenanceUnit(String maintenanceUnit) { this.maintenanceUnit = maintenanceUnit == null ? null : maintenanceUnit.trim(); }

    public String getDeviceDetails() { return deviceDetails; }

    public void setDeviceDetails(String deviceDetails) { this.deviceDetails = deviceDetails == null ? null : deviceDetails.trim(); }

    public String getDepartmentAddress() { return departmentAddress; }

    public void setDepartmentAddress(String departmentAddress) { this.departmentAddress = departmentAddress; }

    public List<Complaint> getComplaintList() { return complaintList; }

    public void setComplaintList(List<Complaint> complaintList) { this.complaintList = complaintList; }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", totDevice=" + totDevice +
                ", unitOfUse='" + unitOfUse + '\'' +
                ", addressOfUse='" + addressOfUse + '\'' +
                ", contact='" + contact + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", certificateOfUse='" + certificateOfUse + '\'' +
                ", nextYearlyInspection='" + nextYearlyInspection + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceNumber='" + deviceNumber + '\'' +
                ", deviceFactoryNumber='" + deviceFactoryNumber + '\'' +
                ", deviceRegistrationNumber='" + deviceRegistrationNumber + '\'' +
                ", deviceAddress='" + deviceAddress + '\'' +
                ", town='" + town + '\'' +
                ", departmentAddress='" + departmentAddress + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceState='" + deviceState + '\'' +
                ", manufacturingUnit='" + manufacturingUnit + '\'' +
                ", installationUnit='" + installationUnit + '\'' +
                ", maintenanceUnit='" + maintenanceUnit + '\'' +
                ", deviceDetails='" + deviceDetails + '\'' +
                ", complaintList=" + complaintList +
                '}';
    }
}