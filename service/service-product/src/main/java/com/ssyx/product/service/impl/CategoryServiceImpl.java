package com.ssyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.model.product.Category;
import com.ssyx.product.mapper.CategoryMapper;
import com.ssyx.product.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.vo.product.CategoryQueryVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public IPage<Category> getPageList(Page<Category> pageParam, CategoryQueryVo categoryQueryVo) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        String name = categoryQueryVo.getName();
        if (name != null) {
            wrapper.like(Category::getName, name);
        }
        return baseMapper.selectPage(pageParam, wrapper);
    }
}
