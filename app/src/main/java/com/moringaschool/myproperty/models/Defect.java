package com.moringaschool.myproperty.models;

import java.io.Serializable;

public class Defect implements Serializable {

    private String description , building, houseNumber, stingUri, tenantId, managerName;

    public Defect() {
    }

    public Defect(String description, String building, String houseNumber, String stingUri) {
        this.description = description;
        this.building = building;
        this.houseNumber = houseNumber;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStingUri() {
        return stingUri;
    }

    public void setStingUri(String stingUri) {
        this.stingUri = stingUri;
    }
}
