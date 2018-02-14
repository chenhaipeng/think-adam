package com.thinkme.sys.permission.service;

import com.thinkme.framework.base.service.BaseService;
import com.thinkme.sys.permission.entity.Role;

import java.util.Set;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2018-01-24 22:24:42
 */
public interface RoleService extends BaseService<Role>{


    Set<Role> findShowRoles(Set<Long> roleIds);
}