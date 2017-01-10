package com.zhb.bean;

import com.zhb.core.ObjectBase;

import java.util.List;

/**
 * Created by zhouhaibin on 2016/9/27.
 * 任务模块对应的表结构
 */
public class Table extends ObjectBase {
    List<Field> fields;//字段列表

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }
}
