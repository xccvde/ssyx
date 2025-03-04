package com.ssyx.product.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.common.result.Result;
import com.ssyx.model.product.Category;
import com.ssyx.product.service.CategoryService;
import com.ssyx.vo.product.CategoryQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
@RestController
@RequestMapping("/admin/product/category")
//@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{page}/{limit}")
    public Result getPageList(@PathVariable Long page,
                              @PathVariable Long limit,
                              CategoryQueryVo categoryQueryVo){
        Page<Category> pageParam = new Page<>(page, limit);
        IPage<Category> categoryIPage = categoryService.getPageList(pageParam, categoryQueryVo);
        return Result.success(categoryIPage);
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Long id){
        return Result.success(categoryService.getById(id));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Category category){
        categoryService.save(category);
        return Result.success(null);
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody Category category){
        categoryService.updateById(category);
        return Result.success(null);
    }

    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable Long id){
        categoryService.removeById(id);
        return Result.success(null);
    }

    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        categoryService.removeByIds(ids);
        return Result.success(null);
    }

    @GetMapping("findAllList")
    public Result<List<Category>> findAllList() {
        return Result.success(categoryService.list());
    }

}

