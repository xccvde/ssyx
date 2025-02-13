package com.ssyx.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.model.activity.ActivityInfo;
import com.ssyx.vo.activity.ActivityRuleVo;

import java.util.Map;

public interface ActivityInfoService extends IService<ActivityInfo> {

    IPage<ActivityInfo> selectPage(Page<ActivityInfo> pageParam);

    Map<String, Object> findActivityRuleList(Long id);

    void saveActivityRule(ActivityRuleVo activityRuleVo);
}
