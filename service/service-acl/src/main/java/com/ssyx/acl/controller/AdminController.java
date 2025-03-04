package com.ssyx.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssyx.common.result.Result;
import com.ssyx.model.acl.Admin;
import com.ssyx.model.acl.Role;
import com.ssyx.acl.service.AdminRoleService;
import com.ssyx.acl.service.AdminService;
import com.ssyx.acl.service.RoleService;
import com.ssyx.vo.acl.AdminQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "管理员接口")
@RestController
@RequestMapping("/admin/acl/user")
//@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation("分页查询")
    @GetMapping("/{page}/{limit}")
    public Result pageList(@PathVariable Long page, @PathVariable Long limit, AdminQueryVo adminQueryVo){
        Page<Admin> pageParam = new Page<>(page, limit);
        IPage<Admin> pageQuery =  adminService.pageQuery(pageParam, adminQueryVo);
        return Result.success(pageQuery);
    }

    @ApiOperation("根据id获取")
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable String id){
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    @ApiOperation("新增")
    @PostMapping("/save")
    public Result save(@RequestBody Admin admin){
        adminService.save(admin);
        return Result.success(null);
    }

    @ApiOperation("更新")
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){
        adminService.updateById(admin);
        return Result.success(null);
    }

    @ApiOperation("删除")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id){
        adminService.removeById(id);
        return Result.success(null);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList){
        adminService.removeByIds(idList);
        return Result.success(null);
    }

    @ApiOperation("获取某个用户的所有角色")
    @GetMapping("/toAssign/{adminId}")
    public Result getRoles(@PathVariable String adminId){
        Map<String, Object> result = new HashMap<>();
        List<Role> roles = roleService.getAllRole();
        result.put("allRolesList", roles);

        List<Role> roleIdList = adminService.getRoles(adminId);
        result.put("assignRoles", roleIdList);
        return Result.success(result);
    }

    @ApiOperation("分配角色")
    @PostMapping("/doAssign")
    public Result assignRoles(@RequestParam Long adminId, @RequestParam List<Long> roleId){
        adminRoleService.saveAdminRole(adminId, roleId);
        return Result.success(null);
    }

}
