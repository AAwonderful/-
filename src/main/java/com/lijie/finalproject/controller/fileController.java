package com.lijie.finalproject.controller;


import com.lijie.finalproject.bean.Classify;
import com.lijie.finalproject.bean.File;
import com.lijie.finalproject.bean.file_user;
import com.lijie.finalproject.service.fileService;
import com.lijie.finalproject.service.manageService;
import com.lijie.finalproject.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author LiJie
 * @date 2020/4/30-15:49
 */
@Controller
public class fileController {
    @Autowired
    fileService fileService;
    @Autowired
    manageService mService;
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    //文件上传
    @RequestMapping(value = "/fileInsert",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> uploadImage(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile[] file, HttpSession session) throws Exception{
        resultMap.put("status", 400);
        //获得文件的分类
        int classifyID = Integer.parseInt(request.getParameter("classifyID"));

        if(file!=null&&file.length>0){
            //组合image名称，“;隔开”
            List<String> fileInsertResoult =new ArrayList<String>();
            PrintWriter out = null;
            try {
                for (int i = 0; i < file.length; i++) {
                    if (!file[i].isEmpty()) {
                        //返回值：1、文件原文件名2、文件类型3、文件随机名4、文件绝对路径
                        //
                         fileInsertResoult = FileUtil.fileInsert(request, "/upload/" + FileUtil.formateString(new Date()) + "/", file[i], true);
                        //将文件信息上传到数据库
                        String FileName = fileInsertResoult.get(0);
                        String type = fileInsertResoult.get(1);
                        String fileName_random = fileInsertResoult.get(2);
                        String location = fileInsertResoult.get(3);

                        //参数对照：int fileID, String fileName, String author, Date date, int looknum, int check, int classifyID, String location, String type
                        File fileInsert=new File(0,FileName,fileName_random,(String)session.getAttribute("loginName"),new Date(),0,0,classifyID,location,type);
                        fileService.fileInsert(fileInsert);
                    }
                }
                //上传成功
                if(fileInsertResoult!=null&&fileInsertResoult.size()>0){
                    System.out.println("上传成功！");
                    resultMap.put("status", 200);
                    resultMap.put("message", "上传成功！");
                }else {
                    resultMap.put("status", 500);
                    resultMap.put("message", "上传失败！文件格式错误！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("status", 500);
                resultMap.put("message", "上传异常！");
            }
        }else {
            resultMap.put("status", 500);
            resultMap.put("message", "没有检测到有效文件！");
        }
        return resultMap;
    }

    //文件列表
    @RequestMapping("/fileList")
    public String fileList(ModelMap map){
        List<File> files = fileService.getFiles();
        map.addAttribute("files",files);
        List<Classify> classifys = mService.getClassify();
        map.addAttribute("classifys",classifys);
        return "manage/fileInformation";
    }
    //文件删除
    @RequestMapping("/file_delete")
    public String file_delete(int fileID,ModelMap map){
        //删除文件
        File file=fileService.getFileByID(fileID);
        FileUtil.fileDelete(file);
        //删除数据库中文件信息
        fileService.fileDelete(fileID);
        List<File> files = fileService.getFiles();
        map.addAttribute("files",files);
        return "manage/fileInformation";
    }

    //文件下载
    @RequestMapping("/file_downLoad")
    public String file_downLoad(int fileID,HttpServletRequest request,HttpServletResponse response){
        File file=fileService.getFileByID(fileID);
        try {
            //文件下载
            synchronized (this){
                //文件下载
                FileUtil.fileDownload(file,response,request);
                //增加查看记录
                Timestamp createTime = new Timestamp(new Date().getTime());
                String  loginName =(String) request.getSession().getAttribute("loginName");
                file_user Record=new file_user(fileID,file.getFileName(),loginName,createTime);
                fileService.fileViewRecord_Insert(Record);
                //文件查看次数+1
                fileService.updateLookNum(fileID,file.getLooknum());
            }
            System.out.println("1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    //审核文件
    @RequestMapping("/file_check")
    public String file_check(int fileID,ModelMap map){
        File file=fileService.getFileByID(fileID);
        int fileCheck = FileUtil.fileCheck(file.getCheck());
        fileService.fileCheckUpdate(fileCheck,fileID);
        List<File> files = fileService.getFiles();
        map.addAttribute("files",files);
        return "manage/fileInformation";
    }
    //文件查看记录
    @RequestMapping("/fileViewRecord")
    public String fileViewRecord(int fileID,ModelMap map){
        List<file_user> file_users = fileService.getfileViewRecordByfileID(fileID);
        map.addAttribute("fileViewRecord",file_users);
        return "manage/fileViewRecord";
    }
    //文件查询
    @RequestMapping("/fileFind_manage")
    public String fileFind_manage( String fileName,String author,int classifyID,String type,int check, ModelMap map){
        //将查询的值分装到bean中
        File file=new File();
        file.setFileName('%'+fileName+'%');
        file.setAuthor('%'+author+'%');
        if(classifyID>0){
            file.setClassifyID(classifyID);
        }
        if(check>=0){
            file.setCheck(check);
        }
        file.setType(type);
        //查询符合条件的值
        List<File> files = fileService.fileSelect(file);
        map.addAttribute("files",files);
        List<Classify> classifys = mService.getClassify();
        map.addAttribute("classifys",classifys);
        return "manage/fileInformation";
    }
}


