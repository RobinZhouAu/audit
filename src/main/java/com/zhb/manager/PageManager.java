package com.zhb.manager;

import com.zhb.bean.page.PageConfig;
import com.zhb.bean.page.PageTable;
import com.zhb.core.ObjectBase;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouhaibin on 2016/11/10.
 */
public class PageManager {
    public static PageConfig pageConfig = new PageConfig();
    public static String pageConfigFileName = "c:\\audit-page-config.xml";

    private static <T> Map<String, T> initObjectMap(FileSystemXmlApplicationContext applicationContext, Class<T> clazz) {
        Map<String, T> objectMap = applicationContext.getBeansOfType(clazz);
        for (String id : objectMap.keySet()) {
            Object o = objectMap.get(id);
            ObjectBase object = (ObjectBase)o;
            object.setId(id);
        }
        return objectMap;
    }

    public static void reloadConfig() {
        File file = new File(pageConfigFileName);
        if (!file.exists())
            return;
        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(pageConfigFileName);
        Map<String, PageTable> tables = initObjectMap(applicationContext, PageTable.class);
//        Map<String, PageTableColumn> columns = initObjectMap(applicationContext, PageTableColumn.class);
        pageConfig.setTables(tables);
    }

    public static Map getPageTableConfig(String[] pageTableIdList) {
        Map map = new HashMap();
        for (String pageTableId : pageTableIdList) {
            PageTable pageTable = pageConfig.getTables().get(pageTableId);
            map.put(pageTable.getId(), pageTable);
        }
        return map;
    }

    public static void main(String args[]) {
        PageManager.reloadConfig();
    }
}
