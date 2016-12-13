package com.zhb.view;

import com.zhb.bean.Discovery;
import com.zhb.bean.Reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouhaibin on 2016/10/7.
 */
public class ReportView {
    String id;
    boolean centerReport = true;
    String projectId;
    String purpose;
    String range;
    String foundation;
    String overview;
    String overviewOpinion;

    List<DiscoveryLevelView> levelViews = new ArrayList<>();
    Map<String, CategoryLevelCount> categoryLevelCountMap = new HashMap<>();
    Map<String, Integer> levelCountMap = new HashMap<>();

    public ReportView() {

    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public boolean isCenterReport() {
        return centerReport;
    }

    public void setCenterReport(boolean centerReport) {
        this.centerReport = centerReport;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getFoundation() {
        return foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverviewOpinion() {
        return overviewOpinion;
    }

    public void setOverviewOpinion(String overviewOpinion) {
        this.overviewOpinion = overviewOpinion;
    }

    public Map<String, CategoryLevelCount> getCategoryLevelCountMap() {
        return categoryLevelCountMap;
    }

    public Map<String, Integer> getLevelCountMap() {
        return levelCountMap;
    }

    public void setLevelCountMap(Map<String, Integer> levelCountMap) {
        this.levelCountMap = levelCountMap;
    }

    public void setCategoryLevelCountMap(Map<String, CategoryLevelCount> categoryLevelCountMap) {
        this.categoryLevelCountMap = categoryLevelCountMap;
    }

    public List<DiscoveryLevelView> getLevelViews() {
        return levelViews;
    }

    public void setLevelViews(List<DiscoveryLevelView> levelViews) {
        this.levelViews = levelViews;
    }

    public void addLevelView(DiscoveryLevelView levelView) {
        levelViews.add(levelView);
    }

    public DiscoveryLevelView findLevelView(String level) {
        for (DiscoveryLevelView view : levelViews) {
            if (view.getLevel().equals(level)) {
                return view;
            }
        }
        return null;
    }

    public void addDiscovery(Discovery discovery) {
        DiscoveryLevelView levelView = findLevelView(discovery.getLevel());
        if (levelView == null)
            return;
        levelView.addDiscovery(discovery);

        CategoryLevelCount categoryLevelCount = categoryLevelCountMap.get(discovery.getCategoryId());
        if (categoryLevelCount == null) {
            categoryLevelCount = new CategoryLevelCount();
            categoryLevelCount.addCenterType(discovery.getCenterType());
            categoryLevelCountMap.put(discovery.getCategoryId(), categoryLevelCount);
        }
        categoryLevelCount.addCount(discovery.getLevel());


        //计算总和
        categoryLevelCount = categoryLevelCountMap.get("TOTAL");
        if (categoryLevelCount == null) {
            categoryLevelCount = new CategoryLevelCount();
            categoryLevelCountMap.put("TOTAL", categoryLevelCount);
        }
        categoryLevelCount.addCount(discovery.getLevel());


        Integer count = levelCountMap.get(discovery.getLevel());
        if (count == null) {
            levelCountMap.put(discovery.getLevel(), 1);
        } else {
            levelCountMap.put(discovery.getLevel(), count + 1);
        }
    }

    public void addReference(Reference reference) {
        for (DiscoveryLevelView levelView : levelViews) {
            levelView.addReference(reference);
        }
    }

    public int getCountByLevel(String level) {
        Integer count = levelCountMap.get(level);
        if (count == null)
            return 0;
        return count;
    }
}
