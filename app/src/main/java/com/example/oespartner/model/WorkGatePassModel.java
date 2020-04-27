package com.example.oespartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkGatePassModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("client")
    @Expose
    private String client;
    @SerializedName("work_client")
    @Expose
    private Object workClient;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("person_name")
    @Expose
    private String personName;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("driving_license_no")
    @Expose
    private String drivingLicenseNo;
    @SerializedName("license_valid_upto")
    @Expose
    private String licenseValidUpto;
    @SerializedName("vehicle_no")
    @Expose
    private String vehicleNo;
    @SerializedName("helper_name")
    @Expose
    private String helperName;
    @SerializedName("eye_test_date")
    @Expose
    private String eyeTestDate;
    @SerializedName("report")
    @Expose
    private String report;
    @SerializedName("training_certificate_no")
    @Expose
    private String trainingCertificateNo;
    @SerializedName("training_valid_upto")
    @Expose
    private String trainingValidUpto;
    @SerializedName("ex_armed")
    @Expose
    private String exArmed;
    @SerializedName("security_reference_no")
    @Expose
    private String securityReferenceNo;
    @SerializedName("security_copy")
    @Expose
    private Object securityCopy;
    @SerializedName("work_reference_no")
    @Expose
    private String workReferenceNo;
    @SerializedName("work_description")
    @Expose
    private String workDescription;
    @SerializedName("work_valid_upto")
    @Expose
    private String workValidUpto;
    @SerializedName("p_reference_no")
    @Expose
    private Object pReferenceNo;
    @SerializedName("visa_validity")
    @Expose
    private String visaValidity;
    @SerializedName("p_valid_upto")
    @Expose
    private String pValidUpto;
    @SerializedName("p_driving_license_validity")
    @Expose
    private Object pDrivingLicenseValidity;
    @SerializedName("p_eye_test_date")
    @Expose
    private Object pEyeTestDate;
    @SerializedName("p_training_certificate_validity")
    @Expose
    private Object pTrainingCertificateValidity;
    @SerializedName("declaration")
    @Expose
    private String declaration;
    @SerializedName("j_declaration")
    @Expose
    private String jDeclaration;
    @SerializedName("h_declaration")
    @Expose
    private String hDeclaration;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("stakeholder_id")
    @Expose
    private String stakeholderId;
    @SerializedName("person_id")
    @Expose
    private String personId;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("police_verify")
    @Expose
    private String policeVerify;
    @SerializedName("firm_name")
    @Expose
    private String firmName;
    @SerializedName("work_gatepass_id")
    @Expose
    private Object workGatepassId;
    @SerializedName("approval")
    @Expose
    private Object approval;
    @SerializedName("gate_no")
    @Expose
    private Object gateNo;
    @SerializedName("entry_date_time")
    @Expose
    private Object entryDateTime;
    @SerializedName("exit_date_time")
    @Expose
    private Object exitDateTime;
    @SerializedName("entry_gate_no")
    @Expose
    private Object entryGateNo;
    @SerializedName("exit_gate_no")
    @Expose
    private Object exitGateNo;
    @SerializedName("duration")
    @Expose
    private Object duration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Object getWorkClient() {
        return workClient;
    }

    public void setWorkClient(Object workClient) {
        this.workClient = workClient;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDrivingLicenseNo() {
        return drivingLicenseNo;
    }

    public void setDrivingLicenseNo(String drivingLicenseNo) {
        this.drivingLicenseNo = drivingLicenseNo;
    }

    public String getLicenseValidUpto() {
        return licenseValidUpto;
    }

    public void setLicenseValidUpto(String licenseValidUpto) {
        this.licenseValidUpto = licenseValidUpto;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getHelperName() {
        return helperName;
    }

    public void setHelperName(String helperName) {
        this.helperName = helperName;
    }

    public String getEyeTestDate() {
        return eyeTestDate;
    }

    public void setEyeTestDate(String eyeTestDate) {
        this.eyeTestDate = eyeTestDate;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getTrainingCertificateNo() {
        return trainingCertificateNo;
    }

    public void setTrainingCertificateNo(String trainingCertificateNo) {
        this.trainingCertificateNo = trainingCertificateNo;
    }

    public String getTrainingValidUpto() {
        return trainingValidUpto;
    }

    public void setTrainingValidUpto(String trainingValidUpto) {
        this.trainingValidUpto = trainingValidUpto;
    }

    public String getExArmed() {
        return exArmed;
    }

    public void setExArmed(String exArmed) {
        this.exArmed = exArmed;
    }

    public String getSecurityReferenceNo() {
        return securityReferenceNo;
    }

    public void setSecurityReferenceNo(String securityReferenceNo) {
        this.securityReferenceNo = securityReferenceNo;
    }

    public Object getSecurityCopy() {
        return securityCopy;
    }

    public void setSecurityCopy(Object securityCopy) {
        this.securityCopy = securityCopy;
    }

    public String getWorkReferenceNo() {
        return workReferenceNo;
    }

    public void setWorkReferenceNo(String workReferenceNo) {
        this.workReferenceNo = workReferenceNo;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getWorkValidUpto() {
        return workValidUpto;
    }

    public void setWorkValidUpto(String workValidUpto) {
        this.workValidUpto = workValidUpto;
    }

    public Object getPReferenceNo() {
        return pReferenceNo;
    }

    public void setPReferenceNo(Object pReferenceNo) {
        this.pReferenceNo = pReferenceNo;
    }

    public String getVisaValidity() {
        return visaValidity;
    }

    public void setVisaValidity(String visaValidity) {
        this.visaValidity = visaValidity;
    }

    public String getPValidUpto() {
        return pValidUpto;
    }

    public void setPValidUpto(String pValidUpto) {
        this.pValidUpto = pValidUpto;
    }

    public Object getPDrivingLicenseValidity() {
        return pDrivingLicenseValidity;
    }

    public void setPDrivingLicenseValidity(Object pDrivingLicenseValidity) {
        this.pDrivingLicenseValidity = pDrivingLicenseValidity;
    }

    public Object getPEyeTestDate() {
        return pEyeTestDate;
    }

    public void setPEyeTestDate(Object pEyeTestDate) {
        this.pEyeTestDate = pEyeTestDate;
    }

    public Object getPTrainingCertificateValidity() {
        return pTrainingCertificateValidity;
    }

    public void setPTrainingCertificateValidity(Object pTrainingCertificateValidity) {
        this.pTrainingCertificateValidity = pTrainingCertificateValidity;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getJDeclaration() {
        return jDeclaration;
    }

    public void setJDeclaration(String jDeclaration) {
        this.jDeclaration = jDeclaration;
    }

    public String getHDeclaration() {
        return hDeclaration;
    }

    public void setHDeclaration(String hDeclaration) {
        this.hDeclaration = hDeclaration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStakeholderId() {
        return stakeholderId;
    }

    public void setStakeholderId(String stakeholderId) {
        this.stakeholderId = stakeholderId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoliceVerify() {
        return policeVerify;
    }

    public void setPoliceVerify(String policeVerify) {
        this.policeVerify = policeVerify;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public Object getWorkGatepassId() {
        return workGatepassId;
    }

    public void setWorkGatepassId(Object workGatepassId) {
        this.workGatepassId = workGatepassId;
    }

    public Object getApproval() {
        return approval;
    }

    public void setApproval(Object approval) {
        this.approval = approval;
    }

    public Object getGateNo() {
        return gateNo;
    }

    public void setGateNo(Object gateNo) {
        this.gateNo = gateNo;
    }

    public Object getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(Object entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    public Object getExitDateTime() {
        return exitDateTime;
    }

    public void setExitDateTime(Object exitDateTime) {
        this.exitDateTime = exitDateTime;
    }

    public Object getEntryGateNo() {
        return entryGateNo;
    }

    public void setEntryGateNo(Object entryGateNo) {
        this.entryGateNo = entryGateNo;
    }

    public Object getExitGateNo() {
        return exitGateNo;
    }

    public void setExitGateNo(Object exitGateNo) {
        this.exitGateNo = exitGateNo;
    }

    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }

}