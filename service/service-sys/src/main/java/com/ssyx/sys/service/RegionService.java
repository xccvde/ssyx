package com.ssyx.sys.service;

import com.ssyx.model.sys.Region;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-15
 */
public interface RegionService extends IService<Region> {

    List<Region> findRegionByKeyword(String keyword);
}
