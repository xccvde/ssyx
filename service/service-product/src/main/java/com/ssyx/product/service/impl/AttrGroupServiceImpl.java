package com.ssyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.model.product.AttrGroup;
import com.ssyx.product.mapper.AttrGroupMapper;
import com.ssyx.product.service.AttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.vo.product.AttrGroupQueryVo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 属性分组 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup> implements AttrGroupService {

    @Override
    public IPage<AttrGroup> getPageList(Page<AttrGroup> attrGroupPage, AttrGroupQueryVo attrGroupQueryVo) {
        LambdaQueryWrapper<AttrGroup> wrapper = new LambdaQueryWrapper<>();
        String name = attrGroupQueryVo.getName();
        if (name != null) {
            wrapper.like(AttrGroup::getName, name);
        }
        return baseMapper.selectPage(attrGroupPage, wrapper);
    }

    @Override
    public List<AttrGroup> findAllList() {
        QueryWrapper<AttrGroup> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        return baseMapper.selectList(wrapper);
    }

}
