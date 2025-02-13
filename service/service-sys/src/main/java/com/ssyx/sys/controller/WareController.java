package com.ssyx.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.common.result.Result;
import com.ssyx.model.sys.Ware;
import com.ssyx.sys.service.WareService;
import com.ssyx.vo.product.WareQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2025-02-15
 */

@RestController
@RequestMapping("/admin/sys/ware")
@CrossOrigin
public class WareController {

    @Autowired
    private WareService wareService;

    @GetMapping("/{page}/{limit}")
    public Result getPageList(@PathVariable Long page,@PathVariable Long limit, WareQueryVo wareQueryVo) {
        Page<Ware> pageParam = new Page<>(page, limit);
        return Result.success(wareService.selectWarePage(pageParam, wareQueryVo));
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Long id){
        return Result.success(wareService.getById(id));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Ware ware){
        wareService.save(ware);
        return Result.success(null);
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody Ware ware){
        wareService.updateById(ware);
        return Result.success(null);
    }

    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable Long id){
        wareService.removeById(id);
        return Result.success(null);
    }

    @DeleteMapping("/batchRemove")
    public Result removeRows(@RequestBody List<Long> ids){
        wareService.removeByIds(ids);
        return Result.success(null);
    }

    @GetMapping("/findAllList")
    public Result findAllList(){
        return Result.success(wareService.list());
    }

}

