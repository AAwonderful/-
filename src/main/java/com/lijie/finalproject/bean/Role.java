package com.lijie.finalproject.bean;

import org.springframework.stereotype.Component;

/**
 * @author LiJie
 * @date 2020/4/22-15:11
 */
@Component
public class Role {
    private Integer roleID;
    private String roleName;

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
