package com.group.smarthome.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface HardwareDAO {
    public List<Map<String, Object>> queryInfo(String username);//search hardware information according to username
    public List<Map<String, Object>> queryData(String hid, String cid);
    public int queryHardId(String hid);//search if this hardware id exists
    public int queryHardFamily(String hid);//search if this hardware has binded with other family
    public int bindHardFamily(String hid,String fid);//bind operation
    public String queryMName(String mid);
    public int queryHardNum(String username);
}
