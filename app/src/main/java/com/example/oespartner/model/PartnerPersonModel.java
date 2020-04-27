package com.example.oespartner.model;

import com.google.gson.annotations.SerializedName;

public class PartnerPersonModel {

    @SerializedName("id")
    private String id;

    @SerializedName("person_name")
    private String person_name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    public PartnerPersonModel(String id, String person_name, String phone, String email) {
        this.id = id;
        this.person_name = person_name;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
