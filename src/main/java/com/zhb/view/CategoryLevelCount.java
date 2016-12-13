package com.zhb.view;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouhaibin on 2016/10/7.
 */
public class CategoryLevelCount {
    Map<String, Integer> levelCountMap = new HashMap<>();
    String centerType = "";

    public String getCenterType() {
        return centerType;
    }

    public void setCenterType(String centerType) {
        this.centerType = centerType;
    }

    public void addCount(String level) {
        Integer count = levelCountMap.get(level);
        if  (count == null) {
            levelCountMap.put(level, 1);
        } else {
            levelCountMap.put(level, count + 1);
        }
    }

    public void addCenterType(String centerType) {
        if (this.centerType == null ||  this.centerType.isEmpty())
            this.centerType = centerType;
        else {
            if (this.centerType.indexOf(centerType) >= 0)
                return;
            this.centerType = this.centerType + "," + centerType;
        }
    }

    public Map<String, Integer> getLevelCountMap() {
        return levelCountMap;
    }

    public void setLevelCountMap(Map<String, Integer> levelCountMap) {
        this.levelCountMap = levelCountMap;
    }
}
