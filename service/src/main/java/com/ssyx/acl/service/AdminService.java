package com.ssyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssyx.model.acl.Admin;
import com.ssyx.model.acl.Role;
import com.ssyx.model.user.User;
import com.ssyx.vo.acl.AdminQueryVo;
import com.ssyx.vo.user.UserQueryVo;

import java.util.List;

public interface AdminService extends IService<Admin> {

    IPage<Admin> pageQuery(Page<Admin> pageParam, AdminQueryVo adminQueryVo);

    List<Role> getRoles(String adminId);
}
