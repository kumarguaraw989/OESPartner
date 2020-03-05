package com.example.oespartner.Model;

public class AddChamberDetailsModel {
    private String No;
    private String Capacity;
    private  String dlLenght;
    private String plLenght;

    public AddChamberDetailsModel() {
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public String getDlLenght() {
        return dlLenght;
    }

    public void setDlLenght(String dlLenght) {
        this.dlLenght = dlLenght;
    }

    public String getPlLenght() {
        return plLenght;
    }

    public void setPlLenght(String plLenght) {
        this.plLenght = plLenght;
    }
}
