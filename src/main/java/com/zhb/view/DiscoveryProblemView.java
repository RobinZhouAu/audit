package com.zhb.view;

import com.zhb.bean.Discovery;
import com.zhb.bean.Reference;
import com.zhb.core.ObjectBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouhaibin on 2016/10/7.
 * 报告里的第二层数据，问题归类的视图
 */
public class DiscoveryProblemView {
    int index = 0;
    String problemId;//问题归类id
    String problemName;//问题归类内容
    String problemOpinion;//问题归类评审建议

    String category;
    List<Reference> references = new ArrayList<>();
    List<DiscoveryPatientView> patientViews = new ArrayList<>();

    public DiscoveryProblemView() {

    }

    public DiscoveryProblemView(String problemId, String problemName) {
        this.problemId = problemId;
        this.problemName = problemName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getProblemOpinion() {
        return problemOpinion;
    }

    public void setProblemOpinion(String problemOpinion) {
        this.problemOpinion = problemOpinion;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public List<DiscoveryPatientView> getPatientViews() {
        return patientViews;
    }

    public void setPatientViews(List<DiscoveryPatientView> patientViews) {
        this.patientViews = patientViews;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public DiscoveryPatientView findPatientView(String patientNo) {
        for (DiscoveryPatientView view : patientViews) {
            if (view.getPatientNo().equals(patientNo))
                return view;
        }
        return null;
    }

    public void addDiscovery(Discovery discovery) {
        String patientNo = discovery.getPatientNo();
        if (patientNo == null || patientNo.isEmpty()) {
            patientNo = ObjectBase.EMPTY_OBJECT;
            DiscoveryPatientView view = new DiscoveryPatientView();
            view.setIndex(patientViews.size() + 1);
            view.setPatientNo(patientNo);
            patientViews.add(view);
            view.addDiscovery(discovery);
        } else {
            DiscoveryPatientView view = findPatientView(patientNo);
            if (view == null) {
                view = new DiscoveryPatientView();
                view.setIndex(patientViews.size() + 1);
                view.setPatientNo(patientNo);
                patientViews.add(view);
            }
            view.addDiscovery(discovery);
        }
    }

    public void addReference(Reference reference) {
        references.add(reference);
    }
}
