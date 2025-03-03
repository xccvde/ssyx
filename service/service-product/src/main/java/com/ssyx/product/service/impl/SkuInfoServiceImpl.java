package com.ssyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.constant.MQConstant;
import com.ssyx.model.product.SkuAttrValue;
import com.ssyx.model.product.SkuImage;
import com.ssyx.model.product.SkuInfo;
import com.ssyx.model.product.SkuPoster;
import com.ssyx.product.mapper.SkuInfoMapper;
import com.ssyx.product.service.SkuAttrValueService;
import com.ssyx.product.service.SkuImageService;
import com.ssyx.product.service.SkuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.product.service.SkuPosterService;
import com.ssyx.service.RabbitMQService;
import com.ssyx.vo.product.SkuInfoQueryVo;
import com.ssyx.vo.product.SkuInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * sku信息 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {

    @Resource
    private SkuImageService skuImageService;

    @Resource
    private SkuPosterService skuPosterService;

    @Resource
    private SkuAttrValueService skuAttrValueService;

    @Resource
    private RabbitMQService rabbitService;

    @Override
    public IPage<SkuInfo> getPageList(Page<SkuInfo> skuInfoPage, SkuInfoQueryVo skuInfoQueryVo) {
        String skuType = skuInfoQueryVo.getSkuType();
        Long categoryId = skuInfoQueryVo.getCategoryId();
        String keyword = skuInfoQueryVo.getKeyword();
        LambdaQueryWrapper<SkuInfo> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(skuType)) {
            wrapper.eq(SkuInfo::getSkuType, skuType);
        }
        if (categoryId != null) {
            wrapper.eq(SkuInfo::getCategoryId, categoryId);
        }
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(SkuInfo::getSkuName, keyword);
        }
        return baseMapper.selectPage(skuInfoPage, wrapper);
    }

    @Override
    public void saveSkuInfo(SkuInfoVo skuInfoVo) {
        SkuInfo skuInfo = new SkuInfo();
        BeanUtils.copyProperties(skuInfoVo, skuInfo);
        baseMapper.insert(skuInfo);
        saveSkuInfoVoList(skuInfoVo, skuInfo);
    }

    private void saveSkuInfoVoList(SkuInfoVo skuInfoVo, SkuInfo skuInfo) {
        List<SkuPoster> skuPosterList = skuInfoVo.getSkuPosterList();
        if (!CollectionUtils.isEmpty(skuPosterList)){
            for (SkuPoster skuPoster: skuPosterList){
                skuPoster.setSkuId(skuInfo.getId());
            }
            skuPosterService.saveBatch(skuPosterList);
        }

        List<SkuAttrValue> skuAttrValueList = skuInfoVo.getSkuAttrValueList();
        if (!CollectionUtils.isEmpty(skuAttrValueList)){
            for (SkuAttrValue skuAttrValue: skuAttrValueList){
                skuAttrValue.setSkuId(skuInfo.getId());
            }
            skuAttrValueService.saveBatch(skuAttrValueList);
        }

        List<SkuImage> skuImagesList = skuInfoVo.getSkuImagesList();
        if (!CollectionUtils.isEmpty(skuImagesList)){
            for (SkuImage skuImage: skuImagesList){
                skuImage.setSkuId(skuInfo.getId());
            }
            skuImageService.saveBatch(skuImagesList);
        }
    }

    @Override
    public SkuInfoVo getSkuById(Long id) {
        SkuInfoVo skuInfoVo = new SkuInfoVo();
        SkuInfo skuInfo = baseMapper.selectById(id);
        BeanUtils.copyProperties(skuInfo, skuInfoVo);

        List<SkuPoster> skuPosterList = skuPosterService
                .list(new LambdaQueryWrapper<SkuPoster>().eq(SkuPoster::getSkuId, id));
        List<SkuAttrValue> skuAttrValueList = skuAttrValueService
                .list(new LambdaQueryWrapper<SkuAttrValue>().eq(SkuAttrValue::getSkuId, id));
        List<SkuImage> skuImagesList = skuImageService
                .list(new LambdaQueryWrapper<SkuImage>().eq(SkuImage::getSkuId, id));
        skuInfoVo.setSkuPosterList(skuPosterList);
        skuInfoVo.setSkuAttrValueList(skuAttrValueList);
        skuInfoVo.setSkuImagesList(skuImagesList);

        return skuInfoVo;
    }

    @Override
    public void updateSkuInfo(SkuInfoVo skuInfoVo) {
        SkuInfo skuInfo = new SkuInfo();
        BeanUtils.copyProperties(skuInfoVo, skuInfo);
        baseMapper.updateById(skuInfo);

        skuAttrValueService.remove(new LambdaQueryWrapper<SkuAttrValue>().eq(SkuAttrValue::getSkuId, skuInfo.getId()));
        skuPosterService.remove(new LambdaQueryWrapper<SkuPoster>().eq(SkuPoster::getSkuId, skuInfo.getId()));
        skuImageService.remove(new LambdaQueryWrapper<SkuImage>().eq(SkuImage::getSkuId, skuInfo.getId()));
        saveSkuInfoVoList(skuInfoVo, skuInfo);
    }

    @Override
    public void publish(Long id, Integer status) {
        if (status == 1) { // 上架
            SkuInfo skuInfo = baseMapper.selectById(id);
            skuInfo.setPublishStatus(status);
            baseMapper.updateById(skuInfo);

//            TODO 整合MQ把数据同步到ES里面
            rabbitService.sendMessage(
                    MQConstant.EXCHANGE_GOODS_DIRECT,
                    MQConstant.ROUTING_GOODS_UPPER,
                    id);
        } else { // 下架
            SkuInfo skuInfo = baseMapper.selectById(id);
            skuInfo.setPublishStatus(status);
            baseMapper.updateById(skuInfo);
//            TODO 整合MQ把数据同步到ES里面
            rabbitService.sendMessage(
                    MQConstant.EXCHANGE_GOODS_DIRECT,
                    MQConstant.ROUTING_LEADER_LOWER,
                    id
            );
        }
    }

    @Override
    public void check(Long id, Integer status) {
        SkuInfo skuInfo = baseMapper.selectById(id);
        skuInfo.setCheckStatus(status);
        baseMapper.updateById(skuInfo);
    }

    @Override
    public void isNewPerson(Long id, Integer status) {
        SkuInfo skuInfo = baseMapper.selectById(id);
        skuInfo.setIsNewPerson(status);
        baseMapper.updateById(skuInfo);
    }

    @Override
    public List<SkuInfo> findSkuInfoList(List<Long> skuIdList) {
        if (CollectionUtils.isEmpty(skuIdList)){
            return Collections.emptyList();
        }
        return baseMapper.selectBatchIds(skuIdList);
    }

    @Override
    public List<SkuInfo> findSkuInfoByKeyword(String keyword) {
        List<SkuInfo> skuInfoList = baseMapper.selectList(
                new LambdaQueryWrapper<SkuInfo>().like(SkuInfo::getSkuName, keyword));
        return skuInfoList;
    }

}
