package com.ssyx.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.model.sys.Ware;
import com.ssyx.sys.mapper.WareMapper;
import com.ssyx.sys.service.WareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.vo.product.WareQueryVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-15
 */
@Service
public class WareServiceImpl extends ServiceImpl<WareMapper, Ware> implements WareService {

    @Override
    public IPage<Ware> selectWarePage(Page<Ware> pageParam, WareQueryVo wareQueryVo) {
        LambdaQueryWrapper<Ware> wrapper = new LambdaQueryWrapper<>();
        String name = wareQueryVo.getName();
        if (name != null) {
            wrapper.like(Ware::getName, name);
        }
        return baseMapper.selectPage(pageParam, wrapper);
    }
}
