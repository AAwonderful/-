package com.lijie.finalproject.dao;

import com.lijie.finalproject.bean.File;
import com.lijie.finalproject.bean.file_user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author LiJie
 * @date 2020/5/1-11:35
 */
@Component
@Mapper
public interface fileDao {
    //文件相关信息添加
    void fileInsert(File file);
    //文件相关信息删除
    void fileDelete(int fileID);
    //查看所有文件相关信息
    List<File> getFiles();
    //查看已审核的文件信息
    List<File> getFilesPass();
    //根据文件ID获得文件信息
    File getFileByID(int fileID);
    /**更新文件的审核状态
     * @param check 更新的审核状态
     * @param fileID 要更新的文件ID
     */
    void fileCheckUpdate(int check,int fileID);
    //下载后添加查看次数
    void updateLookNum(int fileID,int lookNum);
    //记录文件的查看记录
    void fileViewRecord_Insert(file_user Record);
    //根据文件ID获得查看记录
    List<file_user> getfileViewRecordByfileID(int fileID);
    //查询文件
    List<File> fileSelect(File file);
}
