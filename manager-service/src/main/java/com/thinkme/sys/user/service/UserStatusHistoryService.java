package com.thinkme.sys.user.service;

import com.thinkme.framework.base.service.BaseService;
import com.thinkme.sys.user.entity.User;
import com.thinkme.sys.user.entity.UserStatusHistory;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/09 下午5:51
 */
public interface UserStatusHistoryService extends BaseService<UserStatusHistory> {
    String getLastReason(User user);
}
