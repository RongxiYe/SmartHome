package com.group.smarthome.dao.Impl;

import com.group.smarthome.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //check if the user has a family
    @Override
    public String queryFamilyId(String userName){
        String sql = "select FAMILY_familyID from user where userName=?";
        String fid = "";
        try{
            fid = jdbcTemplate.queryForObject(sql,String.class,userName);
        }catch (org.springframework.dao.EmptyResultDataAccessException e){
            fid = null;
        }
        if (fid.equals("null")||fid.trim().equals("")){
            fid = null;
        }
        return fid;
    }
//try{
//
//    }catch (org.springframework.dao.EmptyResultDataAccessException e){
//            =null;
//    }
    //can check if psw is correct,if the username exists and if the user exists in registry
    @Override
    public String queryPassword(String userName){
        String sql = "select password from user where userName=?";
        String psw = "";
        try{
            psw = jdbcTemplate.queryForObject(sql,String.class,userName);
        }catch (org.springframework.dao.EmptyResultDataAccessException e) {
            psw = null;
        }
//        System.out.println("input:"+password+" "+password.getClass()+" db:"+psw+" "+psw.getClass());
        return psw;
    }

    //query user phone
    @Override
    public String queryPhone(String userName){
        String sql = "select phoneNum from user where userName=?";
        String phone = "";
        try{
            phone = jdbcTemplate.queryForObject(sql,String.class,userName);
        }catch (org.springframework.dao.EmptyResultDataAccessException e){
            phone = null;
        }
        return phone;
    }

    //insert username,psw,phone,userid into db
    @Override
    public int registerInsert(String userName,String password,String phoneNum){
        String sql = "INSERT INTO user (userID,userName,password,phoneNum) VALUES (?,?,?,?);";
        String userId = generateUserId();

        int result = jdbcTemplate.update(sql,userId,userName,password,phoneNum);
        return result;
    }

    //change psw
    @Override
    public String changePsw(String userName,String newpsw){
        String sql = "update user set password=? where username=?";
        jdbcTemplate.update(sql,newpsw,userName);
        String nowpsw = queryPassword(userName);
        return nowpsw;
    }

    //change phone
    @Override
    public String changePhone(String userName,String newphone){
        String sql = "update user set phoneNum=? where username=?";
        jdbcTemplate.update(sql,newphone,userName);
        String nowphone=queryPhone(userName);
        return nowphone;
    }

    //check if generated id is unique
    @Override
    public boolean checkIdUnique(String userId){
        boolean isUnique=true;//表示生成的id没有出现过
        String sql = "select username from user where userID=?";
        String userName = "";
        try{
            userName = jdbcTemplate.queryForObject(sql,String.class,userId);
        }catch (org.springframework.dao.EmptyResultDataAccessException e){
            userName = null;
        }
        if (userName!=null){
            isUnique=false;
        }
        return isUnique;
    }

    //generate final user id
    @Override
    public String generateUserId(){
        String userId="";
        do {
            for(int i=0;i<13;i++) {
                Integer j=(int)(Math.random()*11)%10;
                userId+=j.toString();
            }
        } while (!this.checkIdUnique(userId));
        return userId;
      }
//    public int generateUserPk();//generate user table primary key

}
