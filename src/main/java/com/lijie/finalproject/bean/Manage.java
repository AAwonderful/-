package com.lijie.finalproject.bean;

import org.springframework.stereotype.Component;

/**
 * @author LiJie
 * @date 2020/4/17-16:03
 */
@Component
public class Manage {
    private Integer manageID;
    private String manageName;
    private String password;

    public Manage() {
    }

    public Integer getManageID() {
        return manageID;
    }

    public void setManageID(Integer manageID) {
        this.manageID = manageID;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manage{" +
                "manageID=" + manageID +
                ", manageName='" + manageName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
