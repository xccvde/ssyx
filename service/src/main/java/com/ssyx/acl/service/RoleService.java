package com.ssyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.model.acl.Role;
import com.ssyx.vo.acl.RoleQueryVo;

import java.util.List;

public interface RoleService extends IService<Role> {
    IPage<Role> pageQuery(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    List<Role> getAllRole();
}
