package com.example.oespartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("client_validity")
    @Expose
    private String clientValidity;
    @SerializedName("client")
    @Expose
    private Object client;
    @SerializedName("no_of_branch")
    @Expose
    private String noOfBranch;
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
    private String phone;
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
    @SerializedName("branch")
    @Expose
    private Object branch;
    @SerializedName("module")
    @Expose
    private Object module;
    @SerializedName("logo")
    @Expose
    private String logo;
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClientValidity() {
        return clientValidity;
    }

    public void setClientValidity(String clientValidity) {
        this.clientValidity = clientValidity;
    }

    public Object getClient() {
        return client;
    }

    public void setClient(Object client) {
        this.client = client;
    }

    public String getNoOfBranch() {
        return noOfBranch;
    }

    public void setNoOfBranch(String noOfBranch) {
        this.noOfBranch = noOfBranch;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public Object getBranch() {
        return branch;
    }

    public void setBranch(Object branch) {
        this.branch = branch;
    }

    public Object getModule() {
        return module;
    }

    public void setModule(Object module) {
        this.module = module;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
        return "ClientModel{" +
                "id='" + id + '\'' +
                ", clientId='" + clientId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", clientValidity='" + clientValidity + '\'' +
                ", client=" + client +
                ", noOfBranch='" + noOfBranch + '\'' +
                ", address='" + address + '\'' +
                ", landmark='" + landmark + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", website='" + website + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", pan='" + pan + '\'' +
                ", gst='" + gst + '\'' +
                ", esi='" + esi + '\'' +
                ", pf='" + pf + '\'' +
                ", license='" + license + '\'' +
                ", user='" + user + '\'' +
                ", branch=" + branch +
                ", module=" + module +
                ", logo='" + logo + '\'' +
                ", status='" + status + '\'' +
                ", sessionEmail=" + sessionEmail +
                '}';
    }
}
