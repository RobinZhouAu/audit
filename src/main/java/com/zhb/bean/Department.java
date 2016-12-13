package com.zhb.bean;

import com.zhb.core.ObjectBase;
import com.zhb.core.ObjectNameComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhouhaibin on 2016/9/19.
 */
public class Department extends ObjectBase {
    String parentId;
    String fullPathName;
    List<Department> children = new ArrayList<>();

    public void addChild(Department child) {
        children.add(child);
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    public String getFullPathName() {
        return fullPathName;
    }

    public void setFullPathName(String fullPathName) {
        this.fullPathName = fullPathName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public void copyForUpdate(ObjectBase object) {

    }

    public void sortChildren() {
        Collections.sort(children, new ObjectNameComparator());
        for (Department child : children)
            child.sortChildren();
//        for (int i = 0; i < children.size(); i ++) {
//            Department child1 = children.get(i);
//            for (int j = i + 1; j < children.size(); j ++) {
//                Department child2 = children.get(j);
//                if (child1.getName().compareTo(child2.getName()) > 0) {
//                    children.set(i, child2);
//                    children.set(j, child1);
//                    child1 = child2;
//                }
//            }
//        }
    }
}
