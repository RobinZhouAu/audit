package com.zhb.service;

import com.zhb.bean.Module;
import com.zhb.bean.Table;
import com.zhb.core.ObjectBase;
import com.zhb.manager.MemoryCache;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhouhaibin on 2016/7/20.
 */

@Service("ModuleService")
public class ModuleService extends ServiceBase {
    public List loadModules() {
        List list = MemoryCache.getObjectList(Module.class);
        return list;
    }

    public void addModule() {
    }

    public void editModule() {
    }

    public void deleteModule(String id) {
    }

    public Module loadModule(String id) {
        return (Module)MemoryCache.getObject(Module.class, id);
    }

    public Table loadModuleTable(String moduleId) {
        Module module = (Module)MemoryCache.getObject(Module.class, moduleId);
        if (module.getTableId().equals(ObjectBase.EMPTY_OBJECT))
            return null;
        Table table = (Table)MemoryCache.getObject(Table.class, module.getTableId());
        return table;
    }

}
