package com.zhb.bean;

import com.zhb.core.ObjectBase;

/**
 * Created by zhouhaibin on 2016/10/24.
 */
public class Privilege extends ObjectBase {
    public static final int TYPE_PRIVILEGE = 0;
    public static final int TYPE_GROUP = 1;
    public static final String PRIVILEGE_ID_SYSTEM_ADMIN = "SYSTEM_ADMIN";
    public static final String PRIVILEGE_ID_BUSINESS_ADMIN = "BUSINESS_ADMIN";
    public static final String PRIVILEGE_ID_PRINT = "PRINT";
    public static final String PRIVILEGE_ID_CHECK = "CHECK";//报告评审
    public static final String PRIVILEGE_ID_CLASSIFY = "CLASSIFY";//报告分级
//    public static final String PRIVILEGE_ID_SYSTEM_CONFIG = "SYSTEM_CONFIG";//系统配置

    int type = TYPE_PRIVILEGE;
    String groupId = EMPTY_OBJECT;
    String description;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }
}
