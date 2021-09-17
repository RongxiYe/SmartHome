package com.group.smarthome.controller;

import com.group.smarthome.pojo.Family;
import com.group.smarthome.pojo.User;
import com.group.smarthome.service.Impl.UserServiceImpl;
import com.group.smarthome.utils.RegistCheck;
import com.group.smarthome.utils.SessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


@RestController
public class SmartController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public SessionCheck isLogin(HttpSession session){
        SessionCheck sessionCheck = new SessionCheck(null,null);
        if(session.getAttribute("username")==null) {
            sessionCheck.setIsLogin("false");
            sessionCheck.setUsername(null);
        }else{
            sessionCheck.setIsLogin("true");
            sessionCheck.setUsername((String)session.getAttribute("username"));
        }
        return sessionCheck;
    }

    @PostMapping("/login")
    public SessionCheck login(@RequestParam("userName") String username,
                        @RequestParam("password") String password,
                        HttpSession session, HttpServletResponse res) throws IOException {
        User user = new User();
        SessionCheck sessionCheck = new SessionCheck(null,null);
        user.setUserName(username);
        user.setPassword(password);
        String result=userService.loginCheck(user);
        if (result.equals("No Such User! Please register!")){
            sessionCheck.setIsLogin("NoSuchUser");
            sessionCheck.setUsername(null);
        }else if(result.equals("Wrong Password!")){
            sessionCheck.setIsLogin("Wrong Password!");
            sessionCheck.setUsername(username);
        }else{
            session.setAttribute("username", username);
            sessionCheck.setIsLogin("true");
            sessionCheck.setUsername(username);
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
        User user = new User();
        RegistCheck rc = new RegistCheck();
        user.setUserName(username);
        user.setPassword(password);
        user.setPhoneNum(phone);
        String result=userService.registerCheck(user);
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

    @RequestMapping("/user/changepsw")
    public void changePassword(@RequestParam("oldpsw") String oldpsw,
                               @RequestParam("newpsw") String newpsw,
                               HttpSession session){
        //先调login查psw，再调change,最后再对比
        User user = new User();
        user.setUserName((String)session.getAttribute("username"));
        user.setPassword(oldpsw);
        String result = userService.loginCheck(user);
        if (!result.equals("Login Success!")){
            System.out.println("Wrong oldpsw!");
        }else {
            user.setPassword(newpsw);
            String r = userService.changePswCheck(user);
            if (r.equals("Change password Success!")){
                System.out.println("Change password Success!");
            }else{
                System.out.println("Change password failed!");
            }
        }
    }


    @RequestMapping("/user/changephone")
    public void changePhone(@RequestParam("oldphone") String oldphone,
                               @RequestParam("newphone") String newphone,
                               HttpSession session) {
        User user = new User();
        user.setUserName((String) session.getAttribute("username"));
        user.setPassword(oldphone);
        user.setPhoneNum(newphone);
        String r = userService.changePhoneCheck(user);
        if (r.equals("Wrong old phone!")) {
            System.out.println("Wrong old phone!");
        } else if(r.equals("Change phone failed!")){
            System.out.println("Change phone failed!");
        } else {
            System.out.println("Change phone Success!");
        }
    }

//    @GetMapping("/views/account.html")
    public void queryInfo(HttpSession session){
        User user = new User();
        user.setUserName((String)session.getAttribute("username"));
        ArrayList<Object> arraylist = userService.queryInfo(user);
        System.out.println("username: "+user.getUserName());
        System.out.println("phone: "+(String)arraylist.get(0));
        System.out.println("Family information:");
        Family family = (Family)arraylist.get(1);
        System.out.println("address: "+family.getAddress());
        System.out.println("postcode: "+family.getPostcode());

    }
}
