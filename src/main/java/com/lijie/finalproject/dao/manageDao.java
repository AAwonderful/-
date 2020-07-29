package com.lijie.finalproject.dao;


import com.lijie.finalproject.bean.Classify;
import com.lijie.finalproject.bean.Manage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LiJie
 * @date 2020/4/20-19:41
 */
@Component
@Mapper
public interface manageDao {
    //   按管理员名查询管理员
    public Manage getManageByname(String managename);
    // 管理员增加分类
    void classifyInsert(String classifyName);
    //查询所有分类
    List<Classify> getClassify();
    //删除分类
    void classifyDelete(int classifyID);
}
