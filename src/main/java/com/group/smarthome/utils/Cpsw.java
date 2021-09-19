package com.group.smarthome.utils;

public class Cpsw {
    private String cpswState;
    private String isCpswSuccess;

    public String getCpswState() {
        return cpswState;
    }

    public void setCpswState(String cpswState) {
        this.cpswState = cpswState;
    }

    public String getIsCpswSuccess() {
        return isCpswSuccess;
    }

    public void setIsCpswSuccess(String isCpswSuccess) {
        this.isCpswSuccess = isCpswSuccess;
    }

    @Override
    public String toString() {
        return "Cpsw{"+cpswState+" "+isCpswSuccess+"}";
    }
}
