package com.lijie.finalproject.controller;

import com.lijie.finalproject.bean.User;
import com.lijie.finalproject.service.userService;
import com.lijie.finalproject.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiJie
 * @date 2020/4/20-10:37
 */
@Controller
public class UserController {
    @Autowired
    userService userService;
    @Autowired
    ImageUtil fileDao;

    //修改用户信息
    @RequestMapping("/userUpdate")
    public String userUpdate(String username, ModelMap map){
        User userByName = userService.getUserByName(username);
        map.addAttribute("user",userByName);
        return "user/userUpdate";
    }

    @RequestMapping("/userUpdate2")
    public String userUpdate(User user, ModelMap map, MultipartFile image_update, HttpServletRequest request){
        //删除原头像
        if(image_update!=null){
            User deleteHeartImage = userService.getUserByID(user.getUserID());
            int I = fileDao.deleteFile(request, deleteHeartImage.getImage(), "heartImage");
        }
        //头像上传
        fileDao.insertFile(request,image_update,"heartImage");
        //获得上传头像的完整路径
        String Path=fileDao.getImageFilePath(request,image_update,"heartImage");
        //将头像相对路径信息存入对象user
        user.setImage(Path);
        userService.userUpdate(user);
        User userByID = userService.getUserByID(user.getUserID());
        map.addAttribute("user" ,userByID);
        return "dashboard";
    }
}
