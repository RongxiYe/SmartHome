package com.group.smarthome.utils;

import java.util.ArrayList;

public class HardInfo {
    private String hasHardware;
    private int HardNum;
    private ArrayList<HardAbstract> abs;

    public String getHasHardware() {
        return hasHardware;
    }

    public void setHasHardware(String hasHardware) {
        this.hasHardware = hasHardware;
    }

    public int getHardNum() {
        return HardNum;
    }

    public void setHardNum(int hardNum) {
        HardNum = hardNum;
    }

    public ArrayList<HardAbstract> getAbs() {
        return abs;
    }

    public void setAbs(ArrayList<HardAbstract> abs) {
        this.abs = abs;
    }
}
