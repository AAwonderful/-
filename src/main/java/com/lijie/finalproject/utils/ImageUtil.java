package com.lijie.finalproject.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author LiJie
 * @date 2020/4/23-15:20
 */
@Component
public class ImageUtil {

    //返回图片相对路径
    public String getImageFilePath(HttpServletRequest request,MultipartFile image_update,String address){
        //设定文件上传的目标目录
        String fileName=image_update.getOriginalFilename();
        return address+'/'+fileName;
    }




    /**
     *头像删除
     * @param path  文件完整地址+文件名+格式
     * @return -1：文件不存在，1，删除成功；0，
     */
    public int deleteFile(HttpServletRequest request,String path,String address){
        String fileName="";
        if(path!=null){
            fileName=path.substring(path.indexOf("g") + 3);
        }
        String Path = request.getSession().getServletContext().getRealPath(address);
        int resultInfo=-1;
        if(fileName!=null&&Path!=null){
            File file = new File(Path,fileName);
            if (file.exists()) {//文件是否存在
                if (file.delete()) {//存在就删了，返回1
                    resultInfo = 1;
                } else {
                    resultInfo = 0;
                }
            }
        }
        return resultInfo;
    }

    /**
     * 上传文件
     * @param request  HttpServletRequest
     * @param image_update 前端的文件传值
     * @param address 文件上传的目标地址
     */
    public void insertFile(HttpServletRequest request, MultipartFile image_update,String address){
        //使用fileUpload组件完成文件上传
        //设定文件上传的目标目录
        String Path = request.getSession().getServletContext().getRealPath(address);
        //若目录不存在则创建文件上传的位置
        File file = new File(Path);
        if (!file.exists()){
            //不存在则创建该文件夹
            file.mkdirs();
        }
        //获取文件名称
        String fileName=image_update.getOriginalFilename();
        //给名称取唯一值

        System.out.println(fileName);
        //文件上传
        try {
            image_update.transferTo(new File(Path,fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("上传路径为"+Path);
    }


}
