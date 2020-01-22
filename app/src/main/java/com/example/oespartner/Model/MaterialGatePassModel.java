package com.example.oespartner.Model;

import com.google.gson.annotations.SerializedName;

public class MaterialGatePassModel {

    @SerializedName("id")
    private String id;

    @SerializedName("date_time")
    private String date_time;

    @SerializedName("belong_to")
    private String material_category;

    @SerializedName("vehicle_no")
    private String vehicle_no;

    @SerializedName("returnable_nonreturnable")
    private String type_of_material;

    @SerializedName("reason")
    private String reason;

    @SerializedName("status")
    private String status;

    public MaterialGatePassModel(String id,String date_time, String material_category, String vehicle_no, String type_of_material, String reason, String status) {
        this.id = id;
        this.date_time = date_time;
        this.material_category = material_category;
        this.vehicle_no = vehicle_no;
        this.type_of_material = type_of_material;
        this.reason = reason;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getMaterial_category() {
        return material_category;
    }

    public void setMaterial_category(String material_category) {
        this.material_category = material_category;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getType_of_material() {
        return type_of_material;
    }

    public void setType_of_material(String type_of_material) {
        this.type_of_material = type_of_material;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
