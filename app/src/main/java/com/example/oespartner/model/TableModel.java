package com.example.oespartner.model;

public class TableModel {
    String MaterialName;
    String Specification;
    String Unit;
    String Quantity;

    public TableModel(String materialName, String specification, String unit, String quantity) {
        MaterialName = materialName;
        Specification = specification;
        Unit = unit;
        Quantity = quantity;
    }

    public TableModel(){

    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    @Override
    public String toString() {
        return "TableModel{" +
                "MaterialName='" + MaterialName + '\'' +
                ", Specification='" + Specification + '\'' +
                ", Unit='" + Unit + '\'' +
                ", Quantity='" + Quantity + '\'' +
                '}';
    }
}
