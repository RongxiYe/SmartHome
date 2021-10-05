package com.group.smarthome.controller;

import com.group.smarthome.pojo.Family;
import com.group.smarthome.pojo.User;
import com.group.smarthome.service.Impl.UserServiceImpl;
import com.group.smarthome.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


@RestController
public class SmartController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }


//    @GetMapping("/components/*")
//    @GetMapping(value={"/views/*"})
    public JsonResult isLogin(HttpSession session){
        SessionCheck sessionCheck = new SessionCheck(null,null);
        if(session.getAttribute("username")==null) {
            sessionCheck.setIsLogin("false");
            sessionCheck.setUsername(null);
            return JsonResult.isError(1001,sessionCheck,"NOT_LOG_IN");
        }else{
            sessionCheck.setIsLogin("true");
            sessionCheck.setUsername((String)session.getAttribute("username"));
            System.out.println(sessionCheck.toString());
            return JsonResult.isOk(sessionCheck);
        }
    }


//    @RequestMapping("/login")
    @PostMapping("/login")
    public JsonResult login(@RequestBody Map<String,Object> map,
                        HttpSession session, HttpServletResponse res) throws IOException {
        User user = new User();
        SessionCheck sessionCheck = new SessionCheck(null,null);
        String username = (String) map.get("userName");
        String password = (String) map.get("password");
        user.setUserName(username);
        user.setPassword(password);
        String result=userService.loginCheck(user);
        if (result.equals("No Such User! Please register!")){
            sessionCheck.setIsLogin("NoSuchUser");
            sessionCheck.setUsername(null);

            System.out.println(session.getAttribute("username"));//debug
            System.out.println(sessionCheck);

            return JsonResult.isError(1002,sessionCheck,"NO_SUCH_USER");
        }else if(result.equals("Wrong Password!")){
            sessionCheck.setIsLogin("Wrong Password!");
            sessionCheck.setUsername(username);

            System.out.println(session.getAttribute("username"));//debug
            System.out.println(sessionCheck);

            return JsonResult.isError(1003,sessionCheck,"WRONG_PASSWORD");
        }else{
            session.setAttribute("username", username);
            sessionCheck.setIsLogin("true");
            sessionCheck.setUsername(username);

            System.out.println(session.getAttribute("username"));//debug
            System.out.println(sessionCheck);

            return JsonResult.isOk(sessionCheck);
//            res.sendRedirect("/index.html");
        }

//        return sessionCheck;
    }


    @PostMapping("/register")
    public JsonResult register(@RequestBody Map<String,Object> map,
                         HttpSession session, HttpServletResponse res) throws IOException {
//        @RequestBody Map<String,Object> map
        User user = new User();
        RegistCheck rc = new RegistCheck();
        String username = (String) map.get("userName");
        String password = (String) map.get("password");
        String phone = (String) map.get("userPhone");
        String checkPass = (String) map.get("checkPass");
        user.setUserName(username);
        user.setPassword(password);
        user.setPhoneNum(phone);

        String result=userService.registerCheck(user);
//
        if (!checkPass.equals(password)){
            rc.setPswCons("false");
            rc.setUserExist(null);
            rc.setRegisterState("false");
            return JsonResult.isError(2001,rc,"DIFFERENT_PASSWORD");
        }else if (result.equals("User name already exists!")){
            rc.setPswCons("true");
            rc.setUserExist("true");
            rc.setRegisterState("false");
            return JsonResult.isError(2002,rc,"USER_NAME_EXIST");
        }else if(result.equals("Register failed!")){
            rc.setPswCons("true");
            rc.setUserExist("false");
            rc.setRegisterState("false");
            return JsonResult.isError(2003,rc,"UNKNOWN_ERROR");
        }else{
            rc.setPswCons("true");
            rc.setUserExist("false");
            rc.setRegisterState("true");
            return JsonResult.isOk(rc);
        }
    }


    @PostMapping("/user/changepsw")
    public JsonResult changePassword(@RequestBody Map<String,Object> map,
                               HttpSession session){
//        @RequestBody Map<String,Object> map
        User user = new User();
        Cpsw cpsw = new Cpsw();
        String oldpsw = (String) map.get("oldpsw");
        String newpsw = (String) map.get("newpsw");
        if (oldpsw==null||newpsw==null||oldpsw.trim().equals("")||newpsw.trim().equals("")){
            cpsw.setCpswState("inputNull");
            cpsw.setIsCpswSuccess("false");
            return JsonResult.isError(3001,cpsw,"INPUT_NULL");
        }
        user.setUserName((String)session.getAttribute("username"));
        user.setPassword(oldpsw);
        String result = userService.loginCheck(user);
        if (!result.equals("Login Success!")){
            System.out.println("Wrong oldpsw!");
            cpsw.setIsCpswSuccess("false");
            cpsw.setCpswState("WrongPsw");
            return JsonResult.isError(3002,cpsw,"WRONG_PASSWORD");
        }else {
            if (oldpsw.equals(newpsw)){
                cpsw.setIsCpswSuccess("false");
                cpsw.setCpswState("SamePsw");
                return JsonResult.isError(3003,cpsw,"SAME_PASSWORD");
            }
            user.setPassword(newpsw);
            String r = userService.changePswCheck(user);
            if (r.equals("Change password Success!")){
                System.out.println("Change password Success!");
                cpsw.setIsCpswSuccess("true");
                cpsw.setCpswState("Success");
                return JsonResult.isOk(cpsw);
            }else{
                System.out.println("Change password failed!");
                cpsw.setIsCpswSuccess("false");
                cpsw.setCpswState("UnknownError");
                return JsonResult.isError(3004,cpsw,"UNKNOWN_ERROR");
            }
        }
    }


    @PostMapping("/user/changephone")
    public JsonResult changePhone(@RequestBody Map<String,Object> map,
                           HttpSession session) {
//        @RequestBody Map<String,Object> map
        User user = new User();
        Cph cph = new Cph();
        String oldphone = (String) map.get("oldphone");
        String newphone = (String) map.get("newphone");
        if (oldphone==null||newphone==null||oldphone.trim().equals("")||newphone.trim().equals("")){
            cph.setCphState("inputNull");
            cph.setIsCphSuccess("false");
            return JsonResult.isError(4001,cph,"INPUT_NULL");
        }
        user.setUserName((String) session.getAttribute("username"));
        user.setPassword(oldphone);
        user.setPhoneNum(newphone);
        String r = userService.changePhoneCheck(user);
        if (r.equals("Wrong old phone!")) {
            System.out.println("Wrong old phone!");
            cph.setCphState("WrongPhone");
            cph.setIsCphSuccess("false");
            return JsonResult.isError(4002,cph,"WRONG_PHONE");
        } else if(oldphone.equals(newphone)){
            cph.setCphState("SamePhone");
            cph.setIsCphSuccess("false");
            return JsonResult.isError(4003,cph,"SAME_PHONE");
        }else if(r.equals("Change phone failed!")){
            System.out.println("Change phone failed!");
            cph.setCphState("UnknownError");
            cph.setIsCphSuccess("false");
            return JsonResult.isError(4004,cph,"UNKNOWN_ERROR");
        } else {
            System.out.println("Change phone Success!");
            cph.setCphState("Success");
            cph.setIsCphSuccess("true");
            System.out.println(cph);
            return JsonResult.isOk(cph);
        }
    }


    @GetMapping("/components/individuals.html")
    public JsonResult personalInfo(HttpSession session){
        if (isLogin(session).getCode()==1001){
            return isLogin(session);
        }
        User user = new User();
        user.setUserName((String)session.getAttribute("username"));
        user.setPhoneNum(userService.personalInfo(user));
        user.setPassword(null);
        user.setUserID(null);
        user.setFAMILY_familyID(null);
        return JsonResult.isOk(user);
    }

    @GetMapping("/components/family.html")
    public JsonResult queryFamilyInfo(HttpSession session){
        if (isLogin(session).getCode()==1001){
            return isLogin(session);
        }
        User user = new User();
        user.setUserName((String)session.getAttribute("username"));

        ArrayList<Object> al = userService.queryFamilyInfo(user);
        return JsonResult.isOk(al);
    }

    @PostMapping("/user/createFamily")
    public JsonResult createFamily(@RequestBody Map<String,Object> map,
                               HttpSession session){
//        @RequestBody Map<String,Object> map
        User user = new User();
        Family family = new Family();
        CBFamily cb = new CBFamily();
        String address = (String) map.get("address");
        String postcode = (String) map.get("postcode");
        if (address==null||postcode==null||address.trim().equals("")||postcode.trim().equals("")){
            cb.setState("InputNull");
            cb.setIsSuccess("false");
            return JsonResult.isError(5001,cb,"INPUT_NULL");
        }
        user.setUserName((String) session.getAttribute("username"));
        family.setAddress(address);
        family.setPostcode(postcode);
        String r = userService.createFamily(user,family);
        if (r.equals("Create Success!")){
            cb.setState("Success");
            cb.setIsSuccess("true");
            return JsonResult.isOk(cb);
        }else{
            cb.setState("UnknownError");
            cb.setIsSuccess("false");
            return JsonResult.isError(5002,cb,"UNKNOWN_ERROR");
        }
    }

    @PostMapping("/user/bindFamily")
    public JsonResult bindFamily(@RequestBody Map<String,Object> map, HttpSession session){
//        @RequestBody Map<String,Object> map
        User user = new User();
        Family family = new Family();
        CBFamily cb = new CBFamily();
        String fid = (String) map.get("familyID");
        if (fid==null||fid.trim().equals("")){
            cb.setState("InputNull");
            cb.setIsSuccess("false");
            return JsonResult.isError(5001,cb,"INPUT_NULL");
        }
        user.setUserName((String) session.getAttribute("username"));
        family.setFamilyID(fid);
        String r = userService.bindFamily(user,family);
        if (r.equals("Wrong FamilyID!")){
            cb.setState("WrongFid");
            cb.setIsSuccess("false");
            return JsonResult.isError(5003,cb,"WRONG_FAMILY_ID");
        }else if (r.equals("Bind Success!")){
            cb.setState("Success");
            cb.setIsSuccess("true");
            return JsonResult.isOk(cb);
        }else{
            cb.setState("UnknownError");
            cb.setIsSuccess("false");
            return JsonResult.isError(5002,cb,"UNKNOWN_ERROR");
        }
    }

    @GetMapping("/components/home")
    public JsonResult viewHardInfo(HttpSession session) {
        if (isLogin(session).getCode()==1001){
            return isLogin(session);
        }
        User user = new User();
        user.setUserName((String)session.getAttribute("username"));
        HardInfo hi = new HardInfo();
        ArrayList<Object> al = userService.queryFamilyInfo(user);
        if (al.get(0)==null){
            hi.setHasHardware("NoFamily");
            hi.setAbs(null);
            hi.setHardNum(0);
            return JsonResult.isError(6001,hi,"NO_FAMILY");
        }
        hi = userService.viewHardInfo(user,10);
        if (hi.getHardNum()==0){
            return JsonResult.isError(6002,hi,"NO_HARDWARE");
        }else{
            return JsonResult.isOk(hi);
        }
    }

    @PostMapping("/hard/add")
    public JsonResult addHardware(@RequestBody Map<String,Object> map,HttpSession session){
//        @RequestBody Map<String,Object> map
        String hardwareID = (String) map.get("hardwareID");
        AddHardState ahs = new AddHardState();
        if (hardwareID.trim().equals("")){
            ahs.setState("InputNull");
            return JsonResult.isError(7001,ahs,"INPUT_NULL");
        }else{
            String result = userService.addHardware((String) session.getAttribute("username"),hardwareID);
            ahs.setState(result);
            if (result.equals("NoSuchHardID")){
                return JsonResult.isError(7002,ahs,"NO_SUCH_HARD_ID");
            }else if (result.equals("hasOwner")){
                return JsonResult.isError(7003,ahs,"HAS_OWNER");
            }else if (result.equals("Success")){
                return JsonResult.isOk(ahs);
            }else{
                return JsonResult.isError(7004,ahs,"UNKNOWN_ERROR");
            }
        }
    }

    @GetMapping("/components/console")
    public JsonResult console(HttpSession session){
        if (isLogin(session).getCode()==1001){
            return isLogin(session);
        }
        User user = new User();
        user.setUserName((String)session.getAttribute("username"));
        HardInfo hi = new HardInfo();
        ArrayList<Object> al = userService.queryFamilyInfo(user);
        if (al.get(0)==null){
            hi.setHasHardware("NoFamily");
            hi.setAbs(null);
            hi.setHardNum(0);
            return JsonResult.isError(6001,hi,"NO_FAMILY");
        }else{
            hi = userService.viewHardInfo(user,1);
            if (hi.getHardNum()==0){
                return JsonResult.isError(6002,hi,"NO_HARDWARE");
            }else{
                return JsonResult.isOk(hi);
            }
        }
    }

    @PostMapping("hard/controlLight")
    public JsonResult controlLight(@RequestBody Map<String,Object> map,HttpSession session){
        String open = (String) map.get("open");
        String brightness = (String) map.get("brightness");
        String hid = (String) map.get("hardwareID");
        ControlLight cl = userService.controlLight(hid,open,brightness);
        if (cl.getIsSuccess().equals("true")){
            return JsonResult.isOk(cl);
        }else{
            return JsonResult.isError(8001,cl,"CHANGE_FAILED");
        }
    }
}
