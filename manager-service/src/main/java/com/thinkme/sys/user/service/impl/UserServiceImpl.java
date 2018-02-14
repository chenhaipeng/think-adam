package com.thinkme.sys.user.service.impl;

import com.thinkme.framework.base.service.BaseServiceImpl;
import com.thinkme.framework.utils.SpringUtils;
import com.thinkme.sys.user.entity.User;
import com.thinkme.sys.user.entity.UserStatus;
import com.thinkme.sys.user.exception.UserBlockedException;
import com.thinkme.sys.user.exception.UserNotExistsException;
import com.thinkme.sys.user.exception.UserPasswordNotMatchException;
import com.thinkme.sys.user.service.PasswordService;
import com.thinkme.sys.user.service.UserService;
import com.thinkme.sys.user.service.UserStatusHistoryService;
import com.thinkme.sys.user.utils.UserLogUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/08 下午6:06
 */
@Service("sysUserService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {


    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserStatusHistoryService userStatusHistoryService;

    @Override
    public User findByUsername(String username) {
        return dao.fetch(User.class, Cnd.where("username", "=", username));
    }

    @Override
    public User findByEmail(String username) {
        return dao.fetch(User.class, Cnd.where("email", "=", username));
    }

    @Override
    public User findByMobilePhoneNumber(String username) {
        return dao.fetch(User.class, Cnd.where("mobile_phone_number", "=", username));
    }


    @Override
    @Transactional
    public User login(String username, String password) {

         if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "username is empty");
            throw new UserNotExistsException();
        }
        //密码如果不在指定范围内 肯定错误
        if (password.length() < User.PASSWORD_MIN_LENGTH || password.length() > User.PASSWORD_MAX_LENGTH) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "password length error! password is between {} and {}",
                    User.PASSWORD_MIN_LENGTH, User.PASSWORD_MAX_LENGTH);

            throw new UserPasswordNotMatchException();
        }
        User user = null;

        //此处需要走代理对象，目的是能走缓存切面
        UserService proxyUserService = SpringUtils.getBean("sysUserService");
        if (maybeUsername(username)) {
            user = proxyUserService.findByUsername(username);
        }

        if (user == null && maybeEmail(username)) {
            user = proxyUserService.findByEmail(username);
        }

        if (user == null && maybeMobilePhoneNumber(username)) {
            user = proxyUserService.findByMobilePhoneNumber(username);
        }

        if (user == null || Boolean.TRUE.equals(user.getDeleted())) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "user is not exists!");

            throw new UserNotExistsException();
        }

        passwordService.validate(user, password);

        if (user.getStatus() == UserStatus.blocked) {
            UserLogUtils.log(
                    username,
                    "loginError",
                    "user is blocked!");
            throw new UserBlockedException(userStatusHistoryService.getLastReason(user));
        }

        UserLogUtils.log(
                username,
                "loginSuccess",
                "");
        return user;
    }


    private boolean maybeMobilePhoneNumber(String username) {
        if (!username.matches(User.MOBILE_PHONE_NUMBER_PATTERN)) {
            return false;
        }
        return true;
    }

    private boolean maybeUsername(String username) {
        if (!username.matches(User.USERNAME_PATTERN)) {
            return false;
        }
        //如果用户名不在指定范围内也是错误的
        if (username.length() < User.USERNAME_MIN_LENGTH || username.length() > User.USERNAME_MAX_LENGTH) {
            return false;
        }

        return true;
    }

    private boolean maybeEmail(String username) {
        if (!username.matches(User.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }


}
