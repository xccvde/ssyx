package com.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssyx.acl.mapper.PermissionMapper;
import com.ssyx.acl.util.PermissionUtil;
import com.ssyx.model.acl.Permission;
import com.ssyx.acl.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Override
    public List<Permission> getPermissionList() {
        List<Permission> permissions = baseMapper.selectList(null);
        return PermissionUtil.build(permissions);
    }

    @Override
    public void removeChildById(Long id) {
        List<Long> idList = new ArrayList<>();
        this.getAllPermissionId(id, idList);
        baseMapper.deleteBatchIds(idList);
    }

    private void getAllPermissionId(Long id, List<Long> idList) {
        idList.add(id);
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getPid, id);
        List<Permission> childList = baseMapper.selectList(wrapper);
        if (!childList.isEmpty()){
            for (Permission permission:childList){
                getAllPermissionId(permission.getId(), idList);
            }
        }
    }
}
