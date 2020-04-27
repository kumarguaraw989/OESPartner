package com.example.oespartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BranchModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("branch_validity")
    @Expose
    private String branchValidity;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("client")
    @Expose
    private Object client;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("pan")
    @Expose
    private String pan;
    @SerializedName("gst")
    @Expose
    private String gst;
    @SerializedName("esi")
    @Expose
    private String esi;
    @SerializedName("pf")
    @Expose
    private String pf;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("no_of_branch")
    @Expose
    private String noOfBranch;
    @SerializedName("module")
    @Expose
    private Object module;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("session_email")
    @Expose
    private Object sessionEmail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchValidity() {
        return branchValidity;
    }

    public void setBranchValidity(String branchValidity) {
        this.branchValidity = branchValidity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Object getClient() {
        return client;
    }

    public void setClient(Object client) {
        this.client = client;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getEsi() {
        return esi;
    }

    public void setEsi(String esi) {
        this.esi = esi;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNoOfBranch() {
        return noOfBranch;
    }

    public void setNoOfBranch(String noOfBranch) {
        this.noOfBranch = noOfBranch;
    }

    public Object getModule() {
        return module;
    }

    public void setModule(Object module) {
        this.module = module;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getSessionEmail() {
        return sessionEmail;
    }

    public void setSessionEmail(Object sessionEmail) {
        this.sessionEmail = sessionEmail;
    }

    @Override
    public String toString() {
        return "BranchModel{" +
                "id='" + id + '\'' +
                ", branch='" + branch + '\'' +
                ", branchId='" + branchId + '\'' +
                ", branchValidity='" + branchValidity + '\'' +
                ", company='" + company + '\'' +
                ", clientId='" + clientId + '\'' +
                ", client=" + client +
                ", address='" + address + '\'' +
                ", landmark='" + landmark + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", website='" + website + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", pan='" + pan + '\'' +
                ", gst='" + gst + '\'' +
                ", esi='" + esi + '\'' +
                ", pf='" + pf + '\'' +
                ", license='" + license + '\'' +
                ", user='" + user + '\'' +
                ", noOfBranch='" + noOfBranch + '\'' +
                ", module=" + module +
                ", status='" + status + '\'' +
                ", sessionEmail=" + sessionEmail +
                '}';
    }

}
