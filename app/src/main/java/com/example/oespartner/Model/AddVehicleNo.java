package com.example.oespartner.Model;

import com.google.gson.annotations.SerializedName;

public class AddVehicleNo {
    @SerializedName("id")
    private String id;

    @SerializedName("vehicle_no")
    private String vehicle_no;

    @SerializedName("vehicle_type")
    private String vehicle_type;

    public AddVehicleNo(String id, String vehicle_no, String vehicle_type) {
        this.id = id;
        this.vehicle_no = vehicle_no;
        this.vehicle_type = vehicle_type;
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
