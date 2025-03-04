package com.ssyx.activity.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssyx.activity.service.CouponInfoService;
import com.ssyx.common.result.Result;
import com.ssyx.model.activity.CouponInfo;
import com.ssyx.vo.activity.CouponRuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/activity/couponInfo")
//@CrossOrigin
public class CouponInfoController {

    @Autowired
    private CouponInfoService couponInfoService;

    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit) {
        IPage<CouponInfo> pageModel =
                couponInfoService.selectPageCouponInfo(page,limit);
        return Result.success(pageModel);
    }

    //2 添加优惠卷
//    url: `${api_name}/save`,
//    method: 'post',
//    data: role
    @PostMapping("save")
    public Result save(@RequestBody CouponInfo couponInfo) {
        couponInfoService.save(couponInfo);
        return Result.success(null);
    }

    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        CouponInfo couponInfo = couponInfoService.getCouponInfo(id);
        return Result.success(couponInfo);
    }

    @GetMapping("findCouponRuleList/{id}")
    public Result findCouponRuleList(@PathVariable Long id) {
        Map<String,Object> map =
                couponInfoService.findCouponRuleList(id);
        return Result.success(map);
    }

    @PostMapping("saveCouponRule")
    public Result saveCouponRule(@RequestBody CouponRuleVo couponRuleVo) {
        couponInfoService.saveCouponRule(couponRuleVo);
        return Result.success(null);
    }

}
