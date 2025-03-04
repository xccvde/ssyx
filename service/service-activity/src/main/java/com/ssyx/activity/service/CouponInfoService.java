package com.ssyx.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.model.activity.CouponInfo;
import com.ssyx.vo.activity.CouponRuleVo;

import java.util.Map;

public interface CouponInfoService extends IService<CouponInfo> {
    IPage<CouponInfo> selectPageCouponInfo(Long page, Long limit);

    CouponInfo getCouponInfo(Long id);

    Map<String, Object> findCouponRuleList(Long id);

    void saveCouponRule(CouponRuleVo couponRuleVo);
}
