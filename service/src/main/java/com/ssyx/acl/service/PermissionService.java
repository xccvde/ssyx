package com.ssyx.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.model.acl.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<Permission> getPermissionList();

    void removeChildById(Long id);
}
