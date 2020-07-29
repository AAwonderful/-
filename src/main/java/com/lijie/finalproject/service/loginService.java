package com.lijie.finalproject.service;

import com.lijie.finalproject.bean.Manage;
import com.lijie.finalproject.bean.User;
import com.lijie.finalproject.dao.manageDao;
import com.lijie.finalproject.dao.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LiJie
 * @date 2020/4/17-15:51
 */
@Service
public class loginService {
    @Autowired
    userDao dao;
    @Autowired
    manageDao MDao;

    /**
     *
     * @param username
     * @param password
     * @return 1:未注册，2：账号密码正确，3：账号密码错误
     */
    public List UserLogin(String username, String password){
        int check=-1;
        List resoult=new ArrayList();
        //通过用户名查询用户
        User checkuser = dao.getUserByname(username);
        //判断得到的user是否为空
        if(checkuser!=null){
            //已注册，判断账号密码
            if(password.equals(checkuser.getPassword())){
                check=2;
                resoult.add(check);
                resoult.add(checkuser);
            }else {
                check=3;
                resoult.add(check);
            }
        }else {
            //未注册
            check=1;
            resoult.add(check);
        }
        return resoult;
    }

    /**
     *
     * @param managename
     * @param password
     * @return 1:不是管理员  2：账号密码正确，3：账号密码错误
     */
    public List ManageLogin(String managename, String password){
        int check=-1;
        List resoult=new ArrayList();
        //通过用户名查询用户
        Manage checkmanage = MDao.getManageByname(managename);
        //判断得到的user是否为空
        if(checkmanage!=null){
            //已注册，判断账号密码
            if(password.equals(checkmanage.getPassword())){
                check=2;
                resoult.add(check);
                resoult.add(checkmanage);
            }else {
                check=3;
                resoult.add(check);
            }
        }else {
            //未注册
            check=1;
            resoult.add(check);
        }
        return resoult;
    }
}
