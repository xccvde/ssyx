package com.ssyx.acl.controller;

import com.ssyx.acl.service.RolePermissionService;
import com.ssyx.common.result.Result;
import com.ssyx.model.acl.Permission;
import com.ssyx.acl.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("菜单管理")
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @ApiOperation("获取全部菜单")
    @GetMapping
    public Result getPermissionList(){
        List<Permission> permissions =  permissionService.getPermissionList();
        return Result.success(permissions);
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("/remove/{id}")
    public Result removePermission(@PathVariable Long id){
        permissionService.removeChildById(id);
        return Result.success(null);
    }

    @ApiOperation("添加菜单")
    @PostMapping("/save")
    public Result addPermission(@RequestBody Permission permission){
        permissionService.save(permission);
        return Result.success(null);
    }

    @ApiOperation("更新菜单")
    @PutMapping("/update")
    public Result updatePermission(@RequestBody Permission permission){
        permissionService.updateById(permission);
        return Result.success(null);
    }

    @ApiOperation("根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result<List<Permission>> toAssign(@PathVariable Long roleId){
        List<Permission> allPermissionList = permissionService.getPermissionList();
        rolePermissionService.toAssign(roleId, allPermissionList);
        return Result.success(allPermissionList);
    }

    @ApiOperation("给角色分配菜单")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam Long roleId, @RequestParam List<Long> permissionId){
        rolePermissionService.doAssign(roleId, permissionId);
        return Result.success(null);
    }

}
