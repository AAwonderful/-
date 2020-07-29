package com.lijie.finalproject.dao;

import com.lijie.finalproject.bean.Role;
import com.lijie.finalproject.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LiJie
 * @date 2020/4/22-15:31
 */
@Component
@Mapper
public interface roleDao {
    //获得每个user的角色
    List<User> userRole();
    //添加角色
    void roleInsert(String roleName);
    //更具角色名删除角色
    int roleDelete(String roleName);
    //获得全部角色
    List<Role> getRoles();
    //根据userID 获得该用户信息+角色信息
    User getUserAndRole(int userID);

    //从用户角色中删除角色
    int userRoleDelete(int userID ,int roleID);

    //从用户角色中添加角色
    void userRoleInsert(int userID ,int roleID);
}
