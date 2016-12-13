package com.zhb.bean;

import com.zhb.core.ObjectBase;

/**
 * Created by zhouhaibin on 2016/11/3.
 */
public class City extends ObjectBase {
    String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }
}
