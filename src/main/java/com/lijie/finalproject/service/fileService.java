package com.lijie.finalproject.service;

import com.lijie.finalproject.bean.File;
import com.lijie.finalproject.bean.file_user;
import com.lijie.finalproject.dao.fileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiJie
 * @date 2020/5/1-16:18
 */
@Service
public class fileService {
    @Autowired
    fileDao fileDao;
    public void fileInsert(File file){
        fileDao.fileInsert(file);
    }
    public void fileDelete(int fileID){
        fileDao.fileDelete(fileID);
    }
    public List<File> getFiles(){
        List<File> files = fileDao.getFiles();
        return files;
    }
    public File getFileByID(int fileID){
        return fileDao.getFileByID(fileID);
    }
    public void fileCheckUpdate(int check,int fileID){
        fileDao.fileCheckUpdate(check,fileID);
    }
    public List<File> getFilesPass(){
        List<File> files = fileDao.getFilesPass();
        return files;
    }
    public void updateLookNum(int fileID,int lookNum){
        int lookNum_1=lookNum+1;
        fileDao.updateLookNum(fileID,lookNum_1);
    }

    public void fileViewRecord_Insert(file_user Record){
        fileDao.fileViewRecord_Insert(Record);
    }
    public List<file_user> getfileViewRecordByfileID(int fileID){
        return fileDao.getfileViewRecordByfileID(fileID);
    }
    public  List<File> fileSelect(File file){
        return fileDao.fileSelect(file);
    }
}
