package com.group.smarthome.pojo;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String userID;
    private String userName;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getFAMILY_familyID() {
        return FAMILY_familyID;
    }

    public void setFAMILY_familyID(String FAMILY_familyID) {
        this.FAMILY_familyID = FAMILY_familyID;
    }

    private String password;
    private String phoneNum;
    private String FAMILY_familyID;


}
