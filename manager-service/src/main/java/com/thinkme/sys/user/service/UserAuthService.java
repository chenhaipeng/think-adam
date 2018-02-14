package com.thinkme.sys.user.service;

import com.thinkme.framework.base.service.BaseService;
import com.thinkme.sys.permission.entity.Role;
import com.thinkme.sys.user.entity.Auth;
import com.thinkme.sys.user.entity.User;

import java.util.Set;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/24 下午3:46
 */
public interface UserAuthService extends BaseService<Auth> {
    // TODO: 2018/1/11 这个类需要优化
    Set<Role> findRoles(User user);

    Set<String> findStringRoles(User user);

    // TODO: 2018/1/25 这些应该用关系查询而不是一个一个单独查
    Set<String> findStringPermissions(User user);

    Set<Long> findRoleIds(Long userId, Set<Long> groupIds, Set<Long> organizationIds, Set<Long> jobIds,
                          Set<Long[]> organizationJobIds);
}
