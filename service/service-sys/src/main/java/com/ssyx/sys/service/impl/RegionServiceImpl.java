package com.ssyx.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ssyx.model.sys.Region;
import com.ssyx.sys.mapper.RegionMapper;
import com.ssyx.sys.service.RegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-15
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    @Override
    public List<Region> findRegionByKeyword(String keyword) {
        LambdaQueryWrapper<Region> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Region::getName, keyword);
        List<Region> list = baseMapper.selectList(wrapper);
        return list == null ? Collections.emptyList() : list;
    }
}
