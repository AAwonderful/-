package com.lijie.finalproject.bean;

/**
 * @author LiJie
 * @date 2020/4/27-15:28
 */
public class Classify {
    private Integer classifyID;
    private String classifyName;

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

    @Override
    public String toString() {
        return "classify{" +
                "classifyID=" + classifyID +
                ", classifyName='" + classifyName + '\'' +
                '}';
    }
}
