package com.zhb.manager;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhb.util.JsonTimestampWithTimeSerializer;

import java.sql.Timestamp;

/**
 * Created by zhouhaibin on 2016/11/30.
 */
public class ResourceLock {
    public static final int RESOURCE_TYPE_TASK_MODULE = 0;
    public static final int RESOURCE_TYPE_DISCOVERY = 1;
    public static final int RESOURCE_TYPE_CENTER_REPORT = 2;
    public static final int RESOURCE_TYPE_STAGE_REPORT = 3;

    String resourceId;
    int resourceType;
    String userId;
    Timestamp lastUpdateTime;

    public ResourceLock() {
    }

    public ResourceLock(String resourceId, int resourceType, String userId) {
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.userId = userId;
        lastUpdateTime = new Timestamp(System.currentTimeMillis());
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonSerialize(using=JsonTimestampWithTimeSerializer.class)
    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getResourceType() {
        return resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public void update() {
        lastUpdateTime = new Timestamp(System.currentTimeMillis());
    }
}
