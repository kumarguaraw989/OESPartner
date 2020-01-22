package com.example.oespartner.Model;

import com.google.gson.annotations.SerializedName;

public class AddWorkGatePassModel {

@SerializedName("email")
private String email;

@SerializedName("role")
private String role;

@SerializedName("client")
private String client;

@SerializedName("branch")
private String branch;

@SerializedName("person_name")
private String person_name;


@SerializedName("person_id")
private String person_id;

//    @SerializedName("report")
//    private String report;

@SerializedName("designation")
private String designation;

@SerializedName("driving_license_no")
private String driving_license_no;


//@SerializedName("license_valid_upto")
//private String license_valid_upto;
//
//@SerializedName("vehicle_no")
//private String vehicle_no;

//    @SerializedName("helper_name")
//    private String helper_name;

//    @SerializedName("eye_test_date")
//    private String eye_test_date;

//    @SerializedName("training_certificate_no")
//    private String training_certificate_no;

//    @SerializedName("training_valid_upto")
//    private String training_valid_upto;
//
//    @SerializedName("ex_armed")
//    private String ex_armed;

//    @SerializedName("security_reference_no")
//    private String security_reference_no;
//
//    @SerializedName("security_copy")
//    private String security_copy;

@SerializedName("work_reference_no")
private String work_reference_no;

@SerializedName("work_description")
private String work_description;

@SerializedName("work_valid_upto")
private String work_valid_upto;

@SerializedName("police_verify")
private String police_verify;

@SerializedName("visa_validity")
private String visa_validity;

    @SerializedName("p_valid_upto")
    private String p_valid_upto;
//
//    @SerializedName("p_driving_license_validity")
//    private String p_driving_license_validity;
//
//    @SerializedName("p_eye_test_date")
//    private String p_eye_test_date;
//
//    @SerializedName("p_training_certificate_validity")
//    private String p_training_certificate_validity;

@SerializedName("declaration")
private String declaration;

@SerializedName("j_declaration")
private String j_declaration;

@SerializedName("h_declaration")
private String h_declaration;

@SerializedName("stakeholder_id")
private String stakeholder_id;

        @SerializedName("id")
        private String id;

public AddWorkGatePassModel(String email, String role, String client, String branch, String person_name,String person_id, String designation,
        String driving_license_no, String work_reference_no, String work_description, String work_valid_upto, String police_verify, String visa_validity,
        String declaration, String j_declaration, String h_declaration,String stakeholder_id,String p_valid_upto,String id) {
        this.email = email;
        this.role = role;
        this.client = client;
        this.branch = branch;
        this.person_name = person_name;
        this.person_id=person_id;
        this.designation = designation;
        this.driving_license_no = driving_license_no;
        this.work_reference_no = work_reference_no;
        this.work_description = work_description;
        this.work_valid_upto = work_valid_upto;
        this.police_verify = police_verify;
        this.visa_validity = visa_validity;
        this.declaration = declaration;
        this.j_declaration = j_declaration;
        this.h_declaration = h_declaration;
        this.stakeholder_id=stakeholder_id;
        this.p_valid_upto=p_valid_upto;
        this.id=id;

        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
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

public String getPerson_name() {
        return person_name;
        }

public void setPerson_name(String person_name) {
        this.person_name = person_name;
        }

public String getPerson_id() {
        return person_id;
        }

public void setPerson_id(String person_id) {
        this.person_id = person_id;
        }

public String getDesignation() {
        return designation;
        }

public void setDesignation(String designation) {
        this.designation = designation;
        }

public String getDriving_license_no() {
        return driving_license_no;
        }

public void setDriving_license_no(String driving_license_no) {
        this.driving_license_no = driving_license_no;
        }

public String getWork_reference_no() {
        return work_reference_no;
        }

public void setWork_reference_no(String work_reference_no) {
        this.work_reference_no = work_reference_no;
        }

public String getWork_description() {
        return work_description;
        }

public void setWork_description(String work_description) {
        this.work_description = work_description;
        }

public String getWork_valid_upto() {
        return work_valid_upto;
        }

public void setWork_valid_upto(String work_valid_upto) {
        this.work_valid_upto = work_valid_upto;
        }

public String getPolice_verify() {
        return police_verify;
        }

public void setPolice_verify(String police_verify) {
        this.police_verify = police_verify;
        }

public String getVisa_validity() {
        return visa_validity;
        }

public void setVisa_validity(String visa_validity) {
        this.visa_validity = visa_validity;
        }

public String getDeclaration() {
        return declaration;
        }

public void setDeclaration(String declaration) {
        this.declaration = declaration;
        }

public String getJ_declaration() {
        return j_declaration;
        }

public void setJ_declaration(String j_declaration) {
        this.j_declaration = j_declaration;
        }

public String getH_declaration() {
        return h_declaration;
        }

public void setH_declaration(String h_declaration) {
        this.h_declaration = h_declaration;
        }

    public String getStakeholder_id() {
        return stakeholder_id;
    }

    public void setStakeholder_id(String stakeholder_id) {
        this.stakeholder_id = stakeholder_id;
    }

        public String getP_valid_upto() {
                return p_valid_upto;
        }

        public void setP_valid_upto(String p_valid_upto) {
                this.p_valid_upto = p_valid_upto;
        }
}
