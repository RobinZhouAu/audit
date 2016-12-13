package com.zhb.manager;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhouhaibin on 2016/11/30.
 */
public class LockManager {
    static Logger logger = Logger.getLogger(LockManager.class);

    static Map<String, ResourceLock> locks = new HashMap<>();

    public static Map<String, ResourceLock> getLocks() {
        return locks;
    }

    public static boolean addLock(String resourceId, int resourceType, String userId) {
        ResourceLock resourceLock = locks.get(resourceId);
        if (resourceLock != null) {
            if (resourceLock.getUserId().equals(userId)) {
                resourceLock.update();
                logger.debug(String.format("update resource lock[%s][%s]", resourceId, userId));
                return true;
            }
            return false;
        }
        resourceLock = new ResourceLock(resourceId, resourceType, userId);
        locks.put(resourceLock.getResourceId(), resourceLock);
        logger.debug(String.format("add resource lock[%s][%s]", resourceId, userId));
        return true;
    }

    public static boolean releaseLock(String resourceId, String userId) {
        ResourceLock resourceLock = locks.get(resourceId);
        if (resourceLock == null)
            return true;
        if (!resourceLock.getUserId().equals(userId))
            return false;
        locks.remove(resourceId);
        logger.debug(String.format("release resource lock[%s][%s]", resourceId, userId));
        return true;
    }

    public static void releaseUserAllLocks(String userId) {
        logger.debug(String.format("release all user's resource lock[%s]", userId));
        for (Iterator iterator = locks.keySet().iterator(); iterator.hasNext(); ) {
            String resourceId = (String)iterator.next();
            ResourceLock resourceLock = locks.get(resourceId);
            if (resourceLock.getUserId().equals(userId)) {
                locks.remove(resourceId);
                logger.debug(String.format("release user's resource lock[%s]", resourceId));
            }
        }
    }

    public static String getResourceLocker(String resourceId) {
        ResourceLock resourceLock = locks.get(resourceId);
        if (resourceLock != null) {
            return resourceLock.getUserId();
        }
        return null;
    }

    public static boolean releaseByAdmin(String resourceId) {
        ResourceLock resourceLock = locks.get(resourceId);
        if (resourceLock == null)
            return true;
        locks.remove(resourceId);
        logger.debug(String.format("release resource lock by admin [%s]", resourceId));
        return true;
    }

}
