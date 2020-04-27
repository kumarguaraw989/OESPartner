package com.example.oespartner.model;

public class AddPartnerPersonModel {

    private String email;
    private String role;
    private String title;
    private String person_name;
    private String phone;
    private String email1;
    private String photo;
    private String father_name;
    private String dob;
    private String age;
    private String gender;
    private String blood_group;

    public AddPartnerPersonModel(String email, String role, String title, String person_name, String phone,String email1, String photo,
                                 String father_name, String dob, String age, String gender, String blood_group) {
        this.email = email;
        this.role = role;
        this.title = title;
        this.person_name = person_name;
        this.phone = phone;
        this.email1=email1;
        this.photo = photo;
        this.father_name = father_name;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.blood_group = blood_group;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getemail1() {
        return email1;
    }

    public void setemail1(String email1) {
        this.email1 = email1;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }
}
