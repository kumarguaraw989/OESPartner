package com.example.oespartner.Model;

import com.google.gson.annotations.SerializedName;

public class OtpModel {

    @SerializedName("content")
    private int content;

    @SerializedName("contact")
    private String contact;

    public int getContent() {
        return content;
    }
    public void setContent(int content) {
        this.content = content;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "OtpModel{" +
                "content='" + content + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

}
