package com.group.smarthome.dao.Impl;

import com.group.smarthome.dao.HardwareDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class HardwareDAOImpl implements HardwareDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //search hardware information according to username
    public List<Map<String, Object>> queryInfo(String username){
        String sql = "select hardwareID,name,CATEGORY_categoryID,MANUFACTURER_manufacturerID from user u,hardware h "
                +"where u.FAMILY_familyID=h.FAMILY_familyID and u.userName=?";
        List<Map<String, Object>> lm;
        try{
            lm = jdbcTemplate.queryForList(sql,username);
        }catch (org.springframework.dao.EmptyResultDataAccessException e){
            lm = null;
        }
        return lm;
    }

    public int queryHardNum(String username){
        String sql = "select hardwareID,name,CATEGORY_categoryID,MANUFACTURER_manufacturerID from user u,hardware h "
                +"where u.FAMILY_familyID=h.FAMILY_familyID and u.userName=?";
        int num = jdbcTemplate.queryForObject(sql,Integer.class,username);
        return num;
    }

    public List<Map<String, Object>> queryData(String hid, String cid){
        String table = "";
        switch (cid){
            case "001":
                table = "lightdata";
                break;
            case "002":
                table = "doorsensordata";
                break;
            case "003":
                table = "windowsensordata";
                break;
            case "004":
                table = "garagesensordata";
                break;
            case "005":
                table = "temperaturedata";
                break;
            case "006":
                table = "humiditydata";
                break;
            case "007":
                table = "smokesensordata";
                break;
        }

        String sql = "select * from "+ table +" where HARDWARE_hardwareID=? ";//
        List<Map<String, Object>> lm = null;
        try{
            lm = jdbcTemplate.queryForList(sql,hid);
        }catch (org.springframework.dao.EmptyResultDataAccessException e){
            lm = null;
        }
        return lm;
    }

    //search if this hardware id exists
    public int queryHardId(String hid){

    }

    //search if this hardware has binded with other family
    public int queryHardFamily(String hid){

    }

    //bind operation
    public int bindHardFamily(String hid,String fid){

    }

    public String queryMName(String mid){
        String sql = "select name from manufacturer where manufacturerID=?";
        String manufacturer = "";
        try{
            manufacturer = jdbcTemplate.queryForObject(sql,String.class,mid);
        }catch (org.springframework.dao.EmptyResultDataAccessException e){
            manufacturer = "Unknown";
        }
        return manufacturer;
    }
}
