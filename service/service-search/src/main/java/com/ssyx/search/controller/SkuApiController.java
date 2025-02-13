package com.ssyx.search.controller;

import com.ssyx.common.result.Result;
import com.ssyx.search.service.SkuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/search/sku")
@CrossOrigin
public class SkuApiController {
    @Resource
    private SkuService skuService;

    //上架
    @GetMapping("/inner/upperSku/{skuId}")
    public Result<Object> upperSku(@PathVariable Long skuId){
        skuService.upperSku(skuId);
        return Result.success(null);
    }

    //下架
    @GetMapping("/inner/lowerSku/{skuId}")
    public Result<Object> lowerSku(@PathVariable Long skuId){
        skuService.lowerSku(skuId);
        return Result.success(null);
    }
}
