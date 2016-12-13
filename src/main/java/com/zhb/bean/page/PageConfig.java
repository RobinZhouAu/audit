package com.zhb.bean.page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouhaibin on 2016/11/10.
 */
public class PageConfig {
    Map<String, PageTable> tables = new HashMap<>();

    public Map<String, PageTable> getTables() {
        return tables;
    }

    public void setTables(Map<String, PageTable> tables) {
        this.tables = tables;
    }
}
