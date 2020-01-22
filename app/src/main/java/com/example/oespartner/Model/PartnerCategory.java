package com.example.oespartner.Model;

import java.util.HashMap;
import java.util.Map;

public class PartnerCategory {

    private String id;
    private String role;
    private String addPermi;
    private String editPermi;
    private String delPermi;
    private String gatePassPermi;
    private String accessConPermi;
    private Object attenPermi;
    private String materialPermi;
    private Object inventPermi;
    private Object fanPermi;
    private Object inOutPermi;
    private Object checklistPermi;
    private Object dailyCheckPermi;
    private Object idDetailPermi;
    private Object idSummPermi;
    private Object stakePermi;
    private Object permitPermi;
    private Object plantPermi;
    private Object transportPermi;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddPermi() {
        return addPermi;
    }

    public void setAddPermi(String addPermi) {
        this.addPermi = addPermi;
    }

    public String getEditPermi() {
        return editPermi;
    }

    public void setEditPermi(String editPermi) {
        this.editPermi = editPermi;
    }

    public String getDelPermi() {
        return delPermi;
    }

    public void setDelPermi(String delPermi) {
        this.delPermi = delPermi;
    }

    public String getGatePassPermi() {
        return gatePassPermi;
    }

    public void setGatePassPermi(String gatePassPermi) {
        this.gatePassPermi = gatePassPermi;
    }

    public String getAccessConPermi() {
        return accessConPermi;
    }

    public void setAccessConPermi(String accessConPermi) {
        this.accessConPermi = accessConPermi;
    }

    public Object getAttenPermi() {
        return attenPermi;
    }

    public void setAttenPermi(Object attenPermi) {
        this.attenPermi = attenPermi;
    }

    public String getMaterialPermi() {
        return materialPermi;
    }

    public void setMaterialPermi(String materialPermi) {
        this.materialPermi = materialPermi;
    }

    public Object getInventPermi() {
        return inventPermi;
    }

    public void setInventPermi(Object inventPermi) {
        this.inventPermi = inventPermi;
    }

    public Object getFanPermi() {
        return fanPermi;
    }

    public void setFanPermi(Object fanPermi) {
        this.fanPermi = fanPermi;
    }

    public Object getInOutPermi() {
        return inOutPermi;
    }

    public void setInOutPermi(Object inOutPermi) {
        this.inOutPermi = inOutPermi;
    }

    public Object getChecklistPermi() {
        return checklistPermi;
    }

    public void setChecklistPermi(Object checklistPermi) {
        this.checklistPermi = checklistPermi;
    }

    public Object getDailyCheckPermi() {
        return dailyCheckPermi;
    }

    public void setDailyCheckPermi(Object dailyCheckPermi) {
        this.dailyCheckPermi = dailyCheckPermi;
    }

    public Object getIdDetailPermi() {
        return idDetailPermi;
    }

    public void setIdDetailPermi(Object idDetailPermi) {
        this.idDetailPermi = idDetailPermi;
    }

    public Object getIdSummPermi() {
        return idSummPermi;
    }

    public void setIdSummPermi(Object idSummPermi) {
        this.idSummPermi = idSummPermi;
    }

    public Object getStakePermi() {
        return stakePermi;
    }

    public void setStakePermi(Object stakePermi) {
        this.stakePermi = stakePermi;
    }

    public Object getPermitPermi() {
        return permitPermi;
    }

    public void setPermitPermi(Object permitPermi) {
        this.permitPermi = permitPermi;
    }

    public Object getPlantPermi() {
        return plantPermi;
    }

    public void setPlantPermi(Object plantPermi) {
        this.plantPermi = plantPermi;
    }

    public Object getTransportPermi() {
        return transportPermi;
    }

    public void setTransportPermi(Object transportPermi) {
        this.transportPermi = transportPermi;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}