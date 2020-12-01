package com.yanzhen.util;


import com.yanzhen.model.Userinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: xhh
 * @Date: 2020/11/30 19:06
 * @Version 1.0
 */

public class SessionUtils {

    public static Boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Userinfo userinfo= (Userinfo) session.getAttribute("user");

        if (userinfo != null) {
            return userinfo.getType().equals(1);
        }
        return false;

    }

    public static Userinfo getUserInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        Userinfo userinfo= (Userinfo) session.getAttribute("user");
        return userinfo;


    }

}
