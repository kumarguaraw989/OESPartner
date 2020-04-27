package com.example.oespartner.model;

import com.google.gson.annotations.SerializedName;

public class AddAuthorizedSignatoryModel {

    @SerializedName("id")
    private String id;

        @SerializedName("email")
        private String email;

        @SerializedName("role")
        private String role;

        @SerializedName("branch")
        private String branch;

        @SerializedName("person_name")
        private String person_name;

        @SerializedName("reference_no")
        private String reference_no;

        @SerializedName("description")
        private String description;

        @SerializedName("client")
        private String client;

        @SerializedName("valid_upto")
        private String valid_upto;

        @SerializedName("designationnn")
        private String designationnn;



        public AddAuthorizedSignatoryModel(String id,String email, String role, String branch, String person_name, String reference_no, String description, String client, String valid_upto, String designationnn) {
            this.id=id;
            this.email = email;
            this.role = role;
            this.branch = branch;
            this.person_name = person_name;
            this.reference_no = reference_no;
            this.description = description;
            this.client = client;
            this.valid_upto = valid_upto;
            this.designationnn = designationnn;

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

        public String getReference_no() {
            return reference_no;
        }

        public void setReference_no(String reference_no) {
            this.reference_no = reference_no;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public String getValid_upto() {
            return valid_upto;
        }

        public void setValid_upto(String valid_upto) {
            this.valid_upto = valid_upto;
        }

        public String getDesignationnn() {
            return designationnn;
        }

        public void setDesignationnn(String designationnn) {
            this.designationnn = designationnn;
        }
    }