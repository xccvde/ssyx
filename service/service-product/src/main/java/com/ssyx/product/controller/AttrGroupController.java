package com.ssyx.product.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.common.result.Result;
import com.ssyx.model.product.AttrGroup;
import com.ssyx.product.service.AttrGroupService;
import com.ssyx.vo.product.AttrGroupQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 属性分组 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
@RestController
@RequestMapping("/admin/product/attrGroup")
//@CrossOrigin
public class AttrGroupController {

    @Resource
    private AttrGroupService attrGroupService;

    @ApiOperation("商品属性分组列表")
    @GetMapping("/{page}/{limit}")
    public Result<IPage<AttrGroup>> getPageList(@PathVariable Long page,
                                                @PathVariable Long limit,
                                                AttrGroupQueryVo attrGroupQueryVo){
        Page<AttrGroup> attrGroupPage = new Page<>(page, limit);
        IPage<AttrGroup> iPage = attrGroupService.getPageList(attrGroupPage, attrGroupQueryVo);
        return Result.success(iPage);
    }

    @ApiOperation("所有商品属性")
    @GetMapping("/findAllList")
    public Result<List<AttrGroup>> findAllList(){
        List<AttrGroup> attrGroups = attrGroupService.findAllList();
        return Result.success(attrGroups);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result<AttrGroup> get(@PathVariable Long id) {
        AttrGroup attrGroup = attrGroupService.getById(id);
        return Result.success(attrGroup);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result<Object> save(@RequestBody AttrGroup attrGroup) {
        attrGroupService.save(attrGroup);
        return Result.success(null);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result<Object> updateById(@RequestBody AttrGroup attrGroup) {
        attrGroupService.updateById(attrGroup);
        return Result.success(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result<Object> remove(@PathVariable Long id) {
        attrGroupService.removeById(id);
        return Result.success(null);
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result<Object> batchRemove(@RequestBody List<Long> idList) {
        attrGroupService.removeByIds(idList);
        return Result.success(null);
    }


}

