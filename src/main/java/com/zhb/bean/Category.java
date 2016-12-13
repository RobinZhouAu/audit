package com.zhb.bean;

import com.zhb.core.ObjectBase;

/**
 * Created by zhouhaibin on 2016/9/29.
 */
public class Category extends ObjectBase {
    String moduleId;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {
        Category category = (Category)object;
        name = category.name;
        moduleId = category.moduleId;
    }
}
