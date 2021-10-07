package com.group.smarthome.utils;

import com.group.smarthome.pojo.Data;

import java.util.ArrayList;

public class HardAbstract {
    private String hardwareID;
    private String name;
    private String manufactureName;
    private String hasData;
    private String hardType;
    private ArrayList<Data> hd;

    public HardAbstract(){}

    public HardAbstract(String hardwareID, String name, String manufactureName, String hasData, String hardType, ArrayList<Data> hd) {
        this.hardwareID = hardwareID;
        this.name = name;
        this.manufactureName = manufactureName;
        this.hasData = hasData;
        this.hardType = hardType;
        this.hd = hd;
    }

    public String getHardwareID() {
        return hardwareID;
    }

    public void setHardwareID(String hardwareID) {
        this.hardwareID = hardwareID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    public String getHasData() {
        return hasData;
    }

    public void setHasData(String hasData) {
        this.hasData = hasData;
    }

    public String getHardType() {
        return hardType;
    }

    public void setHardType(String hardType) {
        this.hardType = hardType;
    }

    public ArrayList<Data> getHd() {
        return hd;
    }

    public void setHd(ArrayList<Data> hd) {
        this.hd = hd;
    }
}
