package com.group.smarthome.utils;

public class Cph {
    private String cphState;
    private String isCphSuccess;

    public String getCphState() {
        return cphState;
    }

    public void setCphState(String cphState) {
        this.cphState = cphState;
    }

    public String getIsCphSuccess() {
        return isCphSuccess;
    }

    public void setIsCphSuccess(String isCphSuccess) {
        this.isCphSuccess = isCphSuccess;
    }

    @Override
    public String toString() {
        return "Cph{"+cphState+" "+isCphSuccess+"}";
    }
}
