package com.zhb.bean;

import com.zhb.core.ObjectBase;

import java.util.List;

/**
 * Created by zhouhaibin on 2016/10/24.
 */
public class ScoreItem extends ObjectBase {
    List<String> questions;

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }
}
