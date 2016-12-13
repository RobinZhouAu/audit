package com.zhb.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhb.core.ObjectBase;
import com.zhb.manager.MemoryCache;
import com.zhb.util.JsonTimestampWithTimeSerializer;
import net.sf.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by zhouhaibin on 2016/9/27.
 */
public class ModifyRecord extends ObjectBase {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
    public static final int OPERATION_UNKNOWN = -1;
    public static final int OPERATION_ADD = 0;
    public static final int OPERATION_UPDATE = 1;
    public static final int OPERATION_DELETE = 2;
    public static final int OPERATION_OPINION_ACCEPTED = 3;
    public static final int OPERATION_LEVEL_ACCEPTED = 4;

    public static final int TARGET_TYPE_MODULE_RECORD = 0;
    public static final int TARGET_TYPE_DISCOVERY = 1;
    public static final int TARGET_TYPE_CENTER_REPORT = 2;
    public static final int TARGET_TYPE_STAGE_REPORT = 3;

    public static final String TARGET_ID_OVERVIEW = "overview";
    public static final String TARGET_ID_PROBLEM_TYPE = "problem";
    public static final String TARGET_ID_REFERENCE = "reference";

    public static final String FIELD_NAME_OVERVIEW = "稽查概述";
    public static final String FIELD_NAME_PROBLEM = "问题归类";
    public static final String FIELD_NAME_REFERENCE = "依据";
    public static final String FIELD_NAME_DESCRIPTION = "问题简介";
    public static final String FIELD_NAME_LEVEL = "分级";
    public static final String FIELD_NAME_CATEGORY = "分类";
    public static final String FIELD_NAME_PATIENTNO = "受试者编号";
    public static final String FIELD_NAME_MEMO = "备注";
    public static final String FIELD_NAME_INREPORT = "入报告";

    String taskId = EMPTY_OBJECT;//稽查任务Id 或者 单中心报告Id 或者 项目阶段报告Id
    String moduleId = EMPTY_OBJECT;
    String targetId = EMPTY_OBJECT;//ModuleRecordId 或者 DiscoveryId
    int operation = OPERATION_UNKNOWN;
    Timestamp created;
    String userId = EMPTY_OBJECT;
    int targetType = TARGET_TYPE_MODULE_RECORD;
    String fieldName;
    String oldValue;
    String newValue;
    String projectId = EMPTY_OBJECT;
    String projectName;
    String stageName;
    String centerName;
    String patientNo;//受试者编号
    String categoryId = EMPTY_OBJECT;
    String fulltext;

    int index;

    public ModifyRecord() {
        id = generateID();
        created = new Timestamp(System.currentTimeMillis());
    }

    public ModifyRecord(String fieldName, String oldValue, String newValue) {
        id = generateID();
        created = new Timestamp(System.currentTimeMillis());
        this.fieldName = fieldName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int type) {
        this.operation = type;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String objectId) {
        this.targetId = objectId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @JsonSerialize(using= JsonTimestampWithTimeSerializer.class)
    public Timestamp getCreated() {
        return created;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }

    public void updateMetadata(ModuleRecord moduleRecord, Task task) {
        taskId = task.getId();
        moduleId = moduleRecord.getModuleId();
        targetId = moduleRecord.getId();
        projectId = task.getProjectId();
        projectName = task.getProjectName();
        stageName = task.getStageName();
        centerName = task.getCenterName();

        targetType = TARGET_TYPE_MODULE_RECORD;
    }

    public void updateMetadata(Discovery discovery, Task task) {
        taskId = task.getId();
        targetId = discovery.getCode();
        projectId = task.getProjectId();
        projectName = task.getProjectName();
        stageName = task.getStageName();
        centerName = task.getCenterName();
        patientNo = discovery.getPatientNo();
        categoryId = discovery.getCategoryId();

        targetType = TARGET_TYPE_DISCOVERY;
    }

    public void updateMetadata(Discovery discovery, ReportBase report) {
        taskId = report.getId();
        targetId = discovery.getCode();
        projectId = report.getProjectId();
        projectName = report.getProjectName();
        stageName = report.getStageName();
        Center center = (Center)MemoryCache.getObject(Center.class, discovery.getCenterId());
        centerName = center.getName();
        patientNo = discovery.getPatientNo();
        categoryId = discovery.getCategoryId();

        targetType = TARGET_TYPE_DISCOVERY;
    }

    public void updateMetadata(ReportBase report) {
        taskId = report.getId();
        projectId = report.getProjectId();
        projectName = report.getProjectName();
        stageName = report.getStageName();
        if (report instanceof CenterReport) {
            centerName = report.getCenterName();
            targetType = TARGET_TYPE_CENTER_REPORT;
        } else {
            centerName = "";
            targetType = TARGET_TYPE_STAGE_REPORT;
        }
    }

    public void buildFulltext() {
        StringBuffer sb = new StringBuffer();
        sb.append(projectName).append(",");
        sb.append(centerName).append(",");
        sb.append(targetId).append(",");
        sb.append(patientNo);
        fulltext = sb.toString();
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    //把被删除的依据的内容，放到oldValue里面
    public void updateValueByReference(String referenceName) {
        oldValue = "依据：" + referenceName;
    }

    //把被删除的发现的内容，放到oldValue里面
    public void updateValueByDicovery(Discovery discovery) {
        StringBuffer sb = new StringBuffer();
        sb.append("编号：").append(discovery.getCode()).append(";\n");
        sb.append("受试者编号：").append(discovery.getPatientNo()).append(";\n");
        sb.append("分级：").append(discovery.getLevel()).append(";\n");
        sb.append("分类：").append(MemoryCache.getObjectName(Category.class, discovery.getCategoryId())).append(";\n");
        sb.append("问题描述：").append(discovery.getDescription()).append(";\n");
        sb.append("问题归类：").append(MemoryCache.getObjectName(Problem.class, discovery.getProblemId())).append(";\n");
        sb.append("备注：").append(discovery.getMemo()).append(";\n");
        sb.append("创建者：").append(MemoryCache.getUserName(discovery.getCreatorId())).append(";\n");
        sb.append("修改者：").append(MemoryCache.getUserName(discovery.getEditorId())).append(";\n");
        sb.append("入报告：").append(discovery.getInReport() == 1 ?  "是" : "否").append(";\n");
        sb.append("创建时间：").append(formatTime(discovery.getCreated())).append(";\n");
        sb.append("修改时间：").append(formatTime(discovery.getCreated())).append(";\n");
        oldValue = sb.toString();
    }

    private String formatTime(Timestamp time) {
        if (time == null)
            return "";
        return simpleDateFormat.format(time);
    }

    //把被删除的模块记录的内容，放到oldValue里面
    public void updateValueByModuleRecord(ModuleRecord moduleRecord) {
        StringBuffer sb = new StringBuffer();
        Module module = (Module)MemoryCache.getObject(Module.class, moduleRecord.getModuleId());
        if (module == null)
            return;
        Table table = (Table)MemoryCache.getObject(Table.class, module.getTableId());
        if (table == null)
            return;
        JSONObject jsonObject = JSONObject.fromObject(moduleRecord.getContent());
        for (Field field : table.getFields()) {
            Object value = jsonObject.get(field.getId());
            if (value == null)
                continue;
            sb.append(field.getName()).append("：").append(value).append(";\n");
        }

        oldValue = sb.toString();
    }
}
