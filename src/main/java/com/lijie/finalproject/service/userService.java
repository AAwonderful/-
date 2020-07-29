package com.lijie.finalproject.service;

import com.lijie.finalproject.bean.User;
import com.lijie.finalproject.dao.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiJie
 * @date 2020/4/20-10:46
 */
@Service
public class userService {
    @Autowired
    userDao dao;
    //添加用户
    public void userInsert(User user){
        dao.userInsert(user);
    }
    //查询所有用户
    public List<User> getUser(){
       return dao.getUser();
    }
    //修该用户信息
    public void  userUpdate(User user){
        dao.userUpdate(user);
    }
    //通过username获得用户信息
    public User getUserByName(String username){
        User userByname = dao.getUserByname(username);
        return userByname;
    }
    //通过userID获得用户信息
    public User getUserByID(int userID){
        User userByID = dao.getUserByID(userID);
        return userByID;
    }
    //通过userID删除用户信息
    public void userDelete(int userID){
        dao.userDelete(userID);
    }
}
