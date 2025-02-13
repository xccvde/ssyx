package com.ssyx.product.controller;


import com.ssyx.common.result.Result;
import com.ssyx.model.product.Attr;
import com.ssyx.product.service.AttrService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
@RestController
@RequestMapping("/admin/product/attr")
@CrossOrigin
public class AttrController {

    @Resource
    private AttrService attrService;

    @ApiOperation("根据分组id查询商品属性")
    @GetMapping("/{groupId}")
    public Result<List<Attr>> getList(@PathVariable Long groupId){
        List<Attr> attrList = attrService.getList(groupId);
        return Result.success(attrList);
    }
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result<Attr> get(@PathVariable Long id) {
        Attr attr = attrService.getById(id);
        return Result.success(attr);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result<Objects> save(@RequestBody Attr attr) {
        attrService.save(attr);
        return Result.success(null);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result<Objects> updateById(@RequestBody Attr attr) {
        attrService.updateById(attr);
        return Result.success(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result<Objects> remove(@PathVariable Long id) {
        attrService.removeById(id);
        return Result.success(null);
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result<Objects> batchRemove(@RequestBody List<Long> idList) {
        attrService.removeByIds(idList);
        return Result.success(null);
    }

}

