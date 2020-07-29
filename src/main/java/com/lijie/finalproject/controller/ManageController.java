package com.lijie.finalproject.controller;

import com.lijie.finalproject.bean.Classify;
import com.lijie.finalproject.bean.Role;
import com.lijie.finalproject.bean.User;
import com.lijie.finalproject.service.manageService;
import com.lijie.finalproject.service.userService;
import com.lijie.finalproject.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiJie
 * @date 2020/4/22-9:54
 */
@Controller
public class ManageController {
    @Autowired
    userService uService;
    @Autowired
    manageService mService;
    @Autowired
    ImageUtil fileDao;

    @RequestMapping("/userList")
    public String userList(ModelMap map){
        List<User> users = uService.getUser();
        map.addAttribute("users",users);
        List<Role> roles = mService.getRoles();
        map.addAttribute("roles",roles);
        return "dashboard_manage";
    }
    //用户添加
    @RequestMapping("/userInsert")
    public String userInsert(String username,String password,int roleID ,ModelMap map){
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        uService.userInsert(user);
        User userByName = uService.getUserByName(username);
        mService.userRoleInsert(userByName.getUserID(),roleID);
        List<User> users = uService.getUser();
        List<Role> roles = mService.getRoles();
        map.addAttribute("roles",roles);
        map.addAttribute("users",users);
        return "dashboard_manage";

    }
    @RequestMapping("/userMessage")
    public String userMessage(int userID, ModelMap map){
        User userByID = uService.getUserByID(userID);
        map.addAttribute("user",userByID);
        return "manage/userMessage";
    }

    //修改用户信息页面
    @RequestMapping("/userUpdate_manage")
    public String userUpdate_manage(int userID, ModelMap map){
        User userByID  = uService.getUserByID(userID);
        map.addAttribute("user",userByID);
        return "manage/userUpdate_manage";
    }
    //修改用户信息
    @RequestMapping("/userUpdate_manage2")
    public String userUpdate(User user, ModelMap map, MultipartFile image_update, HttpServletRequest request){
        //若头像修改了、删除原头像
        if(image_update!=null){
            User deleteHeartImage = uService.getUserByID(user.getUserID());
            int I = fileDao.deleteFile(request, deleteHeartImage.getImage(), "heartImage");
        }
        //头像上传
        fileDao.insertFile(request,image_update,"heartImage");
        //获得上传头像的完整路径
        String Path=fileDao.getImageFilePath(request,image_update,"heartImage");
        //将头像相对路径信息存入对象user
        user.setImage(Path);
        uService.userUpdate(user);
        User userByID = uService.getUserByID(user.getUserID());
        map.addAttribute("user" ,userByID);

        return "manage/userMessage";
    }

    //管理员删除用户信息
    @RequestMapping("/userDelete_manage")
    public String userDelete_manage(int userID,ModelMap map){
        uService.userDelete(userID);
        List<User> users = uService.getUser();
        map.addAttribute("users",users);
        return "dashboard_manage";

    }

    //用户角色界面，显示出所有user的角色
    @RequestMapping("/roleManage")
    public String roleManage(ModelMap map){
        List<User> userRoles = mService.userRoles();
        map.addAttribute("userRoles",userRoles);
        return "manage/userRole_manage";
    }

    //role的CRUD
    @RequestMapping("/role")
    public String role(ModelMap map){
        List<Role> roles = mService.getRoles();
        map.addAttribute("roles",roles);
        return "manage/role_CRUD";
    }

    //添加角色，并返回到角色界面
    @RequestMapping("/role_insert")
    public  String role_insert(String roleName,ModelMap map){
        if(roleName!=null){
        //避免出现重复角色
        List<Role> check = mService.getRoles();
        //遍历角色，当角色名与添加的角色名不一致时，添加该角色名
        int insert=1;//insert为1时添加角色
        for(Role role:check){
            String checkRoleName=role.getRoleName();
            if(roleName.equals(checkRoleName)){
                insert=0;
                break;
            }
        }
        if(insert==1){
            mService.roleInsert(roleName);
        }
        List<Role> roles = mService.getRoles();
        map.addAttribute("roles",roles);
        }
        return "manage/role_CRUD";
    }
    //删除角色，并返回到角色界面
    @RequestMapping("/role_delete")
    public  String role_delete(String roleName,ModelMap map){
        mService.rloeDelete(roleName);
        List<Role> roles = mService.getRoles();
        map.addAttribute("roles",roles);
        return "manage/role_CRUD";
    }

    //管理用户角色，跳转到用户角色修改界面userRoleUpdate
    @RequestMapping("/userRoleUpdate")
    public String userRoleUpdate(int userID,ModelMap map){
        User userAndRole = mService.getUserAndRole(userID);
        //获得全部role，供用户添加角色时下拉菜单使用
        List<Role> roles = mService.getRoles();
        map.addAttribute("roles",roles);
        map.addAttribute("user",userAndRole);
        return "manage/userRoleUpdate";
    }
    //从用户的角色中删除角色
    @RequestMapping("/userRoleDelete")
    public String userRoleUpdate(int userID,int roleID,ModelMap map){
        mService.userRoleDelete(userID, roleID);
        User userAndRole = mService.getUserAndRole(userID);
        //获得全部role，供用户添加角色时下拉菜单使用
        List<Role> roles = mService.getRoles();
        map.addAttribute("roles",roles);
        map.addAttribute("user",userAndRole);
        return "manage/userRoleUpdate";
    }
    //从用户的角色中添加角色
    @RequestMapping("/userRoleInsert")
    public String userRoleInsert(int userID,int roleID,ModelMap map){

        mService.userRoleInsert(userID,roleID);
        User userAndRole = mService.getUserAndRole(userID);
        //获得全部role，供用户添加角色时下拉菜单使用
        List<Role> roles = mService.getRoles();
        map.addAttribute("roles",roles);
        map.addAttribute("user",userAndRole);
        return "manage/userRoleUpdate";
    }

    @RequestMapping("/file")
    public String file(ModelMap map){
        //查出文件的分类供上传时使用
        List<Classify> classifys = mService.getClassify();
        map.addAttribute("classifys",classifys);
        return "manage/fileList_manage";
    }
    //管理员管理文件的分类classify_manage
    @RequestMapping("/classify_manage")
    public String classify_manage(ModelMap map){
        List<Classify> classifys = mService.getClassify();
        map.addAttribute("classifys",classifys);
        return "manage/classifyList";
    }
    //管理员添加文件分类
    @RequestMapping("/classify_insert")
    public String classify_insert(String classifyName,ModelMap map){
        if(classifyName!=null){
            //避免出现重复分类
            List<Classify> classifys = mService.getClassify();
            //遍历分类，当分类名与添加的分类不一致时，添加该角色名
            int insert=1;//insert为1时添加角色
            for(Classify classify:classifys){
                String checkClassifyName=classify.getClassifyName();
                if(classifyName.equals(checkClassifyName)){
                    insert=0;
                    break;
                }
            }
            if(insert==1){
                mService.classifyInsert(classifyName);
            }else {
                map.addAttribute("error","该分类已存在");
            }
            List<Classify> resoult = mService.getClassify();
            map.addAttribute("classifys",resoult);
        }
        return "manage/classifyList";
    }
    //管理员删除文件分类
    @RequestMapping("classify_delete")
    public String clasify_delete(int classifyID,ModelMap map){
        if(classifyID!=0){
            mService.classifyDelete(classifyID);
        }
        List<Classify> classifys = mService.getClassify();
        map.addAttribute("classifys",classifys);
        return "manage/classifyList";

    }
}
