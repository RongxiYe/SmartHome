package com.group.smarthome.dao;

public interface HardwareDAO {
    public int queryInfo(String username);//search hardware information according to username
    public int queryHardId(String hid);//search if this hardware id exists
    public int queryHardFamily(String hid);//search if this hardware has binded with other family
    public int bindHardFamily(String hid,String fid);//bind operation
}
