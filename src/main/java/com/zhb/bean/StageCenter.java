package com.zhb.bean;

import com.zhb.core.ObjectBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouhaibin on 2016/9/19.
 */
public class StageCenter extends Center {
    String centerId;
    String leaderId = EMPTY_OBJECT;
    List<String> memberIdList = new ArrayList<>();
    int canceled = 0;

    public int getCanceled() {
        return canceled;
    }

    public void setCanceled(int canceled) {
        this.canceled = canceled;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public List<String> getMemberIdList() {
        return memberIdList;
    }

    public void setMemberIdList(List<String> memberIdList) {
        this.memberIdList = memberIdList;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }
}
