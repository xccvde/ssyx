package com.ssyx.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.activity.mapper.CouponInfoMapper;
import com.ssyx.activity.mapper.CouponRangeMapper;
import com.ssyx.activity.mapper.CouponUseMapper;
import com.ssyx.activity.service.CouponInfoService;
import com.ssyx.client.product.product.ProductFeignClient;
import com.ssyx.enums.CouponRangeType;
import com.ssyx.model.activity.CouponInfo;
import com.ssyx.model.activity.CouponRange;
import com.ssyx.model.product.Category;
import com.ssyx.model.product.SkuInfo;
import com.ssyx.vo.activity.CouponRuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Autowired
    private CouponRangeMapper couponRangeMapper;

    @Autowired
    private CouponUseMapper couponUseMapper;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public IPage<CouponInfo> selectPageCouponInfo(Long page, Long limit) {

        Page<CouponInfo> pageParam = new Page<>(page,limit);
        IPage<CouponInfo> couponInfoPage = baseMapper.selectPage(pageParam, null);
        List<CouponInfo> couponInfoList = couponInfoPage.getRecords();
        couponInfoList.stream().forEach(item -> {
            item.setCouponTypeString(item.getCouponType().getComment());
            CouponRangeType rangeType = item.getRangeType();
            if(rangeType != null) {
                item.setRangeTypeString(rangeType.getComment());
            }
        });
        return couponInfoPage;
    }

    @Override
    public CouponInfo getCouponInfo(Long id) {
        CouponInfo couponInfo = baseMapper.selectById(id);
        couponInfo.setCouponTypeString(couponInfo.getCouponType().getComment());
        if(couponInfo.getRangeType() != null) {
            couponInfo.setRangeTypeString(couponInfo.getRangeType().getComment());
        }
        return couponInfo;
    }

    @Override
    public Map<String, Object> findCouponRuleList(Long id) {
        CouponInfo couponInfo = baseMapper.selectById(id);
        List<CouponRange> couponRanges = couponRangeMapper.selectList(new LambdaQueryWrapper<CouponRange>().eq(CouponRange::getCouponId, id));

        List<Long> randIdList = couponRanges.stream().map(CouponRange::getRangeId).collect(Collectors.toList());

        Map<String,Object> result = new HashMap();
        //第三步 分别判断封装不同数据
        if(!CollectionUtils.isEmpty(randIdList)) {
            if(couponInfo.getRangeType() == CouponRangeType.SKU) {
                //// 如果规则类型是SKU ，得到skuId，
                // 远程调用根据多个skuId值获取对应sku信息
                List<SkuInfo> skuInfoList =
                        productFeignClient.findSkuInfoList(randIdList);
                result.put("skuInfoList",skuInfoList);

            } else if(couponInfo.getRangeType() == CouponRangeType.CATEGORY) {
                //// 如果规则类型是分类，得到分类Id，远程调用根据多个分类Id值获取对应分类信息
                List<Category> categoryList =
                        productFeignClient.findCategoryList(randIdList);
                result.put("categoryList",categoryList);
            }
        }
        return result;
    }

    @Override
    public void saveCouponRule(CouponRuleVo couponRuleVo) {
        couponRangeMapper.delete(new LambdaQueryWrapper<CouponRange>().eq(CouponRange::getCouponId, couponRuleVo.getCouponId()));

        CouponInfo couponInfo = baseMapper.selectById(couponRuleVo.getCouponId());
        couponInfo.setRangeType(couponRuleVo.getRangeType());
        couponInfo.setConditionAmount(couponRuleVo.getConditionAmount());
        couponInfo.setAmount(couponRuleVo.getAmount());
        couponInfo.setRangeDesc(couponRuleVo.getRangeDesc());
        baseMapper.updateById(couponInfo);

        List<CouponRange> couponRangeList = couponRuleVo.getCouponRangeList();
        for (CouponRange couponRange : couponRangeList) {
            couponRange.setCouponId(couponRuleVo.getCouponId());
            couponRangeMapper.insert(couponRange);
        }

    }


}
