package com.zhb.controller;

import com.zhb.core.ObjectBase;
import com.zhb.manager.ObjectManager;
import com.zhb.service.ObjectService;
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
public class ObjectController extends ControllerBase {
    @javax.annotation.Resource(name="ObjectService")
    private ObjectService objectService;

    @RequestMapping("/loadObjectList")
    @ResponseBody
    public Map loadObjectList(HttpServletRequest request) {
        String objectClassName = ServletRequestUtils.getStringParameter(request, "objectClassName", null);
        String orderBy = ServletRequestUtils.getStringParameter(request, "orderBy", null);
        int start = ServletRequestUtils.getIntParameter(request, "start", 0);
        int limit = ServletRequestUtils.getIntParameter(request, "limit", 1);
        if (orderBy == null) {
            //获取默认的排序字段
        }
        List list = objectService.loadList(objectClassName, orderBy, start, limit);
        Map result = new HashMap();
        result.put("list", list);
        return result;
    }

    @RequestMapping("/loadObjectById")
    @ResponseBody
    public Map loadObjectById(HttpServletRequest request) throws ClassNotFoundException {
        String id = ServletRequestUtils.getStringParameter(request, "id", "-");
        String objectClassName = ServletRequestUtils.getStringParameter(request, "objectClassName", null);
        Class clazz = ObjectManager.getObjectClassByName(objectClassName);
        ObjectBase object = (ObjectBase)objectService.loadObjectById(id, clazz);
        Map result = new HashMap();
        result.put("object", object);
        return result;
    }


    @RequestMapping("/saveObject")
    @ResponseBody
    public Map saveObject(HttpServletRequest request) throws ClassNotFoundException {
        String objectClassName = ServletRequestUtils.getStringParameter(request, "objectClassName", null);
        Class clazz = ObjectManager.getObjectClassByName(objectClassName);
        ObjectBase object = (ObjectBase)getObjectParameter(request, "object", clazz);

        if (object != null) {
            objectService.saveObject(object, clazz);
        }
        Map result = new HashMap();
        result.put("result", "success");
        return result;
    }

    @RequestMapping("/deleteObject")
    @ResponseBody
    public Map deleteObject(HttpServletRequest request) throws ClassNotFoundException {
        String id = ServletRequestUtils.getStringParameter(request, "id", null);
        String objectClassName = ServletRequestUtils.getStringParameter(request, "objectClassName", null);
        Class clazz = ObjectManager.getObjectClassByName(objectClassName);
        if (id != null) {
            objectService.deleteObject(id, clazz);
        }
        Map result = new HashMap();
        result.put("result", "success");
        return result;
    }
}
