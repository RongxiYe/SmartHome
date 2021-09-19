package com.group.smarthome.utils;

public class CBFamily {
    private String State;
    private String isSuccess;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public String toString() {
        return "CBFamily{" +
                "State='" + State + '\'' +
                ", isSuccess='" + isSuccess + '\'' +
                '}';
    }
}
