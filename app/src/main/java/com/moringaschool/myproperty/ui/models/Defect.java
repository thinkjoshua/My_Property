package com.moringaschool.myproperty.ui.models;

import android.net.Uri;

public class Defect {
    String description;
    Uri imageUri;
    String stingUri;
    int defect_id;
    String building;
    String houseNumber;

    public Defect(String description,  int defect_id, String stingUri, String building, String houseNumber) {
        this.description = description;
        this.stingUri = stingUri;
        this.defect_id = defect_id;
        this.building = building;
        this.houseNumber = houseNumber;
    }

//    public Defect(String description, String building, String houseNumber) {
//        this.description = description;
//        this.building = building;
//        this.houseNumber = houseNumber;
//    }

    public Defect(String description,  String stingUri, String building, String houseNumber) {
        this.description = description;
        this.stingUri = stingUri;
        this.building = building;
        this.houseNumber = houseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getStingUri() {
        return stingUri;
    }

    public void setStingUri(String stingUri) {
        this.stingUri = stingUri;
    }

    public int getDefect_id() {
        return defect_id;
    }

    public void setDefect_id(int defect_id) {
        this.defect_id = defect_id;
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
}
