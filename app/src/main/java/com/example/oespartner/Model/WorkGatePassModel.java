package com.example.oespartner.Model;

import com.google.gson.annotations.SerializedName;

public class WorkGatePassModel {

    @SerializedName("client")
    private String client;

    @SerializedName("branch")
    private String branch;

    @SerializedName("stakeholder_id")
    private String stakeholder_id;

    @SerializedName("person_id")
    private String person_id;

    @SerializedName("person_name")
    private String person_name;

    @SerializedName("p_valid_upto")
    private String p_valid_upto;

    @SerializedName("status")
    private String status;

    @SerializedName("id")
    private String id;

    public WorkGatePassModel(String client, String branch, String stakeholder_id, String person_id, String person_name, String p_valid_upto, String status, String id) {
        this.client = client;
        this.branch = branch;
        this.stakeholder_id = stakeholder_id;
        this.person_id = person_id;
        this.person_name = person_name;
        this.p_valid_upto = p_valid_upto;
        this.status = status;
        this.id=id;
    }

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

    public String getStakeholder_id() {
        return stakeholder_id;
    }

    public void setStakeholder_id(String stakeholder_id) {
        this.stakeholder_id = stakeholder_id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getP_valid_upto() {
        return p_valid_upto;
    }

    public void setP_valid_upto(String p_valid_upto) {
        this.p_valid_upto = p_valid_upto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
