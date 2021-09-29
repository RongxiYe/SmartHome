package com.group.smarthome.pojo;

public class LightData extends Data{
    private String brightness;


    public String getDataID() {
        return super.getDataID();
    }

    public void setDataID(String dataID) {
        super.setDataID(dataID);
    }

    public String getTime() {
        return super.getTime();
    }

    public void setTime(String time) {
        super.setTime(time);
    }

    public String getOpen() {
        return super.getData();
    }

    public void setOpen(String open) {
        super.setData(open);
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    public String getHARDWARE_hardwareID() {
        return super.getHARDWARE_hardwareID();
    }

    public void setHARDWARE_hardwareID(String HARDWARE_hardwareID) {
        super.setHARDWARE_hardwareID(HARDWARE_hardwareID);
    }
}
