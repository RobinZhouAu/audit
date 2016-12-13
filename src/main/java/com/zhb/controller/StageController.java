package com.zhb.controller;

import com.zhb.bean.Stage;
import com.zhb.service.StageService;
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
public class StageController extends ControllerBase {
    @javax.annotation.Resource(name="StageService")
    private StageService stageService;

    @RequestMapping("/stage-manager")
    public String toStageManager(HttpServletRequest request) {
        return "stage-manager";
    }

    @RequestMapping("/loadStages")
    @ResponseBody
    public Map loadStages(HttpServletRequest request) {
        List list = stageService.loadStages();
        Map result = new HashMap();
        result.put("list", list);
        return result;
    }

    @RequestMapping("/addStage")
    @ResponseBody
    public Map addStage(HttpServletRequest request) {
        stageService.addStage();
        return successResult();
    }

    @RequestMapping("/editStage")
    @ResponseBody
    public Map editStage(HttpServletRequest request) {
        stageService.editStage();
        return successResult();
    }

    @RequestMapping("/deleteStage")
    @ResponseBody
    public Map deleteStage(HttpServletRequest request) {
        String id = ServletRequestUtils.getStringParameter(request, "id", null);
        stageService.deleteStage(id);
        return successResult();
    }

    @RequestMapping("/loadStage")
    @ResponseBody
    public Map loadStage(HttpServletRequest request) {
        String id = ServletRequestUtils.getStringParameter(request, "id", null);
        Stage stage = stageService.loadStage(id);
        Map result = new HashMap();
        result.put("item", stage);
        return result;
    }

}
