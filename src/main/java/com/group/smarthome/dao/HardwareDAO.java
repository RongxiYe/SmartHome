package com.group.smarthome.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface HardwareDAO {
    public List<Map<String, Object>> queryInfo(String username);//search hardware information according to username
    public List<Map<String, Object>> queryData(String hid, String cid);
    public int queryHardId(String hid);//search if this hardware id exists,1->exist
    public int queryHardFamily(String hid);//search if this hardware has bound with other family,1->has been bound
    public String bindHardFamily(String hid,String fid);//bind operation
    public String queryMName(String mid);
    public int queryHardNum(String username);
    public void updateLight(String hid,String open,String brightness);
    public Map<String, Object> queryLight(String hid);
    public boolean checkIdUnique(String dataID);
    public String generateDataId();
    public String getTime();
}
