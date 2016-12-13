package com.zhb.bean;

import com.zhb.core.ObjectBase;

/**
 * Created by zhouhaibin on 2016/9/19.
 */
public class Role extends ObjectBase {
//    String description;
    String privilegeIds = "";

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(String privilegeIds) {
        this.privilegeIds = privilegeIds;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {
        Role role = (Role)object;
        name = role.getName();
        privilegeIds = role.getPrivilegeIds();
    }
}
