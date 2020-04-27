package com.example.oespartner.model;

import com.google.gson.annotations.SerializedName;

public class Test {
    @SerializedName("id")
    private String Person_id;

    public Test(String person_id) {
        Person_id = person_id;
    }

    public String getPerson_id() {
        return Person_id;
    }

    public void setPerson_id(String person_id) {
        Person_id = person_id;
    }
}
