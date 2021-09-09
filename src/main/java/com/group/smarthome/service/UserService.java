package com.group.smarthome.service;

import com.group.smarthome.pojo.User;

public interface UserService {
    public String loginCheck(User user);
    public String registerCheck(User user);
    public String changePswCheck(User user);
}
