package com.ssyx.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.model.sys.RegionWare;
import com.ssyx.vo.sys.RegionWareQueryVo;

/**
 * <p>
 * 城市仓库关联表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-15
 */
public interface RegionWareService extends IService<RegionWare> {

    IPage<RegionWare> selectRegionWarePage(Page<com.ssyx.model.sys.RegionWare> pageParam, RegionWareQueryVo regionWareQueryVo);

    void saveRegionWare(RegionWare regionWare);

    void updateStatus(Long id, Integer status);
}
