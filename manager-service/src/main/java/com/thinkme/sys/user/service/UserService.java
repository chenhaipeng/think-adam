package com.thinkme.sys.user.service;

import com.thinkme.framework.base.service.BaseService;
import com.thinkme.sys.user.entity.User;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/08 下午6:04
 */
public interface UserService extends BaseService<User> {
    User findByUsername(String username);

    User findByEmail(String username);

    User login(String username, String password);

    User findByMobilePhoneNumber(String username);
}
