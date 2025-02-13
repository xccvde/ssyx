package com.ssyx.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.common.exception.SsyxException;
import com.ssyx.common.result.ResultCodeEnum;
import com.ssyx.model.sys.RegionWare;
import com.ssyx.sys.mapper.RegionWareMapper;
import com.ssyx.sys.service.RegionWareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.vo.sys.RegionWareQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 城市仓库关联表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-15
 */
@Service
public class RegionWareServiceImpl extends ServiceImpl<RegionWareMapper, RegionWare> implements RegionWareService {

    @Override
    public IPage<RegionWare> selectRegionWarePage(Page<RegionWare> pageParam, RegionWareQueryVo regionWareQueryVo) {
        LambdaQueryWrapper<RegionWare> wrapper = new LambdaQueryWrapper<>();
        String keyword = regionWareQueryVo.getKeyword();
        if (!StringUtils.isEmpty(keyword)){
            wrapper.like(RegionWare::getRegionName,keyword).or().like(RegionWare::getWareName,keyword);
        }
        return baseMapper.selectPage(pageParam,wrapper);
    }

    @Override
    public void saveRegionWare(RegionWare regionWare) {
        LambdaQueryWrapper<RegionWare> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RegionWare::getRegionId,regionWare.getRegionId());
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new SsyxException(ResultCodeEnum.REGION_OPEN);
        }
        baseMapper.insert(regionWare);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        RegionWare regionWare = baseMapper.selectById(id);
        regionWare.setStatus(status);
        baseMapper.updateById(regionWare);
    }
}
