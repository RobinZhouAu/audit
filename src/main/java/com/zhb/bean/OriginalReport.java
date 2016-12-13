package com.zhb.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhb.core.ObjectBase;
import com.zhb.core.TimestampMorpher;
import com.zhb.util.JsonTimestampSerializer;
import com.zhb.util.TimestampValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouhaibin on 2016/9/19.
 * 原始版稽查记录表
 */
public class OriginalReport extends ObjectBase {
    public static final int SCORE_STATUS_UNSCORED = 0;//未评估
    public static final int SCORE_STATUS_SCORED = 1;//已评估

    String projectId = EMPTY_OBJECT;
    String stageId = EMPTY_OBJECT;
    String centerId = EMPTY_OBJECT;
    String taskId = EMPTY_OBJECT;
    String creatorId = EMPTY_OBJECT;
    Timestamp created;
    int scoreStatus = SCORE_STATUS_UNSCORED;
    int score = 0;
    String itemScore = EMPTY_JSON_ARRAY;
    String scoreUserId = EMPTY_OBJECT;
    Timestamp scoreTime;
    String projectName;
    String centerName;
    String stageName;
    String leaderId = EMPTY_OBJECT;
    String memberIds = EMPTY_JSON_ARRAY;
    String content;
    String fulltext;
    int canceled = 0;
    int closed = 0;


    List<Discovery> discoveries;
    List<ModuleRecord> moduleRecords;

    public OriginalReport() {

    }

    public OriginalReport(Task task) {
        id = task.getId();
        projectId = task.getProjectId();
        stageId = task.getStageId();
        centerId = task.getCenterId();
        taskId = task.getId();
        leaderId = task.getLeaderId();
        memberIds = task.getMemberIds();
        projectName = task.getProjectName();
        centerName = task.getCenterName();
        stageName = task.getStageName();
        fulltext = task.getFulltext();
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public int getCanceled() {
        return canceled;
    }

    public void setCanceled(int canceled) {
        this.canceled = canceled;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public List<Discovery> getDiscoveries() {
        return discoveries;
    }

    public void setDiscoveries(List<Discovery> discoveries) {
        this.discoveries = discoveries;
    }

    public List<ModuleRecord> getModuleRecords() {
        return moduleRecords;
    }

    public void setModuleRecords(List<ModuleRecord> moduleRecords) {
        this.moduleRecords = moduleRecords;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @JsonSerialize(using=JsonTimestampSerializer.class)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScoreStatus() {
        return scoreStatus;
    }

    public void setScoreStatus(int scoreStatus) {
        this.scoreStatus = scoreStatus;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getItemScore() {
        return itemScore;
    }

    public void setItemScore(String itemScore) {
        this.itemScore = itemScore;
    }

    public String getScoreUserId() {
        return scoreUserId;
    }

    public void setScoreUserId(String scoreUserId) {
        this.scoreUserId = scoreUserId;
    }

    public Timestamp getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Timestamp scoreTime) {
        this.scoreTime = scoreTime;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }

    public void list2Content() {
        JSONObject jsonObject = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Timestamp.class, new TimestampValueProcessor());
        jsonObject.put("moduleRecords", JSONArray.fromObject(moduleRecords, jsonConfig));
        jsonObject.put("discoveries", JSONArray.fromObject(discoveries, jsonConfig));
        content = jsonObject.toString();
    }

    public void content2List() {
        JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher());
        JSONObject jsonObject = JSONObject.fromObject(content);
//        moduleRecords = convertJSONArrayToList(jsonObject.getJSONArray("moduleRecords"), ModuleRecord.class, classMap);
        moduleRecords = new ArrayList();
        JSONArray jsonArray = jsonObject.getJSONArray("moduleRecords");
        for (int i = 0; i < jsonArray.size(); i ++) {
            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
            ModuleRecord moduleRecord = new ModuleRecord();
            moduleRecord.fromJsonObject(jsonObject2);

            moduleRecords.add(moduleRecord);
        }

        discoveries = convertJSONArrayToList(jsonObject.getJSONArray("discoveries"), Discovery.class, null);
    }

}
