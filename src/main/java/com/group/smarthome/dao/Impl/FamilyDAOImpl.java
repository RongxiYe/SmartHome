package com.group.smarthome.dao.Impl;

import com.group.smarthome.dao.FamilyDAO;
import com.group.smarthome.pojo.Family;
import com.group.smarthome.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class FamilyDAOImpl implements FamilyDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Family queryFamilyInfo(String fid) {
        String sql = "select * from family where familyID=?";
        Family family;
        try{
            family = jdbcTemplate.queryForObject(sql,new RowMapper<Family>() {

                @Override
                public Family mapRow(ResultSet rs, int i) throws SQLException {
                    Family family = new Family();
                    family.setFamilyID(rs.getString("familyID"));
                    family.setAddress(rs.getString("address"));
                    family.setPostcode(rs.getString("postcode"));
                    return family;
                }
            },fid);
        }catch (org.springframework.dao.EmptyResultDataAccessException e) {
            family = null;
        }
        return family;
    }

    @Override
    public ArrayList<User> queryFamilyMember(String fid){
        String sql = "select * from user where FAMILY_familyID=?";
        ArrayList<User> al;
        try{
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, fid);
            al = new ArrayList<User>();
            for (Map<String, Object> map : list){
                User user = new User();
                user.setUserID(null);
                user.setUserName((String)map.get("userName"));
                user.setPassword(null);
                user.setPhoneNum((String)map.get("phoneNum"));
                user.setFAMILY_familyID(fid);
                al.add(user);
            }
        }catch (org.springframework.dao.EmptyResultDataAccessException e){
            al = null;
        }
        return al;
    }

    //generate unique fid
    @Override
    public String generatefid(){
        String fid="";
        do {
            for(int i=0;i<6;i++) {
                Integer j=(int)(Math.random()*11)%10;
                fid+=j.toString();
            }
            fid+="-";
            for(int i=0;i<4;i++) {
                Integer j=(int)(Math.random()*11)%10;
                fid+=j.toString();
            }
        }while(!this.checkdUnique(fid));

        return fid;
    }

    //check if fid is unique
    @Override
    public boolean checkdUnique(String fid){
        boolean flag=true;//fid is unique
        String sql = "select postcode from family where familyID=?";
        String pc = "";
        try{
            pc = jdbcTemplate.queryForObject(sql,String.class,fid);
        }catch (org.springframework.dao.EmptyResultDataAccessException e){
            pc = null;
        }
        if(pc!=null){
            flag = false;
        }
        return flag;
    }

    //create an empty family;
    @Override
    public String createFamilyInsert(String address,String postcode){
        String sql = "INSERT INTO family (familyID,address,postcode) VALUES (?,?,?);";
        String fid = generatefid();
        int result = jdbcTemplate.update(sql,fid,address,postcode);
        if (result==1){
            return fid;
        }else{
            return null;
        }
    }

    //bind with an existing family
    @Override
    public String bindFamilyInsert(String fid,String username){
        String sql = "update user set FAMILY_familyID=? where userName=?";
        int result = jdbcTemplate.update(sql,fid,username);
        if (result==1){
            return fid;
        }else{
            return null;
        }
    }
}
