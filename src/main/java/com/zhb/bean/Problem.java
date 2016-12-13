package com.zhb.bean;

import com.zhb.core.ObjectBase;

/**
 * Created by zhouhaibin on 2016/9/29.
 */
public class Problem extends ObjectBase {
    String categoryId = EMPTY_OBJECT;
    String moduleId = EMPTY_OBJECT;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {
        Problem problem = (Problem)object;
        moduleId = problem.getModuleId();
        categoryId = problem.getCategoryId();
        name = problem.getName();
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
}
