package com.example.oespartner.model;

import com.google.gson.annotations.SerializedName;

public class VisitorGatePassModel {

    @SerializedName("person_id")
    private String Person_id;

    @SerializedName("client")
    private String client;

    @SerializedName("branch")
    private String branch;

    @SerializedName("person_name")
    private String person_name;

    @SerializedName("visit_date")
    private String visit_date;

    @SerializedName("status")
    private String status;

    @SerializedName("id")
    private String id;

    public VisitorGatePassModel(String person_id, String client, String branch, String person_name, String visit_date,String status,String id) {
        Person_id = person_id;
        this.client = client;
        this.branch = branch;
        this.person_name = person_name;
        this.visit_date = visit_date;
        this.status = status;
        this.id=id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerson_id() {
        return Person_id;
    }

    public void setPerson_id(String person_id) {
        Person_id = person_id;
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

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }





}
