package com.coderwzt.controller;

import com.coderwzt.model.Userinfo;
import com.coderwzt.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private IUserinfoService userinfoService;

    @RequestMapping("/loginIn")
    @ResponseBody
    public Map loginIn(Userinfo userinfo, HttpServletRequest request){
        Map map=new HashMap();
        // 获取服务器的session
        HttpSession session=request.getSession();
        if(session==null){
            map.put("code",404);
            map.put("msg","登录超时了");
            return map;
        }
        // 查询用户名和密码是否正确
        Userinfo user=userinfoService.queryUserByNameAndPwd(userinfo);
        // 没有从数据库找到该用户，返回一个错误提示
        if(user==null){
            map.put("code",404);
            map.put("msg","用户名或者密码错误");
            return map;
        }else{
            // 将用户信息给存储到session中，后面可以从session获取用户
            session.setAttribute("user",user);
            map.put("code",200);
            map.put("user",user);
            map.put("username",user.getUsername());
            return map;
        }

    }


    /**
     * 退出功能
     */
    @RequestMapping("/loginOut")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取session
        HttpSession session=request.getSession();
        // 清空数据
        session.invalidate();
        // 重定向会登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}
