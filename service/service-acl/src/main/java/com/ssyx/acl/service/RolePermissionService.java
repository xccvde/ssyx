package com.ssyx.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.model.acl.Permission;
import com.ssyx.model.acl.RolePermission;

import java.util.List;

public interface RolePermissionService extends IService<RolePermission> {
    void toAssign(Long roleId, List<Permission> allPermissionList);

    void doAssign(Long roleId, List<Long> permissionId);
}
