package com.group.smarthome.utils;

public class SessionCheck {
    private String isLogin;
    private String username;

    public SessionCheck() {
    }

    public SessionCheck(String isLogin, String username) {
        this.isLogin = isLogin;
        this.username = username;
    }

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "SessionCheck{"+isLogin+" "+username+"}";
    }
}
