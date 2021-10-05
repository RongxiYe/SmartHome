package com.group.smarthome.service;

import com.group.smarthome.pojo.User;
import com.group.smarthome.utils.ControlLight;
import com.group.smarthome.utils.HardInfo;

import java.util.ArrayList;

public interface UserService {
    public String loginCheck(User user);
    public String registerCheck(User user);
    public String changePswCheck(User user);
    public String changePhoneCheck(User user);
    public String personalInfo(User user);
    public ArrayList<Object> queryFamilyInfo(User user);
    public HardInfo viewHardInfo(User user,int COUNT);
    public String addHardware(String username, String hid);
    public ControlLight controlLight(String hid,String open,String brightness);
}
