package com.thinkme.sys.permission.service;

import com.thinkme.framework.base.service.BaseService;
import com.thinkme.sys.permission.entity.RoleResourcePermission;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2018-01-24 22:24:42
 */
public interface RoleResourcePermissionService extends BaseService<RoleResourcePermission>{


    List<RoleResourcePermission> findByRoleId(Long roleId);
}