package com.zhb.bean;

import com.zhb.core.ObjectBase;

/**
 * Created by zhouhaibin on 2016/9/19.
 */
public class Module extends ObjectBase {
    public static final String MODULE_ID_PROJECT_DESCRIPTION = "90";
    String tableId = EMPTY_OBJECT;
    int multipleRecord = 0;

    public int getMultipleRecord() {
        return multipleRecord;
    }

    public void setMultipleRecord(int multipleRecord) {
        this.multipleRecord = multipleRecord;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }
}
