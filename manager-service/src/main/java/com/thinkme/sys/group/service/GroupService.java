package com.thinkme.sys.group.service;

import com.thinkme.framework.base.service.BaseService;
import com.thinkme.sys.group.entity.Group;

import java.util.Set;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/11 下午5:04
 */
public interface GroupService extends BaseService<Group> {
    Set<Long> findDefaultGroupIds();

    Set<Long> findShowGroupIds(Long userId, Set<Long> organizationIds);
}
