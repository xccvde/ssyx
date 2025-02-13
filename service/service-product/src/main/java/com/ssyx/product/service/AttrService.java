package com.ssyx.product.service;

import com.ssyx.model.product.Attr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
public interface AttrService extends IService<Attr> {

    List<Attr> getList(Long groupId);
}
