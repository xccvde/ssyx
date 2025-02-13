package com.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.acl.mapper.AdminRoleMapper;
import com.ssyx.acl.mapper.RoleMapper;
import com.ssyx.model.acl.AdminRole;
import com.ssyx.model.acl.Role;
import com.ssyx.acl.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {


    @Override
    public void saveAdminRole(Long adminId, List<Long> roleIds) {
        // 删除用户的所有角色
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId, adminId);
        baseMapper.delete(wrapper);

        // 添加用户角色
        List<AdminRole> adminRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            adminRoles.add(adminRole);
        }
        this.saveBatch(adminRoles);
    }
}
