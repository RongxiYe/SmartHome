package com.group.smarthome.service.Impl;

import com.group.smarthome.dao.FamilyDAO;
import com.group.smarthome.dao.HardwareDAO;
import com.group.smarthome.dao.UserDAO;
import com.group.smarthome.pojo.Data;
import com.group.smarthome.pojo.Family;
import com.group.smarthome.pojo.LightData;
import com.group.smarthome.pojo.User;
import com.group.smarthome.utils.ControlLight;
import com.group.smarthome.utils.HardAbstract;
import com.group.smarthome.utils.HardInfo;
import com.group.smarthome.utils.MD5Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@Service
public class UserServiceImpl {

    private UserDAO userDao;
    private FamilyDAO familyDAO;
    private HardwareDAO hardwareDAO;

    @Autowired
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setFamilyDAO(FamilyDAO familyDAO) {
        this.familyDAO = familyDAO;
    }

    @Autowired
    public void setHardwareDAO(HardwareDAO hardwareDAO) {
        this.hardwareDAO = hardwareDAO;
    }

    //check the login state
    public String loginCheck(User user) {
        String psw = userDao.queryPassword(user.getUserName());
        String md5psw = MD5Crypto.encrypt(user.getPassword());
        System.out.println(psw + "  " + md5psw);
        System.out.println(psw.equals(md5psw));
        if (psw == null) {
            return "No Such User! Please register!";
        } else if (!psw.equals(md5psw)) {
            return "Wrong Password!";
        } else {
            return "Login Success!";
        }
    }

    public String registerCheck(User user) {
        String psw = userDao.queryPassword(user.getUserName());
        if (psw != null) {
            return "User name already exists!";
        } else {
            String md5psw = MD5Crypto.encrypt(user.getPassword());
            int result = userDao.registerInsert(user.getUserName(), md5psw, user.getPhoneNum());
            System.out.println(result);
            if (result == 1) {
                return "Register Success!";
            } else {
                return "Register failed!";
            }
        }
    }

    public String changePswCheck(User user) {
        String md5psw = MD5Crypto.encrypt(user.getPassword());
        String nowpsw = userDao.changePsw(user.getUserName(), md5psw);
        if (nowpsw.equals(md5psw)) {
            return "Change password Success!";
        } else {
            return "Change password failed!";
        }
    }

    public String changePhoneCheck(User user) {
        String phone = userDao.queryPhone(user.getUserName());
        if (!phone.equals(user.getPassword())) {
            return "Wrong old phone!";
        } else {
            String nowphone = userDao.changePhone(user.getUserName(), user.getPhoneNum());
            if (nowphone.equals(user.getPhoneNum())) {
                return "Change phone Success!";
            } else {
                return "Change phone failed!";
            }
        }
    }

    public String personalInfo(User user) {
        String phoneNum = userDao.queryPhone(user.getUserName());
        return phoneNum;
    }

    public ArrayList<Object> queryFamilyInfo(User user) {
        String fid = userDao.queryFamilyId(user.getUserName());
        System.out.println("fid: " + fid);
        ArrayList<Object> arraylist = new ArrayList<Object>();
        Family family = null;
        ArrayList<User> useral = null;
        if (fid == null) {
            family = null;
            arraylist.add(0, null);
        } else {
            family = familyDAO.queryFamilyInfo(fid);
            arraylist.add(0, family);
            useral = familyDAO.queryFamilyMember(fid);
            arraylist.add(1, useral);
        }
        System.out.println(family);
        System.out.println(arraylist);
        System.out.println(useral);
        return arraylist;
    }

    public String createFamily(User user, Family family) {
        String fid = familyDAO.createFamilyInsert(family.getAddress(), family.getPostcode());
        if (fid == null) {
            return "Create failed!";
        } else {
            familyDAO.bindFamilyInsert(fid, user.getUserName());
            String nowfid = userDao.queryFamilyId(user.getUserName());
            if (fid.equals(nowfid)) {
                return "Create Success!";
            } else {
                return "Create failed!";
            }
        }
    }

    public String bindFamily(User user, Family family) {
        boolean isunique = familyDAO.checkdUnique(family.getFamilyID());
        if (isunique == true) {
            return "Wrong FamilyID!";
        } else {
            familyDAO.bindFamilyInsert(family.getFamilyID(), user.getUserName());
            String nowfid = userDao.queryFamilyId(user.getUserName());
            if (family.getFamilyID().equals(nowfid)) {
                return "Bind Success!";
            } else {
                return "Bind failed!";
            }
        }
    }

    public HardInfo viewHardInfo(User user,int COUNT) {
        HardInfo hi = new HardInfo();
        ArrayList<HardAbstract> abs = new ArrayList<HardAbstract>();
        HardAbstract hab = new HardAbstract();
        Data data = new Data();
        List<Map<String, Object>> info = hardwareDAO.queryInfo(user.getUserName());
        if (info == null) {
            hi.setHasHardware("false");
            hi.setHardNum(0);
            hi.setAbs(null);
        } else {
            hi.setHasHardware("true");
            int num = hardwareDAO.queryHardNum(user.getUserName());
            hi.setHardNum(num);
            ListIterator<Map<String, Object>> lit = info.listIterator();
            while (lit.hasNext()) {
                Map<String, Object> hard = lit.next();
                hab.setHardwareID((String) hard.get("hardwareID"));
                hab.setName((String) hard.get("name"));
                hab.setHardType((String) hard.get("CATEGORY_categoryID"));
                hab.setManufactureName(hardwareDAO.queryMName((String) hard.get("MANUFACTURER_manufacturerID")));
                ArrayList<Data> hd = new ArrayList<Data>();
                Data d = new Data();
                List<Map<String, Object>> lm = hardwareDAO.queryData((String) hard.get("hardwareID"), (String) hard.get("CATEGORY_categoryID"));
                if (lm == null) {
                    hab.setHasData("false");
                    hab.setHd(null);
                } else {
                    ListIterator<Map<String, Object>> li = lm.listIterator();
                    int count = 0;
                    if (hab.getHardType().equals("001")) {
                        while (li.hasNext()&&count<=COUNT) {
                            LightData l = new LightData();
                            Map<String, Object> map = li.next();
                            l.setTime((String) map.get("time"));
                            l.setData((String) map.get("open"));
                            l.setBrightness((String) map.get("brightness"));
                            hd.add(l);
                            count++;
                        }
                    } else if (hab.getHardType().equals("002") || hab.getHardType().equals("003") || hab.getHardType().equals("004")) {
                        while (li.hasNext()&&count<=COUNT) {
                            Map<String, Object> map = li.next();
                            d.setTime((String) map.get("time"));
                            d.setData((String) map.get("movement"));
                            hd.add(d);
                            count++;
                        }
                    }else if(hab.getHardType().equals("005")){
                        while (li.hasNext()&&count<=COUNT) {
                            Map<String, Object> map = li.next();
                            d.setTime((String) map.get("time"));
                            d.setData((String) map.get("temperature"));
                            hd.add(d);
                            count++;
                        }
                    }else if(hab.getHardType().equals("006")){
                        while (li.hasNext()&&count<=COUNT) {
                            Map<String, Object> map = li.next();
                            d.setTime((String) map.get("time"));
                            d.setData((String) map.get("humidity"));
                            hd.add(d);
                            count++;
                        }
                    }else{
                        while (li.hasNext()&&count<=COUNT) {
                            Map<String, Object> map = li.next();
                            d.setTime((String) map.get("time"));
                            d.setData((String) map.get("smokeScope"));
                            hd.add(d);
                            count++;
                        }
                    }
                }
                hab.setHd(hd);
                abs.add(hab);
            }
            hi.setAbs(abs);
        }
        return hi;
    }

    public String addHardware(String username, String hid){
        String state = "";
        if (hardwareDAO.queryHardId(hid)==0){
            state = "NoSuchHardID";
        }else{
            if (hardwareDAO.queryHardFamily(hid)==1){
                state = "hasOwner";
            }else{
                if (userDao.queryFamilyId(username)==null){
                    state = "NoSuchHardID";
                }else{
                    String fid = userDao.queryFamilyId(username);
                    state = hardwareDAO.bindHardFamily(hid,fid);
                }
            }
        }
        return state;
    }

    public ControlLight controlLight(String hid,String open, String brightness){
        hardwareDAO.updateLight(hid,open,brightness);
        Map<String, Object> map = hardwareDAO.queryLight(hid);
        ControlLight cl = new ControlLight();
        cl.setIsSuccess("false");
        cl.setOpen("No Value");
        cl.setBrightness("No Value");
        if (map!=null){
            String nowopen = (String) map.get("open");
            String nowb = (String) map.get("brightness");
            if (nowopen.equals(open)&&nowb.equals(brightness)){
                cl.setIsSuccess("true");
                cl.setOpen(open);
                cl.setBrightness(brightness);
            }
        }
        return cl;
    }

}
