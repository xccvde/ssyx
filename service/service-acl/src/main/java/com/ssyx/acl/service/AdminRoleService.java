package com.ssyx.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.model.acl.AdminRole;
import com.ssyx.model.acl.Role;

import java.util.List;

public interface AdminRoleService extends IService<AdminRole> {


    void saveAdminRole(Long adminId, List<Long> roleIds);
}
