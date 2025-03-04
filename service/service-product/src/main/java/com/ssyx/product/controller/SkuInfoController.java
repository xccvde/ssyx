package com.ssyx.product.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.common.result.Result;
import com.ssyx.model.product.SkuInfo;
import com.ssyx.product.service.SkuInfoService;
import com.ssyx.vo.product.SkuInfoQueryVo;
import com.ssyx.vo.product.SkuInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * sku信息 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
@RestController
@RequestMapping("/admin/product/skuInfo")
//@CrossOrigin
public class SkuInfoController {

    @Resource
    private SkuInfoService skuInfoService;

    @ApiOperation("获取sku列表")
    @GetMapping("/{page}/{limit}")
    public Result<IPage<SkuInfo>> getPageList(@PathVariable Long page,
                                              @PathVariable Long limit,
                                              SkuInfoQueryVo skuInfoQueryVo){
        Page<SkuInfo> skuInfoPage = new Page<>(page, limit);
        IPage<SkuInfo> iPage = skuInfoService.getPageList(skuInfoPage, skuInfoQueryVo);
        return Result.success(iPage);

    }

    @ApiOperation("添加商品sku信息")
    @PostMapping("/save")
    public Result<Object> save(@RequestBody SkuInfoVo skuInfoVo){
        skuInfoService.saveSkuInfo(skuInfoVo);
        return Result.success(null);
    }

    @ApiOperation("查询sku信息")
    @GetMapping("/get/{id}")
    public Result<SkuInfoVo> getById(@PathVariable Long id){
        SkuInfoVo skuInfoVo = skuInfoService.getSkuById(id);
        return Result.success(skuInfoVo);
    }

    @ApiOperation("修改商品sku信息")
    @PutMapping("/update")
    public Result<Object> update(@RequestBody SkuInfoVo skuInfoVo){
        skuInfoService.updateSkuInfo(skuInfoVo);
        return Result.success(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result<Object> remove(@PathVariable Long id) {
        skuInfoService.removeById(id);
        return Result.success(null);
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result<Object> batchRemove(@RequestBody List<Long> idList) {
        skuInfoService.removeByIds(idList);
        return Result.success(null);
    }

    @GetMapping("/publish/{id}/{status}")
    public Result<Object> publish(@PathVariable Long id, @PathVariable Integer status) {
        skuInfoService.publish(id, status);
        return Result.success(null);
    }

    @GetMapping("/check/{id}/{status}")
    public Result<Object> check(@PathVariable Long id, @PathVariable Integer status) {
        skuInfoService.check(id, status);
        return Result.success(null);
    }

    @GetMapping("/isNewPerson/{id}/{status}")
    public Result<Object> isNewPerson(@PathVariable Long id, @PathVariable Integer status) {
        skuInfoService.isNewPerson(id, status);
        return Result.success(null);
    }

}

