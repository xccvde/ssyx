package com.ssyx.acl.util;

import com.ssyx.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {
    public static List<Permission> build(List<Permission> permissionList) {
        List<Permission> list = new ArrayList<>();
        for (Permission permission : permissionList) {
            if (permission.getPid() == 0) {
                permission.setLevel(1);
                list.add(findChildrenPermission(permission, permissionList));
            }
        }
        return list;
    }

    private static Permission findChildrenPermission(Permission permission, List<Permission> permissionList) {
        List<Permission> children = new ArrayList<>();
        for (Permission child : permissionList) {
            // 判断封装类的值相等的时候调用.xxxValue()
            if (child.getPid().longValue() == permission.getId().longValue()) {
                Integer level = permission.getLevel();
                child.setLevel(level + 1);
                children.add(findChildrenPermission(child, permissionList));
            }
        }

        permission.setChildren(children);
        return permission;
    }
}
