package com.ssyx.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.common.result.Result;
import com.ssyx.model.acl.Role;
import com.ssyx.acl.service.RoleService;
import com.ssyx.vo.acl.RoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色接口")
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("分页查询")
    @GetMapping("/{page}/{limit}")
    public Result pageList(@PathVariable Long page, @PathVariable Long limit, RoleQueryVo roleQueryVo){
        Page<Role> pageParam = new Page<>(page, limit);
        IPage<Role> pageQuery =  roleService.pageQuery(pageParam, roleQueryVo);
        return Result.success(pageQuery);
    }

    @ApiOperation("角色新增")
    @PostMapping("/save")
    public Result save(@RequestBody Role role){
        roleService.save(role);
        return Result.success(null);
    }

    @ApiOperation("角色获取")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id){
        Role role = roleService.getById(id);
        return Result.success(role);
    }

    @ApiOperation("角色更新")
    @PutMapping("/update")
    public Result updateById(@RequestBody Role role){
        roleService.updateById(role);
        return Result.success(null);
    }

//    @ApiOperation("获取角色权限列表")
//    @GetMapping("/toAssign/${roleId}")
//    public Result getAssign(@PathVariable String roleId){
//        roleService.getAssign(roleId);
//        return Result.success(null);
//    }

    @ApiOperation("角色删除")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id){
        roleService.removeById(id);
        return Result.success(null);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList){
        roleService.removeByIds(idList);
        return Result.success(null);
    }
}
