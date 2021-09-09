package com.group.smarthome.pojo;

public class Hardware {
    private String hardwareID;
    private String name;
    private String instruction;
    private String FAMILY_familyID;
    private String CATEGORY_categoryID;
    private String MANUFACTURER_manufacturerID;

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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getFAMILY_familyID() {
        return FAMILY_familyID;
    }

    public void setFAMILY_familyID(String FAMILY_familyID) {
        this.FAMILY_familyID = FAMILY_familyID;
    }

    public String getCATEGORY_categoryID() {
        return CATEGORY_categoryID;
    }

    public void setCATEGORY_categoryID(String CATEGORY_categoryID) {
        this.CATEGORY_categoryID = CATEGORY_categoryID;
    }

    public String getMANUFACTURER_manufacturerID() {
        return MANUFACTURER_manufacturerID;
    }

    public void setMANUFACTURER_manufacturerID(String MANUFACTURER_manufacturerID) {
        this.MANUFACTURER_manufacturerID = MANUFACTURER_manufacturerID;
    }
}
