package com.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.acl.mapper.RolePermissionMapper;
import com.ssyx.acl.service.RolePermissionService;
import com.ssyx.model.acl.Permission;
import com.ssyx.model.acl.RolePermission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
    @Override
    public void toAssign(Long roleId, List<Permission> allPermissionList) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId,roleId);
        List<RolePermission> rolePermissionList = baseMapper.selectList(wrapper);
        for (RolePermission rolePermission:rolePermissionList){
            toAssignChildren(rolePermission, allPermissionList);
        }
    }

    private void toAssignChildren(RolePermission rolePermission, List<Permission> allPermissionList) {
        for (Permission permission : allPermissionList){
            if (rolePermission.getPermissionId().longValue() == permission.getId().longValue()){
                permission.setSelect(true);
            }
            if (!permission.getChildren().isEmpty()){
                toAssignChildren(rolePermission, permission.getChildren());
            }
        }
    }


    @Override
    @Transactional
    public void doAssign(Long roleId, List<Long> permissionId) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        baseMapper.delete(wrapper);
        List<RolePermission> list = new ArrayList<>();
        for (Long id : permissionId){
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(id);
            list.add(rolePermission);
        }
        saveBatch(list);
    }
}
