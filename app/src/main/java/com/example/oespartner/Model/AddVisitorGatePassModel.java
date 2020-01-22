package com.example.oespartner.Model;

import com.google.gson.annotations.SerializedName;

public class AddVisitorGatePassModel {

    @SerializedName("email")
    private String email;

    @SerializedName("client")
    private String client;

    @SerializedName("role")
    private String role;

    @SerializedName("branch")
    private String branch;

    @SerializedName("person_name")
    private String person_name;

    @SerializedName("firm_name")
    private String firm_name;

    @SerializedName("designation")
    private String designation;

    @SerializedName("approval")
    private String approval;

    @SerializedName("person_visited")
    private String person_visited;

    @SerializedName("reason")
    private String reason;

    @SerializedName("visit_date")
    private String visit_date;

    @SerializedName("visit_time")
    private String visit_time;

    @SerializedName("declaration")
    private String declaration;
    @SerializedName("person_id")
    private String personId;

    @SerializedName("id")
    private String id;
    public AddVisitorGatePassModel(String email, String role,String client, String branch, String person_name, String firm_name, String designation,
                                   String approval, String person_visited, String reason, String visit_date, String visit_time, String declaration,String personId,String id) {
        this.email = email;
        this.client = client;
        this.role = role;
        this.branch = branch;
        this.person_name = person_name;
        this.firm_name = firm_name;
        this.designation = designation;
        this.approval = approval;
        this.person_visited = person_visited;
        this.reason = reason;
        this.visit_date = visit_date;
        this.visit_time = visit_time;
        this.declaration = declaration;
        this.personId=personId;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getFirm_name() {
        return firm_name;
    }

    public void setFirm_name(String firm_name) {
        this.firm_name = firm_name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getPerson_visited() {
        return person_visited;
    }

    public void setPerson_visited(String person_visited) {
        this.person_visited = person_visited;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(String visit_time) {
        this.visit_time = visit_time;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }
}
