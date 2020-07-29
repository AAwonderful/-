package com.lijie.finalproject.utils;

/**
 * @author LiJie
 * @date 2020/4/30-15:36
 */
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * lijie
 */
public class FileUtil {
    /**
     *
     * @param request
     * @param path_deposit 新增目录名 支持多级不存在目录
     * @param file 待文件
     * @param isRandomName 是否要基于文件名称重新编排名称
     * @return  List<String> 1、文件原文件名2、文件类型3、文件绝对路径
     */
    public static  List<String> fileInsert(HttpServletRequest request, String path_deposit, MultipartFile file, boolean isRandomName) {
        //返回的文件信息，用于存储到数据库。
        //1、文件原文件名2、文件类型3、文件的随机名称4、文件绝对路径
        List<String> resoult=new ArrayList<>();
        //上传
        try {
            String[] typeImg={"gif","png","jpg","doc","docx","pdf","xls","xlsx","ppt","pptx"};

            if(file!=null){
                String origName=file.getOriginalFilename();// 文件原名称
                System.out.println("上传的文件原名称:"+origName);
                //文件原文件名
                resoult.add(origName);
                // 判断文件类型
                String type=origName.indexOf(".")!=-1?origName.substring(origName.lastIndexOf(".")+1, origName.length()):null;
                if (type!=null) {
                    boolean booIsType=false;
                    for (int i = 0; i < typeImg.length; i++) {
                        if (typeImg[i].equals(type.toLowerCase())) {
                            booIsType=true;
                            //文件的类型
                            resoult.add(type);
                        }
                    }
                    //类型正确
                    if (booIsType) {
                        //存放图片文件的路径
                        String path="E:\\j2ee_finalProject\\file";
                        System.out.print("文件上传的路径"+path);
                        //组合名称
                        String fileSrc = path+path_deposit;
                        //是否随机名称
                        if(isRandomName){
                            //随机名规则：文件名+当前日期+8位随机数+文件后缀名
                            origName=origName.substring(0,origName.lastIndexOf("."))+formateString(new Date())+
                                    MathUtil.getRandom620(4)+origName.substring(origName.lastIndexOf("."));
                            //文件的随机名称
                            resoult.add(origName);
                        }
                        System.out.println("随机文件名："+origName);
                        //判断是否存在目录
                        File targetFile=new File(fileSrc,origName);
                        if(!targetFile.exists()){
                            targetFile.getParentFile().mkdirs();//创建目录
                        }

                        //上传
                        file.transferTo(targetFile);
                        //完整路径
                        System.out.println("完整路径:"+targetFile.getAbsolutePath());
                        //保存文件的绝对路径
                        resoult.add(targetFile.getAbsolutePath());

                    }
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return resoult;
    }

    /**
     * 格式化日期并去掉”-“
     * @param date
     * @return
     */
    public static String formateString(Date date){
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        String list[] = dateFormater.format(date).split("-");
        String result = "";
        for (int i=0;i<list.length;i++) {
            result += list[i];
        }
        return result;
    }

    /**
     * 下载文件
     * @param
     * @param response
     */
    public static void fileDownload(com.lijie.finalproject.bean.File fileDownload, HttpServletResponse response, HttpServletRequest request)
    throws IOException {
        File file=new File(fileDownload.getLocation());
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源已被删除！！");
            return;
        }
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=\"" +new String(fileDownload.getFileName_random().getBytes("utf-8"),"ISO8859-1")+"\"");
        //读取要下载的文件，保存到文件输入流
        try {
            FileInputStream in = new FileInputStream(fileDownload.getLocation());
            //创建输出流
            OutputStream out = response.getOutputStream();
             //创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区当中
            while((len=in.read(buffer))>0){
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
                }
            //关闭文件输入流
            in.close();
            //关闭输出流
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件删除
     * @param fileDelete
     */
    public static void fileDelete(com.lijie.finalproject.bean.File fileDelete){
        File deleteFile = new File(fileDelete.getLocation());
        if (deleteFile.exists() && deleteFile.isFile()){
               deleteFile.delete();
        }
    }
    //文件的审核状态
    public static int fileCheck(int checkID){
        if(checkID==0){
            checkID=1;
        }else if(checkID==1){
            checkID=0;
        }
        return checkID;
    }

}
