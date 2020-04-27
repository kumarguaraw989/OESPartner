package com.example.oespartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaterialGatePassModel {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("client")
    @Expose
    private String client;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("gate_pass_type")
    @Expose
    private String gatePassType;
    @SerializedName("partner_code")
    @Expose
    private String partnerCode;
    @SerializedName("partner_name")
    @Expose
    private String partnerName;
    @SerializedName("vehicle_no")
    @Expose
    private String vehicleNo;
    @SerializedName("vehicle_load")
    @Expose
    private String vehicleLoad;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("belong_to")
    @Expose
    private String belongTo;
    @SerializedName("returnable_nonreturnable")
    @Expose
    private String returnableNonreturnable;
    @SerializedName("work_order_reference")
    @Expose
    private String workOrderReference;
    @SerializedName("material_name")
    @Expose
    private Object materialName;
    @SerializedName("specification")
    @Expose
    private Object specification;
    @SerializedName("unit")
    @Expose
    private Object unit;
    @SerializedName("qty")
    @Expose
    private Object qty;
    @SerializedName("qty_received")
    @Expose
    private Object qtyReceived;
    @SerializedName("material_type")
    @Expose
    private Object materialType;
    @SerializedName("store_no")
    @Expose
    private Object storeNo;
    @SerializedName("reason_of_rejection")
    @Expose
    private Object reasonOfRejection;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("material_client")
    @Expose
    private String materialClient;
    @SerializedName("material_gatepass_id")
    @Expose
    private String materialGatepassId;
    @SerializedName("firm_name")
    @Expose
    private String firmName;

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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getGatePassType() {
        return gatePassType;
    }

    public void setGatePassType(String gatePassType) {
        this.gatePassType = gatePassType;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleLoad() {
        return vehicleLoad;
    }

    public void setVehicleLoad(String vehicleLoad) {
        this.vehicleLoad = vehicleLoad;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getReturnableNonreturnable() {
        return returnableNonreturnable;
    }

    public void setReturnableNonreturnable(String returnableNonreturnable) {
        this.returnableNonreturnable = returnableNonreturnable;
    }

    public String getWorkOrderReference() {
        return workOrderReference;
    }

    public void setWorkOrderReference(String workOrderReference) {
        this.workOrderReference = workOrderReference;
    }

    public Object getMaterialName() {
        return materialName;
    }

    public void setMaterialName(Object materialName) {
        this.materialName = materialName;
    }

    public Object getSpecification() {
        return specification;
    }

    public void setSpecification(Object specification) {
        this.specification = specification;
    }

    public Object getUnit() {
        return unit;
    }

    public void setUnit(Object unit) {
        this.unit = unit;
    }

    public Object getQty() {
        return qty;
    }

    public void setQty(Object qty) {
        this.qty = qty;
    }

    public Object getQtyReceived() {
        return qtyReceived;
    }

    public void setQtyReceived(Object qtyReceived) {
        this.qtyReceived = qtyReceived;
    }

    public Object getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Object materialType) {
        this.materialType = materialType;
    }

    public Object getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(Object storeNo) {
        this.storeNo = storeNo;
    }

    public Object getReasonOfRejection() {
        return reasonOfRejection;
    }

    public void setReasonOfRejection(Object reasonOfRejection) {
        this.reasonOfRejection = reasonOfRejection;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getMaterialClient() {
        return materialClient;
    }

    public void setMaterialClient(String materialClient) {
        this.materialClient = materialClient;
    }

    public String getMaterialGatepassId() {
        return materialGatepassId;
    }

    public void setMaterialGatepassId(String materialGatepassId) {
        this.materialGatepassId = materialGatepassId;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

}
