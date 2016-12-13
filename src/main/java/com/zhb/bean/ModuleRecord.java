package com.zhb.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhb.core.ObjectBase;
import com.zhb.util.JsonTimestampWithTimeSerializer;
import net.sf.json.JSONObject;

import java.sql.Timestamp;

/**
 * Created by zhouhaibin on 2016/9/29.
 */
public class ModuleRecord extends ObjectBase {
    String taskId;
    String moduleId;
    String taskModuleId;
    String creatorId;
    Timestamp created;
    String content;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @JsonSerialize(using=JsonTimestampWithTimeSerializer.class)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getTaskModuleId() {
        return taskModuleId;
    }

    public void setTaskModuleId(String taskModuleId) {
        this.taskModuleId = taskModuleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {
        ModuleRecord moduleRecord = (ModuleRecord)object;
        content = moduleRecord.getContent();
    }

    public void fromJsonObject(JSONObject jsonObject) {
        id = jsonObject.getString("id");
//        name = jsonObject.getString("name");
        taskId = jsonObject.getString("taskId");
        moduleId = jsonObject.getString("moduleId");
        taskModuleId = jsonObject.getString("taskModuleId");
        creatorId = jsonObject.getString("creatorId");
        content = jsonObject.get("content").toString();
    }
}
