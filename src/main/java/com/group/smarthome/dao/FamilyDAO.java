package com.group.smarthome.dao;

import com.group.smarthome.pojo.Family;
import com.group.smarthome.pojo.User;

import java.util.ArrayList;

public interface FamilyDAO {
    public Family queryFamilyInfo(String fid);//query familyid,address,postcode
    public ArrayList<User> queryFamilyMember(String fid);//query other family member
    public String generatefid();//generate unique fid
    public boolean checkdUnique(String fid);//check if fid is unique
    public String createFamilyInsert(String address,String postcode);//create an empty family;
    public String bindFamilyInsert(String fid,String username);//bind with an existing family
}
