package com.ssyx.product.controller;

import com.ssyx.model.product.Category;
import com.ssyx.model.product.SkuInfo;
import com.ssyx.product.service.CategoryService;
import com.ssyx.product.service.SkuInfoService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhan_py
 * @Date 2024/2/22 14:42
 */
@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductInnerController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private SkuInfoService skuInfoService;

    @GetMapping("/inner/getCategory/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId){
        if (categoryId != null) {
            return categoryService.getById(categoryId);
        }
        return null;
    }

    @GetMapping("/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable Long skuId){
        if (skuId == null) {
            return null;
        }
        return skuInfoService.getById(skuId);
    }

}
