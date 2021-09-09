package com.group.smarthome.service.Impl;

import com.group.smarthome.dao.UserDAO;
import com.group.smarthome.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl {

    private UserDAO userDao;

    @Autowired
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    //check the login state
    public String loginCheck(User user){
        String psw = userDao.queryPassword(user.getUserName());
        String md5psw = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
//        System.out.println(psw+"  "+md5psw);
//        System.out.println(psw.equals(md5psw));
        if (psw==null){
            return "No Such User! Please register!";
        }else if (!psw.equals(md5psw)){
            return "Wrong Password!";
        }else {
            return "Login Success!";
        }
    }

    public String registerCheck(User user){
        String psw = userDao.queryPassword(user.getUserName());
        if (psw!=null){
            return "User name already exists!";
        }else{
            String md5psw = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            int result = userDao.registerInsert(user.getUserName(),md5psw,user.getPhoneNum());
            System.out.println(result);
            if (result==1){
                return "Register Success!";
            }else{
                return "Register failed!";
            }

        }
    }

}
