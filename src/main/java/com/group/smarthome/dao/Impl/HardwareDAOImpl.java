package com.group.smarthome.dao.Impl;

import com.group.smarthome.dao.HardwareDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;

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

        String sql = "select * from "+ table +" where HARDWARE_hardwareID=? order by time desc";//
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
        int flag = 0;
        String sql = "select count(*) from hardware where hardwareID=?";
        Map<String,Object> map = jdbcTemplate.queryForMap(sql,hid);
        if ((int) map.get("count(*)")!=0){
            flag = 1;
        }
        return flag;
    }

    //search if this hardware has binded with other family
    public int queryHardFamily(String hid){
        int flag = 0;
        String sql = "select FAMILY_familyID from hardware where hardwareID=?";
        Map<String,Object> map = jdbcTemplate.queryForMap(sql,hid);
        if (map.get("FAMILY_familyID")==null){
            flag = 0;
        }else{
            if (((String)map.get("FAMILY_familyID")).trim().equals("0")){
                flag = 0;
            }else{
                flag = 1;
            }
        }
        return flag;
    }

    //bind operation
    public String bindHardFamily(String hid,String fid){
        String state = "Failed";
        String sql = "update hardware set FAMILY_familyID=? where hardwareID=?";
        jdbcTemplate.update(sql,fid,hid);
        sql = "select FAMILY_familyID from hardware where hardwareID=?";
        String nowfid = jdbcTemplate.queryForObject(sql,String.class,hid);
        if (nowfid!=null){
            if (nowfid.equals(fid)){
                state = "Success";
            }
        }
        return state;
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

    public void updateLight(String hid,String open,String brightness){
        String sql = "insert into lightdata (dataID,time,open,brightness,HARDWARE_hardwareID) values (?,?,?,?,?);";
        String dataID = generateDataId();
        String time = getTime();
        jdbcTemplate.update(sql,dataID,time,open,brightness,hid);
    }

    public Map<String, Object> queryLight(String hid){
        String sql = "select * from lightdata where HARDWARE_hardwareID=? order by time desc";
        List<Map<String, Object>> list;
        try{
            list = jdbcTemplate.queryForList(sql,hid);
        }catch (org.springframework.dao.EmptyResultDataAccessException e){
            list = null;
        }
        Map<String, Object> map;
        if (list!=null){
            map = list.get(0);
        }else {
            map = null;
        }
        return map;
    }

    public boolean checkIdUnique(String dataID){
        boolean isUnique=true;//表示生成的id没有出现过
        String sql = "select HARDWARE_hardwareID from lightdata where dataID=?";
        String hid = "";
        try{
            hid = jdbcTemplate.queryForObject(sql,String.class,dataID);
        }catch (org.springframework.dao.EmptyResultDataAccessException e){
            hid = null;
        }
        if (hid!=null){
            isUnique=false;
        }
        return isUnique;
    }

    public String generateDataId(){
        String dataID="";
        do {
            for(int i=0;i<11;i++) {
                Integer j=(int)(Math.random()*11)%10;
                dataID+=j.toString();
            }
        } while (!this.checkIdUnique(dataID));
        return dataID;
    }

    public String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}