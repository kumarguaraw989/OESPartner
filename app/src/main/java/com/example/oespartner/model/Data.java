package com.example.oespartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Data {
    @SerializedName("admin_id")
    @Expose
    private String adminId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("client")
    @Expose
    private String client;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("firm_name")
    @Expose
    private String firmName;
    @SerializedName("firm_email")
    @Expose
    private Object firmEmail;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("partner_pic")
    @Expose
    private String partnerPic;
    @SerializedName("partner_id")
    @Expose
    private String partnerId;
    @SerializedName("vendor_code")
    @Expose
    private String vendorCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("response_code")
    @Expose
    private String responseCode;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public Object getFirmEmail() {
        return firmEmail;
    }

    public void setFirmEmail(Object firmEmail) {
        this.firmEmail = firmEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPartnerPic() {
        return partnerPic;
    }

    public void setPartnerPic(String partnerPic) {
        this.partnerPic = partnerPic;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

}


