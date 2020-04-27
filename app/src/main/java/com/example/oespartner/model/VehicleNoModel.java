package com.example.oespartner.model;

import com.google.gson.annotations.SerializedName;

public class VehicleNoModel {

    @SerializedName("id")
    private String id;

    @SerializedName("vehicle_no")
    private String vehicle_no;

    @SerializedName("vehicle_type")
    private String vehicle_type;

    @SerializedName("status")
    private String status;

    public VehicleNoModel(String id, String vehicle_no, String vehicle_type,String status) {
        this.id = id;
        this.vehicle_no = vehicle_no;
        this.vehicle_type = vehicle_type;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }
}
