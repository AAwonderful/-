package com.lijie.finalproject.bean;

import java.util.Date;

/**
 * @author LiJie
 * @date 2020/5/1-11:31
 */
public class File {
    private Integer fileID;
    private String fileName;
    private String fileName_random;
    private String author;
    private Date date;
    private Integer looknum;
    private Integer check;
    private Integer classifyID;
    private String classifyName;
    private String location;
    private String type;

    public File() {
    }

    public File(int fileID, String fileName, String fileName_random, String author, Date date, int looknum, int check, int classifyID, String location, String type) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.fileName_random = fileName_random;
        this.author = author;
        this.date = date;
        this.looknum = looknum;
        this.check = check;
        this.classifyID = classifyID;
        this.location = location;
        this.type = type;
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

    public String getFileName_random() {
        return fileName_random;
    }

    public void setFileName_random(String fileName_random) {
        this.fileName_random = fileName_random;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLooknum() {
        return looknum;
    }

    public void setLooknum(Integer looknum) {
        this.looknum = looknum;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public Integer getClassifyID() {
        return classifyID;
    }

    public void setClassifyID(Integer classifyID) {
        this.classifyID = classifyID;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileID=" + fileID +
                ", fileName='" + fileName + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", looknum=" + looknum +
                ", check=" + check +
                ", classifyID=" + classifyID +
                ", classifyName='" + classifyName + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
