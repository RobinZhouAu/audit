package com.zhb.bean;

import com.zhb.core.ObjectBase;

import java.util.Map;

/**
 * Created by zhouhaibin on 2016/9/19.
 */
public class User extends ObjectBase {
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_HANGUP = 1;//挂起

    int status = STATUS_NORMAL;
    String departmentId;
    String roleIds = "";
    String password = EMPTY_OBJECT;
    String contact;

    Map userPrivileges;

    public boolean hasPrivilege(String privilegeId) {
        if (userPrivileges == null)
            return false;
        return userPrivileges.containsKey(privilegeId);
    }

    public Map getUserPrivileges() {
        return userPrivileges;
    }

    public void setUserPrivileges(Map userPrivileges) {
        this.userPrivileges = userPrivileges;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {
        User user = (User)object;
        contact = user.getContact();
        departmentId = user.getDepartmentId();
        roleIds = user.getRoleIds();
        name = user.getName();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
