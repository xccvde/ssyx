package com.ssyx.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssyx.model.activity.ActivityInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {
    List<Long> selectSkuIdListExist(List<Long> skuIdList);
}
