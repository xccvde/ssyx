package com.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.acl.mapper.RoleMapper;
import com.ssyx.model.acl.Role;
import com.ssyx.acl.service.RoleService;
import com.ssyx.vo.acl.RoleQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>  implements RoleService {

    @Override
    public IPage<Role> pageQuery(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        String name = roleQueryVo.getRoleName();
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like(Role::getRoleName, name);
        }
        Page<Role> rolePage = baseMapper.selectPage(pageParam, wrapper);
        return rolePage;
    }

    @Override
    public List<Role> getAllRole() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        return baseMapper.selectList(wrapper);
    }

}
