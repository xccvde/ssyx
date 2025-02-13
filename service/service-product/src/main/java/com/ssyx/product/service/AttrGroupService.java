package com.ssyx.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.model.product.AttrGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.vo.product.AttrGroupQueryVo;

import java.util.List;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
public interface AttrGroupService extends IService<AttrGroup> {

    IPage<AttrGroup> getPageList(Page<AttrGroup> attrGroupPage, AttrGroupQueryVo attrGroupQueryVo);

    List<AttrGroup> findAllList();

}
