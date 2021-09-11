package com.group.smarthome.controller;

import com.group.smarthome.pojo.Family;
import com.group.smarthome.pojo.User;
import com.group.smarthome.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


@Controller
public class SmartController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public void login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session, HttpServletResponse res) throws IOException {
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        String result=userService.loginCheck(user);
        if (result.equals("Login Success!")){
            session.setAttribute("username", username);
            res.sendRedirect("/index.html");
        }else{
            res.sendRedirect("/login.html");
        }
        System.out.println(session.getAttribute("username"));
    }

    @RequestMapping("/register")
    public void register(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("phone") String phone,
                         HttpSession session, HttpServletResponse res) throws IOException {
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setPhoneNum(phone);
        String result=userService.registerCheck(user);
        if (result.equals("Register Success!")){
            session.setAttribute("username", username);
            res.sendRedirect("/login.html");
        }else{
            res.sendRedirect("/register.html");
        }
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
