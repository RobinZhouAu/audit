package com.zhb.bean.page;

import com.zhb.core.ObjectBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouhaibin on 2016/11/10.
 */
public class PageTable extends ObjectBase {
    String columnWidths;
    List<PageTableColumn> columns = new ArrayList<>();

    public String getColumnWidths() {
        return columnWidths;
    }

    public void setColumnWidths(String columnWidths) {
        this.columnWidths = columnWidths;
    }

    public List<PageTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<PageTableColumn> columns) {
        this.columns = columns;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }
}
