package com.moringaschool.myproperty.models;

public class DoneDefect {
    private String name, uri, contactorName, contractorPhone, contractorLocation, managerName, tenantId;

    public DoneDefect() {
    }

    public DoneDefect(String name, String uri, String contactorName, String contractorPhone, String contractorLocation, String managerName, String tenantId) {
        this.name = name;
        this.uri = uri;
        this.contactorName = contactorName;
        this.contractorPhone = contractorPhone;
        this.contractorLocation = contractorLocation;
        this.managerName = managerName;
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getContactorName() {
        return contactorName;
    }

    public void setContactorName(String contactorName) {
        this.contactorName = contactorName;
    }

    public String getContractorPhone() {
        return contractorPhone;
    }

    public void setContractorPhone(String contractorPhone) {
        this.contractorPhone = contractorPhone;
    }

    public String getContractorLocation() {
        return contractorLocation;
    }

    public void setContractorLocation(String contractorLocation) {
        this.contractorLocation = contractorLocation;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
