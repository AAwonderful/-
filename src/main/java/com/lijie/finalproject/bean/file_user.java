package com.lijie.finalproject.bean;

import java.sql.Timestamp;

/**文件查看记录类
 * @author LiJie
 * @date 2020/5/9-10:17
 */
public class file_user {
    private Integer fileID;
    private String fileName;
    private String userName;
    private Timestamp date;

    public file_user(int fileID, String fileName, String userName, Timestamp date) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.userName = userName;
        this.date = date;
    }

    public file_user() {
    }

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "file_user{" +
                "fileID=" + fileID +
                ", fileName='" + fileName + '\'' +
                ", userName='" + userName + '\'' +
                ", date=" + date +
                '}';
    }
}
