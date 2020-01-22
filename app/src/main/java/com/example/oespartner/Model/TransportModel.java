package com.example.oespartner.Model;

import com.google.gson.annotations.SerializedName;

public class TransportModel {

    @SerializedName("vehicle_name")
    private String vehicle_no;

    @SerializedName("vehicle_type")
    private String vehicle_type;

    @SerializedName("vehicle_capacity")
    private String vehicle_capacity;

    @SerializedName("transport_id")
    private String id;



    public TransportModel(String vehicle_no, String vehicle_type, String vehicle_capacity, String id) {
        this.vehicle_no = vehicle_no;
        this.vehicle_type = vehicle_type;
        this.vehicle_capacity = vehicle_capacity;
        this.id= id;
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

    public String getVehicle_capacity() {
        return vehicle_capacity;
    }

    public void setVehicle_capacity(String vehicle_capacity) {
        this.vehicle_capacity = vehicle_capacity;
    }
}
