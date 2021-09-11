package com.group.smarthome.service.Impl;

import com.group.smarthome.dao.FamilyDAO;
import com.group.smarthome.dao.UserDAO;
import com.group.smarthome.pojo.Family;
import com.group.smarthome.pojo.User;
import com.group.smarthome.utils.MD5Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Service
public class UserServiceImpl {

    private UserDAO userDao;
    private FamilyDAO familyDAO;

    @Autowired
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }
    @Autowired
    public void setFamilyDAO(FamilyDAO familyDAO) {
        this.familyDAO = familyDAO;
    }

    //check the login state
    public String loginCheck(User user){
        String psw = userDao.queryPassword(user.getUserName());
        String md5psw = MD5Crypto.encrypt(user.getPassword());
        System.out.println(psw+"  "+md5psw);
        System.out.println(psw.equals(md5psw));
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
            String md5psw = MD5Crypto.encrypt(user.getPassword());
            int result = userDao.registerInsert(user.getUserName(),md5psw,user.getPhoneNum());
            System.out.println(result);
            if (result==1){
                return "Register Success!";
            }else{
                return "Register failed!";
            }
        }
    }

    public String changePswCheck(User user){
        String md5psw = MD5Crypto.encrypt(user.getPassword());
        String nowpsw = userDao.changePsw(user.getUserName(),md5psw);
        if (nowpsw.equals(md5psw)){
            return "Change password Success!";
        }else{
            return "Change password failed!";
        }
    }

    public String changePhoneCheck(User user){
        String phone = userDao.queryPhone(user.getUserName());
        if (!phone.equals(user.getPassword())){
            return "Wrong old phone!";
        }else{
            String nowphone = userDao.changePhone(user.getUserName(),user.getPhoneNum());
            if (nowphone.equals(user.getPhoneNum())){
                return "Change phone Success!";
            }else {
                return "Change phone failed!";
            }
        }
    }


    public ArrayList<Object> queryInfo(User user){
        String fid = userDao.queryFamilyId(user.getUserName());
        System.out.println("fid: "+fid);
        String phoneNum = userDao.queryPhone(user.getUserName());
        Family family = null;
        if (fid==null){
            family = null;
        }else{
            family = familyDAO.queryFamilyInfo(fid);
        }
        System.out.println(family);
        ArrayList<Object> arraylist = new ArrayList<Object>();
        arraylist.add(0,phoneNum);
        arraylist.add(1,family);
        System.out.println(arraylist);
        return arraylist;
    }
}
