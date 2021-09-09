package com.group.smarthome.controller;

import com.group.smarthome.pojo.User;
import com.group.smarthome.service.Impl.UserServiceImpl;
import com.group.smarthome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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


}
