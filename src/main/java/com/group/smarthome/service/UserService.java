package com.group.smarthome.service;

import com.group.smarthome.pojo.User;

import java.util.ArrayList;

public interface UserService {
    public String loginCheck(User user);
    public String registerCheck(User user);
    public String changePswCheck(User user);
    public String changePhoneCheck(User user);
    public String personalInfo(User user);
    public ArrayList<Object> queryFamilyInfo(User user);
}
