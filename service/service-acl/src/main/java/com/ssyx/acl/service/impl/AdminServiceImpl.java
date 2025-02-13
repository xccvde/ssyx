package com.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.acl.mapper.AdminMapper;
import com.ssyx.acl.mapper.AdminRoleMapper;
import com.ssyx.acl.mapper.RoleMapper;
import com.ssyx.model.acl.Admin;
import com.ssyx.model.acl.AdminRole;
import com.ssyx.model.acl.Role;
import com.ssyx.model.user.User;
import com.ssyx.acl.service.AdminService;
import com.ssyx.vo.acl.AdminQueryVo;
import com.ssyx.vo.user.UserQueryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public IPage<Admin> pageQuery(Page<Admin> pageParam, AdminQueryVo adminQueryVo) {
        String name = adminQueryVo.getName();
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like(Admin::getName, name);
        }
        Page<Admin> adminPage = baseMapper.selectPage(pageParam, wrapper);
        return adminPage;
    }

    @Override
    public List<Role> getRoles(String adminId) {

        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId, adminId);
        List<AdminRole> adminRoles = adminRoleMapper.selectList(wrapper);
        if (adminRoles != null && !adminRoles.isEmpty()) {
            List<Long> roleIds = adminRoles.stream().map(AdminRole::getRoleId).collect(Collectors.toList());
            List<Role> roles = roleMapper.selectBatchIds(roleIds);
            return roles;
        }
        return Collections.emptyList();
    }

}
