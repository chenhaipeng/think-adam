package com.thinkme.sys.user.service;

import com.thinkme.framework.base.service.BaseService;
import com.thinkme.sys.user.entity.UserOrganizationJob;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/10 下午5:34
 */
public interface UserOrganizationJobService extends BaseService<UserOrganizationJob> {
    List<UserOrganizationJob> findByUserId(Long userId);
}
