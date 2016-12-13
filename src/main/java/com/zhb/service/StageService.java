package com.zhb.service;

import com.zhb.bean.Stage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhouhaibin on 2016/7/20.
 */

@Service("StageService")
public class StageService extends ServiceBase {
    public List loadStages() {
        String sql = "from Stage order by name";
        List list = dao.loadList(sql, 0, 10000);
        return list;
    }

    public void addStage() {
    }

    public void editStage() {
    }

    public void deleteStage(String id) {
    }

    public Stage loadStage(String id) {
        return null;
    }
}
