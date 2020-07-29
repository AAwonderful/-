package com.lijie.finalproject.controller;

import com.lijie.finalproject.bean.Manage;
import com.lijie.finalproject.bean.Role;
import com.lijie.finalproject.bean.User;
import com.lijie.finalproject.service.loginService;
import com.lijie.finalproject.service.manageService;
import com.lijie.finalproject.service.userService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author LiJie
 * @date 2020/4/17-14:54
 */
@Controller
public class login {
    @Autowired
    loginService service;
    @Autowired
    userService uService;
    @Autowired
    manageService mService;

    @RequestMapping("/")
    public String  index(){
        return "login";
    }

    @RequestMapping("/login_manage")
    public String  login_manage(){
        return "login_manage";
    }
    @RequestMapping("/login")
    public String  login(){
        return "login";
    }

    //国际化
    @RequestMapping("/LocaleResolver")
    public String Locale(){
        return "login";
    }

    //登录成功后跳转到主页面-----user
    @RequestMapping(value = "/home",method = RequestMethod.POST)
    public String dashboard(String username,String password, ModelMap map,HttpSession session){
        List list = service.UserLogin(username, password);
        String resoult=null;
        //通过返回的值判断账号密码正确性
        if((int)list.get(0)==1){
            resoult="login";
            map.addAttribute("error","该用户名未注册！");
        }else if ((int)list.get(0)==2){
            //登录成功
            session.setAttribute("loginName",username);
            User ResoultUser = (User)list.get(1);
            map.addAttribute("user",ResoultUser);
            resoult="dashboard";
        }else if((int)list.get(0)==3){
            resoult="login";
            map.addAttribute("error","账号密码错误！");
        }
        return resoult;
    }

    //登录成功后跳转到主页面-----manage
    @RequestMapping(value = "/managehome",method = RequestMethod.POST)
    public String managehome(String managename,String password, ModelMap map,HttpSession session){
        List list = service.ManageLogin(managename,password);
        String resoult=null;
        //通过返回的值判断账号密码正确性
        if((int)list.get(0)==1){
            resoult="login_manage";
            map.addAttribute("error","该管理员未注册！");
        }else if ((int)list.get(0)==2){
            //登录成功,并返回所有用户
            List<User> users = uService.getUser();
            map.addAttribute("users",users);
            List<Role> roles = mService.getRoles();
            map.addAttribute("roles",roles);
            session.setAttribute("loginName",managename);
            resoult="dashboard_manage";
        }else if((int)list.get(0)==3){
            resoult="login_manage";
            map.addAttribute("error","账号密码错误！");
        }
        return resoult;
    }
}
