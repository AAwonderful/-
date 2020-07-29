package com.lijie.finalproject.controller;

import com.lijie.finalproject.bean.Classify;
import com.lijie.finalproject.bean.File;
import com.lijie.finalproject.service.fileService;
import com.lijie.finalproject.service.manageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author LiJie
 * @date 2020/5/8-14:20
 */
@Controller
public class file_userController {
    @Autowired
    manageService mService;
    @Autowired
    fileService fileService;
    @RequestMapping("/file_user")
    public String file(ModelMap map){
        //查出文件的分类供上传时使用
        List<Classify> classifys = mService.getClassify();
        map.addAttribute("classifys",classifys);
        return "user/fileList_user";
    }
    //用户查看已审核的文件
    @RequestMapping("/fileList_user")
    public String fileList_user(ModelMap map){
        List<File> files = fileService.getFilesPass();
        map.addAttribute("files",files);
        List<Classify> classifys = mService.getClassify();
        map.addAttribute("classifys",classifys);
        return "user/fileInformation_user";
    }
    //查询文件
    @RequestMapping("/fileFind_user")
    public String fileFind_user( String fileName,String author,int classifyID,String type, ModelMap map){
        File file=new File();
        file.setFileName('%'+fileName+'%');
        file.setAuthor('%'+author+'%');
        if(classifyID>0){
            file.setClassifyID(classifyID);
        }
        file.setType(type);
        List<File> files = fileService.fileSelect(file);
        map.addAttribute("files",files);
        List<Classify> classifys = mService.getClassify();
        map.addAttribute("classifys",classifys);
        return "user/fileInformation_user";
    }
}
