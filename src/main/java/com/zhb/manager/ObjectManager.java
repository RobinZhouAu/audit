package com.zhb.manager;

/**
 * Created by zhouhaibin on 2016/7/20.
 */
public class ObjectManager {
    public static Class getObjectClassByName(String className) throws ClassNotFoundException {
        Class clazz = Class.forName("com.zhb.bean." + className);
        return clazz;
    }
}
