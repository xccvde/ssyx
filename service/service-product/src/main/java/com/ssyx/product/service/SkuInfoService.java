package com.ssyx.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.model.product.SkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.vo.product.SkuInfoQueryVo;
import com.ssyx.vo.product.SkuInfoVo;

import java.util.List;

/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
public interface SkuInfoService extends IService<SkuInfo> {

    IPage<SkuInfo> getPageList(Page<SkuInfo> skuInfoPage, SkuInfoQueryVo skuInfoQueryVo);

    void saveSkuInfo(SkuInfoVo skuInfoVo);

    SkuInfoVo getSkuById(Long id);

    void updateSkuInfo(SkuInfoVo skuInfoVo);

    void publish(Long id, Integer status);

    void check(Long id, Integer status);

    void isNewPerson(Long id, Integer status);

    List<SkuInfo> findSkuInfoList(List<Long> skuIdList);

    List<SkuInfo> findSkuInfoByKeyword(String keyword);
}
