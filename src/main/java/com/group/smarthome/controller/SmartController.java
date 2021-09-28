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
    public SessionCheck isLogin(HttpSession session){
        SessionCheck sessionCheck = new SessionCheck(null,null);
        if(session.getAttribute("username")==null) {
            sessionCheck.setIsLogin("false");
            sessionCheck.setUsername(null);
        }else{
            sessionCheck.setIsLogin("true");
            sessionCheck.setUsername((String)session.getAttribute("username"));
        }
        System.out.println(sessionCheck.toString());
        return sessionCheck;
    }


//    @RequestMapping("/login")
    @PostMapping("/login")
    public SessionCheck login(@RequestParam("userName") String username,
                        @RequestParam("password") String password,
                        HttpSession session, HttpServletResponse res) throws IOException {
//        @RequestBody Map<String,Object> map
        User user = new User();
        SessionCheck sessionCheck = new SessionCheck(null,null);
        user.setUserName(username);
        user.setPassword(password);
//        user.setUserName((String) map.get("userName"));
//        user.setPassword((String) map.get("password"));
        String result=userService.loginCheck(user);
        if (result.equals("No Such User! Please register!")){
            sessionCheck.setIsLogin("NoSuchUser");
            sessionCheck.setUsername(null);
        }else if(result.equals("Wrong Password!")){
            sessionCheck.setIsLogin("Wrong Password!");
            sessionCheck.setUsername(username);
//            sessionCheck.setUsername(user.getUserName());
        }else{
            session.setAttribute("username", username);
//            session.setAttribute("username", user.getUserName());
            sessionCheck.setIsLogin("true");
            sessionCheck.setUsername(username);
//            sessionCheck.setUsername(user.getUserName());
            res.sendRedirect("/index.html");//最后去掉
        }
        System.out.println(session.getAttribute("username"));
        System.out.println(sessionCheck);
        return sessionCheck;
    }


    @PostMapping("/register")
    public RegistCheck register(@RequestParam("userName") String username,
                         @RequestParam("password") String password,
                         @RequestParam("checkPass") String checkpass,
                         @RequestParam("userPhone") String phone,
                         HttpSession session, HttpServletResponse res) throws IOException {
//        @RequestBody Map<String,Object> map
        User user = new User();
        RegistCheck rc = new RegistCheck();
//        user.setUserName((String) map.get("userName"));
//        user.setPassword((String) map.get("password"));
//        user.setPhoneNum((String) map.get("userPhone"));
        user.setUserName(username);
        user.setPassword(password);
        user.setPhoneNum(phone);
        String result=userService.registerCheck(user);
//        (String) map.get("checkPass")
        if (!checkpass.equals(password)){
            rc.setPswCons("false");
            rc.setUserExist(null);
            rc.setRegisterState("false");
        }else if (result.equals("User name already exists!")){
            rc.setPswCons("true");
            rc.setUserExist("true");
            rc.setRegisterState("false");
        }else if(result.equals("Register failed!")){
            rc.setPswCons("true");
            rc.setUserExist("false");
            rc.setRegisterState("false");
        }else{
            rc.setPswCons("true");
            rc.setUserExist("false");
            rc.setRegisterState("true");
        }
        return rc;
    }


    @PostMapping("/user/changepsw")
    public Cpsw changePassword(@RequestParam("oldpsw") String oldpsw,
                               @RequestParam("newpsw") String newpsw,
                               HttpSession session){
//        @RequestBody Map<String,Object> map
        User user = new User();
        Cpsw cpsw = new Cpsw();
//        oldpsw = (String) map.get("oldpsw");
//        newpsw = (String) map.get("newpsw");
        if (oldpsw==null||newpsw==null||oldpsw.trim().equals("")||newpsw.trim().equals("")){
            cpsw.setCpswState("inputNull");
            cpsw.setIsCpswSuccess("false");
            return cpsw;
        }
        user.setUserName((String)session.getAttribute("username"));
        user.setPassword(oldpsw);
        String result = userService.loginCheck(user);
        if (!result.equals("Login Success!")){
            System.out.println("Wrong oldpsw!");
            cpsw.setIsCpswSuccess("false");
            cpsw.setCpswState("WrongPsw");
        }else {
            if (oldpsw.equals(newpsw)){
                cpsw.setIsCpswSuccess("false");
                cpsw.setCpswState("SamePsw");
                return cpsw;
            }
            user.setPassword(newpsw);
            String r = userService.changePswCheck(user);
            if (r.equals("Change password Success!")){
                System.out.println("Change password Success!");
                cpsw.setIsCpswSuccess("true");
                cpsw.setCpswState("Success");
            }else{
                System.out.println("Change password failed!");
                cpsw.setIsCpswSuccess("false");
                cpsw.setCpswState("UnknownError");
            }
        }
        return cpsw;
    }


    @PostMapping("/user/changephone")
    public Cph changePhone(@RequestParam("oldphone") String oldphone,
                           @RequestParam("newphone") String newphone,
                           HttpSession session) {
//        @RequestBody Map<String,Object> map
        User user = new User();
        Cph cph = new Cph();
//        oldphone = (String) map.get("oldphone");
//        newphone = (String) map.get("newphone");
        if (oldphone==null||newphone==null||oldphone.trim().equals("")||newphone.trim().equals("")){
            cph.setCphState("inputNull");
            cph.setIsCphSuccess("false");
            return cph;
        }
        user.setUserName((String) session.getAttribute("username"));
        user.setPassword(oldphone);
        user.setPhoneNum(newphone);
        String r = userService.changePhoneCheck(user);
        if (r.equals("Wrong old phone!")) {
            System.out.println("Wrong old phone!");
            cph.setCphState("WrongPhone");
            cph.setIsCphSuccess("false");
        } else if(oldphone.equals(newphone)){
            cph.setCphState("SamePhone");
            cph.setIsCphSuccess("false");
        }else if(r.equals("Change phone failed!")){
            System.out.println("Change phone failed!");
            cph.setCphState("UnknownError");
            cph.setIsCphSuccess("false");
        } else {
            System.out.println("Change phone Success!");
            cph.setCphState("Success");
            cph.setIsCphSuccess("true");
        }
        System.out.println(cph);
        return cph;
    }


    @GetMapping("/components/individuals.html")
    public Object personalInfo(HttpSession session){
        if (isLogin(session).getIsLogin().equals("false")){
            return isLogin(session);
        }
        User user = new User();
        user.setUserName((String)session.getAttribute("username"));
        user.setPhoneNum(userService.personalInfo(user));
        user.setPassword(null);
        user.setUserID(null);
        user.setFAMILY_familyID(null);
        return user;
    }

    @GetMapping("/components/family.html")
    public Object queryFamilyInfo(HttpSession session){
        if (isLogin(session).getIsLogin().equals("false")){
            return isLogin(session);
        }
        User user = new User();
        user.setUserName((String)session.getAttribute("username"));

        ArrayList<Object> al = userService.queryFamilyInfo(user);
        return al;
    }

    @PostMapping("/user/createFamily")
    public CBFamily createFamily(@RequestParam("address") String address,
                               @RequestParam("postcode") String postcode,
                               HttpSession session){
//        @RequestBody Map<String,Object> map
        User user = new User();
        Family family = new Family();
        CBFamily cb = new CBFamily();
//        address = (String) map.get("address");
//        postcode = (String) map.get("postcode");
        if (address==null||postcode==null||address.trim().equals("")||postcode.trim().equals("")){
            cb.setState("InputNull");
            cb.setIsSuccess("false");
            return cb;
        }
        user.setUserName((String) session.getAttribute("username"));
        family.setAddress(address);
        family.setPostcode(postcode);
        String r = userService.createFamily(user,family);
        if (r.equals("Create Success!")){
            cb.setState("Success");
            cb.setIsSuccess("true");
        }else{
            cb.setState("UnknownError");
            cb.setIsSuccess("false");
        }
        return cb;

    }

    @PostMapping("/user/bindFamily")
    public CBFamily bindFamily(@RequestParam("familyID") String fid, HttpSession session){
//        @RequestBody Map<String,Object> map
        User user = new User();
        Family family = new Family();
        CBFamily cb = new CBFamily();
//        fid = (String) map.get("familyID");
        if (fid==null||fid.trim().equals("")){
            cb.setState("InputNull");
            cb.setIsSuccess("false");
            return cb;
        }
        user.setUserName((String) session.getAttribute("username"));
        family.setFamilyID(fid);
        String r = userService.bindFamily(user,family);
        if (r.equals("Wrong FamilyID!")){
            cb.setState("WrongFid");
            cb.setIsSuccess("false");
        }else if (r.equals("Bind Success!")){
            cb.setState("Success");
            cb.setIsSuccess("true");
        }else{
            cb.setState("UnknownError");
            cb.setIsSuccess("false");
        }
        return cb;
    }

    @GetMapping("/components/console")
}
