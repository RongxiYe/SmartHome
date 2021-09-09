package com.group.smarthome.dao;

import com.group.smarthome.pojo.User;

import javax.servlet.http.HttpSession;

public interface UserDAO {
      public String queryFamilyId(String userName);//check if the user has a family
      public String queryPassword(String userName);//login check if the username exists,match password and username
      public String queryPhone(String userName);//query user phone
      public int registerInsert(String userName,String password,String phoneNum);//insert username,psw,phone,userid into db
      public String changePsw(String userName,String newpsw);//change psw
      public String changePhone(String userName,String newphone);//change phone
      public boolean checkIdUnique(String userId);//check if generated id is unique
      public String generateUserId();//generate user id
//    public int generateUserPk();//generate user table primary key
}
