package com.group.smarthome.pojo;

public class LightData extends Data{
    private String brightness;

    public LightData() {
    }

    public LightData(String time, String data, String brightness) {
        super(time, data);
        this.brightness = brightness;
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

}
