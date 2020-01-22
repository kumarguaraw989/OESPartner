package com.example.oespartner.Model;

public class AddMaterialGatePassModel {

    private String email;
    private String role;
    private String client;
    private String branch;
    private String gate_pass_type;
    private String partner_code;
    private String partner_name;
    private String vehicle_no;
    private String vehicle_load;
    private String reason;
    private String belong_to;
    private String returnable_nonreturnable;
    private String work_order_reference;
    private String date_time;

    public AddMaterialGatePassModel(String email, String role, String client, String branch, String gate_pass_type, String partner_code, String partner_name, String vehicle_no, String vehicle_load, String reason, String belong_to, String returnable_nonreturnable,String date_time) {
        this.email = email;
        this.role = role;
        this.client = client;
        this.branch = branch;
        this.gate_pass_type = gate_pass_type;
        this.partner_code = partner_code;
        this.partner_name = partner_name;
        this.vehicle_no = vehicle_no;
        this.vehicle_load = vehicle_load;
        this.reason = reason;
        this.belong_to = belong_to;
        this.returnable_nonreturnable = returnable_nonreturnable;
        this.work_order_reference = work_order_reference;
        this.date_time = date_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getGate_pass_type() {
        return gate_pass_type;
    }

    public void setGate_pass_type(String gate_pass_type) {
        this.gate_pass_type = gate_pass_type;
    }

    public String getPartner_code() {
        return partner_code;
    }

    public void setPartner_code(String partner_code) {
        this.partner_code = partner_code;
    }

    public String getPartner_name() {
        return partner_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getVehicle_load() {
        return vehicle_load;
    }

    public void setVehicle_load(String vehicle_load) {
        this.vehicle_load = vehicle_load;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBelong_to() {
        return belong_to;
    }

    public void setBelong_to(String belong_to) {
        this.belong_to = belong_to;
    }

    public String getReturnable_nonreturnable() {
        return returnable_nonreturnable;
    }

    public void setReturnable_nonreturnable(String returnable_nonreturnable) {
        this.returnable_nonreturnable = returnable_nonreturnable;
    }

    public String getWork_order_reference() {
        return work_order_reference;
    }

    public void setWork_order_reference(String work_order_reference) {
        this.work_order_reference = work_order_reference;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}
