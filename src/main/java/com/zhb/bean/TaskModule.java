package com.zhb.bean;

import com.zhb.core.ObjectBase;

/**
 * Created by zhouhaibin on 2016/9/27.
 */
public class TaskModule extends ObjectBase {
    String moduleId;

    public TaskModule() {

    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
}
