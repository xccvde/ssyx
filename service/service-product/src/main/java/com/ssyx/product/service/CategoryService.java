package com.ssyx.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.model.product.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.vo.product.CategoryQueryVo;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
public interface CategoryService extends IService<Category> {

    IPage<Category> getPageList(Page<Category> pageParam, CategoryQueryVo categoryQueryVo);
}
