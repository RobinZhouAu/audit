package com.zhb.controller;

import com.zhb.bean.Role;
import com.zhb.bean.User;
import com.zhb.manager.MemoryCache;
import com.zhb.query.QueryResult;
import com.zhb.service.DepartmentService;
import com.zhb.service.ProjectService;
import com.zhb.service.UserService;
import com.zhb.util.PageUtil;
import com.zhb.view.ObjectView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouhaibin on 2016/7/20.
 */
@Controller
public class UserController extends ControllerBase {
    @javax.annotation.Resource(name="UserService")
    private UserService userService;

    @javax.annotation.Resource(name="DepartmentService")
    private DepartmentService departmentService;

    @javax.annotation.Resource(name="ProjectService")
    private ProjectService projectService;

    @RequestMapping("/toUserManager")
    public String toUserManager(HttpServletRequest request) {
        Map globalValues = new HashMap();
        List allDepartments = departmentService.loadAllDepartments(false);
        globalValues.put("allDepartments", allDepartments);
//        globalValues.put("allDepartments", MemoryCache.getObjectList(Department.class));
        List allUsers = userService.loadAllUsers();
        globalValues.put("allUsers", allUsers);
        List allRoles = MemoryCache.getObjectList(Role.class);
        List roleViewList = new ArrayList();
        for (int i = 0; i < allRoles.size(); i ++) {
            Role role = (Role)allRoles.get(i);
            ObjectView view = new ObjectView(role);
            roleViewList.add(view);
        }
        globalValues.put("allRoles", roleViewList);
        request.setAttribute(PageUtil.GLOBAL_VALUES, globalValues);
        return "/jsp/user-manager";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        return "/jsp/login";
    }

    @RequestMapping("/toModifyPassword")
    public String toModifyPassword(HttpServletRequest request) {
        return "/jsp/modify-password";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_ID);
        request.getSession().removeAttribute(USER_NAME);
        request.getSession().removeAttribute(USER_PRIVILEGES);
        return "/jsp/login";
    }

    @RequestMapping("/loadUsers")
    @ResponseBody
    public Map loadUsers(HttpServletRequest request) {
        int start = getIntParameter(request, "start");
        int limit = getIntParameter(request, "limit");
        String keywords = getStringParameter(request, "keywords");
        QueryResult queryResult = userService.loadUsers(start, limit, keywords);
        Map result = new HashMap();
        result.put("result", queryResult);
        return result;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Map addUser(HttpServletRequest request) throws Exception {
        User user = (User)getObjectParameter(request, "user", User.class);
        if (userService.getUser(user.getId()) != null) {
            return errorResult("该用户编号已经存在");
        }
        userService.addUser(user);
        String successMessage = String.format("用户[%s]已添加成功，系统默认密码[%s]", user.getName(), UserService.DEFAULT_PASSWORD);
        Map result = new HashMap();
        result.put("successMessage", successMessage);
        return result;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Map updateUser(HttpServletRequest request) {
        User user = (User)getObjectParameter(request, "user", User.class);
        userService.updateUser(user);
        return successResult();
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map deleteUser(HttpServletRequest request) {
        String id = getStringParameter(request, "id");
        userService.deleteUser(id);
        return successResult();
    }

    @RequestMapping("/stopUser")
    @ResponseBody
    public Map stopUser(HttpServletRequest request) {
        String id = getStringParameter(request, "id");
        userService.stopUser(id);
        return successResult();
    }

    @RequestMapping("/startUser")
    @ResponseBody
    public Map startUser(HttpServletRequest request) {
        String id = getStringParameter(request, "id");
        userService.startUser(id);
        return successResult();
    }

    @RequestMapping("/loadUser")
    @ResponseBody
    public Map loadUser(HttpServletRequest request) {
        String id = ServletRequestUtils.getStringParameter(request, "id", null);
        User user = userService.getUser(id);
        Map result = new HashMap();
        result.put("item", user);
        return result;
    }

    @RequestMapping("/userLogin")
    @ResponseBody
    public Map userLogin(HttpServletRequest request) {
        String userId = getStringParameter(request, "userId");
        String pw = getStringParameter(request, "pw");
        String message = userService.userLogin(userId, pw);
        Map result = new HashMap();
        if (message != null) {
            result.put("loginMessage", message);
            result.put("result", false);
            return result;
        }
        //login success
        request.getSession().setAttribute(USER_ID, userId);
        User user = (User)MemoryCache.getObject(User.class, userId);
        request.getSession().setAttribute(USER_NAME, user.getName());
        request.getSession().setAttribute(USER_PRIVILEGES, user.getUserPrivileges());
        result.put("result", true);
        return result;
    }

    @RequestMapping("/loadUserResources")
    @ResponseBody
    public Map loadUserResources(HttpServletRequest request) {
        String id = getStringParameter(request, "id");
        List projects = userService.loadUserProjects(id);
        List centers = userService.loadUserCenters(id);
        Map result = new HashMap();
        result.put("projects", projects);
        result.put("centers", centers);
        return result;
    }

    @RequestMapping("/handoverProject")
    @ResponseBody
    public Map handoverProject(HttpServletRequest request) {
        String projectId = getStringParameter(request, "projectId");
        String fromUserId = getStringParameter(request, "fromUserId");
        String toUserId = getStringParameter(request, "toUserId");
        userService.handoverProject(projectId, fromUserId, toUserId);
        return successResult();
    }

    @RequestMapping("/handoverProjectCenter")
    @ResponseBody
    public Map handoverProjectCenter(HttpServletRequest request) {
        String projectId = getStringParameter(request, "projectId");
        String stageId = getStringParameter(request, "stageId");
        String taskId = getStringParameter(request, "taskId");
        String fromUserId = getStringParameter(request, "fromUserId");
        String toUserId = getStringParameter(request, "toUserId");
        userService.handoverProjectCenter(projectId, stageId, taskId, fromUserId, toUserId);
        return successResult();
    }

    @RequestMapping("/modifyPassword")
    @ResponseBody
    public Map modifyPassword(HttpServletRequest request) throws Exception {
        String oldPw = getStringParameter(request, "oldPw");
        String newPw = getStringParameter(request, "newPw");
        String newPw2 = getStringParameter(request, "newPw2");
        String userId = loadUserId(request);
        String errorMessage = userService.modifyPassword(userId, oldPw, newPw, newPw2);
        if (errorMessage != null)
            return errorResult(errorMessage);
        return successResult();
    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    public Map resetPassword(HttpServletRequest request) throws Exception {
        String id = getStringParameter(request, "id");
        String userId = loadUserId(request);
        userService.resetPassword(id);
        return successResult();
    }
}
