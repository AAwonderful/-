package com.lijie.finalproject.service;

import com.lijie.finalproject.bean.Classify;
import com.lijie.finalproject.bean.Role;
import com.lijie.finalproject.bean.User;
import com.lijie.finalproject.dao.manageDao;
import com.lijie.finalproject.dao.roleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiJie
 * @date 2020/4/22-16:19
 */
@Service
public class manageService {
    @Autowired
    roleDao roleDao;
    @Autowired
    manageDao manageDao;

    public List<User> userRoles(){
        return roleDao.userRole();
    }
    //获得全部role
    public List<Role> getRoles(){
        return roleDao.getRoles();
    }
    //role删除
    public int rloeDelete(String roleName){
        return roleDao.roleDelete(roleName);
    }
    //role增加
    public void roleInsert(String roleName){
        roleDao.roleInsert(roleName);
    }
    //按userID 获得用户+角色
    public User getUserAndRole(int userID){
        User userAndRole = roleDao.getUserAndRole(userID);
        return userAndRole;
    }
    /**
     *从用户角色中删除角色
     * @param userID
     * @param roleID
     * @return 0:无删除，1删除
     */
    public int userRoleDelete(int userID,int roleID){
        int i = roleDao.userRoleDelete(userID, roleID);
        return i;
    }
    //从用户角色中添加角色
    public void userRoleInsert(int userID,int roleID){
        int check=1;
        User userAndRole = roleDao.getUserAndRole(userID);
        if(userAndRole!=null){
            for(Role role:userAndRole.getRoles()){
                if(role.getRoleID()==roleID){
                    check=0;
                    break;
                }
            }
        }
        //防止添加重复的角色
        if(check==1){
            roleDao.userRoleInsert(userID,roleID);
        }
    }
    //查询所有文件分类
    public List<Classify> getClassify(){
        return manageDao.getClassify();
    }
    //增加文件分类
    public void classifyInsert(String classifyName){
        manageDao.classifyInsert(classifyName);
    }
    //删除文件分类
    public void classifyDelete(int classifyID){
        manageDao.classifyDelete(classifyID);
    }


}
