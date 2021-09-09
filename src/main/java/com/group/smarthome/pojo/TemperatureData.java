package com.group.smarthome.pojo;

public class TemperatureData {
    private String dataID;
    private String time;
    private String temperature;
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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHARDWARE_hardwareID() {
        return HARDWARE_hardwareID;
    }

    public void setHARDWARE_hardwareID(String HARDWARE_hardwareID) {
        this.HARDWARE_hardwareID = HARDWARE_hardwareID;
    }
}
