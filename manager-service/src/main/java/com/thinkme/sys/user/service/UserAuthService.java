package com.thinkme.sys.user.service;

import com.thinkme.framework.base.service.BaseService;
import com.thinkme.sys.user.entity.Auth;

import java.util.Set;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/24 下午3:46
 */
public interface UserAuthService extends BaseService<Auth> {
    Set<Long> findRoleIds(Long userId, Set<Long> groupIds, Set<Long> organizationIds, Set<Long> jobIds,
                          Set<Long[]> organizationJobIds);
}
