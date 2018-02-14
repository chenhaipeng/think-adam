package com.thinkme.sys.permission.service.impl;

import com.google.common.collect.Sets;
import com.thinkme.framework.base.service.BaseServiceImpl;
import com.thinkme.sys.permission.entity.Role;
import com.thinkme.sys.permission.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2018-01-24 22:24:42
 */
@Slf4j
@Service(value = "sysRoleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Override
    public Set<Role> findShowRoles(Set<Long> roleIds) {

        Set<Role> roles = Sets.newHashSet();

        List<Role> roleList = query();
        //角色太多用 in ?,不多让他走索引
        for (Role role : roleList) {
            if (Boolean.TRUE.equals(role.getIsShow()) && roleIds.contains(role.getId())) {
                roles.add(role);
            }
        }
        return roles;
    }






}