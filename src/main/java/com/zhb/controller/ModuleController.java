package com.zhb.controller;

import com.zhb.bean.Module;
import com.zhb.bean.Table;
import com.zhb.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouhaibin on 2016/7/20.
 */
@Controller
public class ModuleController extends ControllerBase {
    @javax.annotation.Resource(name="ModuleService")
    private ModuleService moduleService;

    @RequestMapping("/module-manager")
    public String toModuleManager(HttpServletRequest request) {
        return "module-manager";
    }

    @RequestMapping("/loadModules")
    @ResponseBody
    public Map loadModules(HttpServletRequest request) {
        List list = moduleService.loadModules();
        Map result = new HashMap();
        result.put("list", list);
        return result;
    }

    @RequestMapping("/addModule")
    @ResponseBody
    public Map addModule(HttpServletRequest request) {
        moduleService.addModule();
        return successResult();
    }

    @RequestMapping("/editModule")
    @ResponseBody
    public Map editModule(HttpServletRequest request) {
        moduleService.editModule();
        return successResult();
    }

    @RequestMapping("/deleteModule")
    @ResponseBody
    public Map deleteModule(HttpServletRequest request) {
        String id = ServletRequestUtils.getStringParameter(request, "id", null);
        moduleService.deleteModule(id);
        return successResult();
    }

    @RequestMapping("/loadModule")
    @ResponseBody
    public Map loadModule(HttpServletRequest request) {
        String id = ServletRequestUtils.getStringParameter(request, "id", null);
        Module module = moduleService.loadModule(id);
        Map result = new HashMap();
        result.put("item", module);
        return result;
    }

    @RequestMapping("/loadModuleTable")
    @ResponseBody
    public Map loadModuleTable(HttpServletRequest request) {
        String moduleId = getStringParameter(request, "moduleId");
        Module module = moduleService.loadModule(moduleId);
        Table table = moduleService.loadModuleTable(moduleId);
        Map result = new HashMap();
        result.put("table", table);
        result.put("module", module);
        return result;
    }

}
