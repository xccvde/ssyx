package com.ssyx.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.common.result.Result;
import com.ssyx.model.sys.RegionWare;
import com.ssyx.sys.service.RegionWareService;
import com.ssyx.vo.sys.RegionWareQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 城市仓库关联表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2025-02-15
 */
@Api(tags = "开通区域接口")
@RestController
@RequestMapping("/admin/sys/regionWare")
@CrossOrigin
public class RegionWareController {

    @Autowired
    private RegionWareService regionWareService;

    @ApiOperation("获取开通区域信息")
    @GetMapping("/{page}/{limit}")
    public Result getPageList(@PathVariable Long page,
                              @PathVariable Long limit,
                              RegionWareQueryVo regionWareQueryVo) {
        Page<RegionWare> pageParam = new Page<>(page, limit);
        IPage<RegionWare> regionWarePage = regionWareService.selectRegionWarePage(pageParam, regionWareQueryVo);
        return Result.success(regionWarePage);
    }

    @ApiOperation("添加开通区域")
    @PostMapping("/save")
    public Result saveRegionWare(@RequestBody RegionWare regionWare){
        regionWareService.saveRegionWare(regionWare);
        return Result.success(null);
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Long id){
        return Result.success(regionWareService.getById(id));
    }

    @PostMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status){
        regionWareService.updateStatus(id, status);
        return Result.success(null);
    }

    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable Long id){
        regionWareService.removeById(id);
        return Result.success(null);
    }

    @PostMapping("/batchRemove")
    public Result removeRows(@RequestBody List<Long> ids){
        regionWareService.removeByIds(ids);
        return Result.success(null);
    }

}

