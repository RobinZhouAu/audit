package com.zhb.bean;

import com.zhb.core.ObjectBase;

import java.util.List;

/**
 * Created by zhouhaibin on 2016/9/19.
 * 系统预制阶段
 */
//@Entity
//@Table(name="ProjectStage")
public class ProjectStage extends ObjectBase {
    public static final int STATUS_PLANNING = 0;
    public static final int STATUS_RUNNING = 1;
    public static final int STATUS_TRACING = 2;
    public static final int STATUS_CLOSED = 3;
    public static final int STATUS_CANCELED = 4;//内存中的状态

//    String projectId;
//    int stageIndex;
    int centerCount;
//    boolean selected;
    int canceled = 0;
//    Timestamp created;

    List<StageCenter> stageCenters;
    List<String> moduleIdList;

    public List<String> getModuleIdList() {
        return moduleIdList;
    }

    public void setModuleIdList(List<String> moduleIdList) {
        this.moduleIdList = moduleIdList;
    }

    public List<StageCenter> getStageCenters() {
        return stageCenters;
    }

    public void setStageCenters(List<StageCenter> stageCenters) {
        this.stageCenters = stageCenters;
    }

//    public String getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(String projectId) {
//        this.projectId = projectId;
//    }

    public int getCenterCount() {
        return centerCount;
    }

    public void setCenterCount(int centerCount) {
        this.centerCount = centerCount;
    }

//    public int getStageIndex() {
//        return stageIndex;
//    }
//
//    public void setStageIndex(int stageIndex) {
//        this.stageIndex = stageIndex;
//    }

    public int getCanceled() {
        return canceled;
    }

    public void setCanceled(int canceled) {
        this.canceled = canceled;
    }

//    public boolean isSelected() {
//        return selected;
//    }
//
//    public void setSelected(boolean selected) {
//        this.selected = selected;
//    }

//    public Timestamp getCreated() {
//        return created;
//    }
//
//    public void setCreated(Timestamp created) {
//        this.created = created;
//    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }

    public StageCenter findCenter(String centerId) {
        for (StageCenter stageCenter : stageCenters) {
            if (stageCenter.getCenterId().equals(centerId))
                return stageCenter;
        }
        return null;
    }

    public StageCenter findCenterByStageCenterId(String stageCenterId) {
        for (StageCenter stageCenter : stageCenters) {
            if (stageCenter.getId().equals(stageCenterId))
                return stageCenter;
        }
        return null;
    }
}
