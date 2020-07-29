package com.lijie.finalproject.dao;

import com.lijie.finalproject.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LiJie
 * @date 2020/4/17-15:02
 */
@Component
@Mapper
public interface userDao {
//  查询所有用户
    List<User> getUser();
//   按用户名查询用户
    User getUserByname(String username);
//  修改学生信息
    void userUpdate(User user);
//   按用户ID查询用户
    User getUserByID(int userID);
//    按用户ID删除学生
    void userDelete(int userID);
//    添加用户
    void userInsert(User user);
}
