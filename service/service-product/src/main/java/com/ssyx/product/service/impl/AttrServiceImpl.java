package com.ssyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ssyx.model.product.Attr;
import com.ssyx.product.mapper.AttrMapper;
import com.ssyx.product.service.AttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements AttrService {

    @Override
    public List<Attr> getList(Long groupId) {
        LambdaQueryWrapper<Attr> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Attr::getAttrGroupId, groupId);
        return baseMapper.selectList(wrapper);
    }
}
