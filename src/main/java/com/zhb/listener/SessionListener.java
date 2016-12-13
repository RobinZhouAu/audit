package com.zhb.listener;

import com.zhb.controller.ControllerBase;
import com.zhb.manager.LockManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by zhouhaibin on 2016/11/30.
 */
public class SessionListener implements HttpSessionListener {
    protected Logger logger = Logger.getLogger(SessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        String userId = (String)session.getAttribute(ControllerBase.USER_ID);
        if (userId != null)
            LockManager.releaseUserAllLocks(userId);
        logger.info(String.format("User[%s] session destroyed", userId));
    }
}
