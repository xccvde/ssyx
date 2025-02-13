package com.ssyx.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.model.sys.Ware;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.vo.product.WareQueryVo;

/**
 * <p>
 * 仓库表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-15
 */
public interface WareService extends IService<Ware> {

    IPage<Ware> selectWarePage(Page<Ware> pageParam, WareQueryVo wareQueryVo);
}
