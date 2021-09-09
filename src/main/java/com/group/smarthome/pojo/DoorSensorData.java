package com.group.smarthome.pojo;

public class DoorSensorData {
    private String dataID;
    private String time;
    private String movement;
    private String HARDWARE_hardwareID;

    public String getDataID() {
        return dataID;
    }

    public void setDataID(String dataID) {
        this.dataID = dataID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public String getHARDWARE_hardwareID() {
        return HARDWARE_hardwareID;
    }

    public void setHARDWARE_hardwareID(String HARDWARE_hardwareID) {
        this.HARDWARE_hardwareID = HARDWARE_hardwareID;
    }
}
