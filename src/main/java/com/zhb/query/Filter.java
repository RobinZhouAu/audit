package com.zhb.query;

import com.zhb.core.ObjectBase;

/**
 * Created by zhouhaibin on 2016/9/21.
 */
public class Filter extends ObjectBase {
    @Override
    public void copyForUpdate(ObjectBase object) {

    }

    public Filter(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
