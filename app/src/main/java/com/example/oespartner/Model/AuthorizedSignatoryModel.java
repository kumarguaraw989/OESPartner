package com.example.oespartner.Model;

import com.google.gson.annotations.SerializedName;

public class AuthorizedSignatoryModel {

    @SerializedName("client")
    private String client;

    @SerializedName("branch")
    private String branch;

    @SerializedName("person_name")
    private String person_name;

    @SerializedName("valid_upto")
    private String valid_upto;

    @SerializedName("id")
    private String id;

    public AuthorizedSignatoryModel(String client, String branch, String person_name, String valid_upto,String id) {
        this.client = client;
        this.branch = branch;
        this.person_name = person_name;
        this.valid_upto = valid_upto;
        this.id=id;
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

    public String getValid_upto() {
        return valid_upto;
    }

    public void setValid_upto(String valid_upto) {
        this.valid_upto = valid_upto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
