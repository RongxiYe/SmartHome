package com.group.smarthome.pojo;

public class LightData {
    private String dataID;
    private String time;
    private String open;
    private String brightness;
    private String HARDWARE_hardwareID;
    private String mode;

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

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    public String getHARDWARE_hardwareID() {
        return HARDWARE_hardwareID;
    }

    public void setHARDWARE_hardwareID(String HARDWARE_hardwareID) {
        this.HARDWARE_hardwareID = HARDWARE_hardwareID;
    }
}
