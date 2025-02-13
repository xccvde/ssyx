package com.ssyx.activity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.activity.mapper.CouponInfoMapper;
import com.ssyx.activity.service.CouponInfoService;
import com.ssyx.model.activity.CouponInfo;
import org.springframework.stereotype.Service;

@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {
}
