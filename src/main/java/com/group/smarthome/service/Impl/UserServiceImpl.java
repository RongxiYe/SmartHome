package com.group.smarthome.service.Impl;

import com.group.smarthome.dao.FamilyDAO;
import com.group.smarthome.dao.UserDAO;
import com.group.smarthome.pojo.Family;
import com.group.smarthome.pojo.User;
import com.group.smarthome.utils.MD5Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String personalInfo(User user){
        String phoneNum = userDao.queryPhone(user.getUserName());
        return phoneNum;
    }

    public ArrayList<Object> queryFamilyInfo(User user){
        String fid = userDao.queryFamilyId(user.getUserName());
        System.out.println("fid: "+fid);
        ArrayList<Object> arraylist = new ArrayList<Object>();
        Family family = null;
        ArrayList<User> useral = null;
        if (fid==null){
            family = null;
            arraylist.add(0,null);
        }else{
            family = familyDAO.queryFamilyInfo(fid);
            arraylist.add(0,family);
            useral = familyDAO.queryFamilyMember(fid);
            arraylist.add(1,useral);
        }
        System.out.println(family);
        System.out.println(arraylist);
        System.out.println(useral);
        return arraylist;
    }

    public String createFamily(User user,Family family){
        String fid = familyDAO.createFamilyInsert(family.getAddress(),family.getPostcode());
        if(fid==null){
            return "Create failed!";
        }else{
            familyDAO.bindFamilyInsert(fid,user.getUserName());
            String nowfid = userDao.queryFamilyId(user.getUserName());
            if (fid.equals(nowfid)){
                return "Create Success!";
            }else{
                return "Create failed!";
            }
        }
    }

    public String bindFamily(User user,Family family){
        boolean isunique = familyDAO.checkdUnique(family.getFamilyID());
        if (isunique==true){
            return "Wrong FamilyID!";
        }else {
            familyDAO.bindFamilyInsert(family.getFamilyID(), user.getUserName());
            String nowfid = userDao.queryFamilyId(user.getUserName());
            if (family.getFamilyID().equals(nowfid)) {
                return "Bind Success!";
            } else {
                return "Bind failed!";
            }
        }
    }
}
