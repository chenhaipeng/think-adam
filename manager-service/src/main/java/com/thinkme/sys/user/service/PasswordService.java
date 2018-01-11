package com.thinkme.sys.user.service;

import com.thinkme.framework.utils.Md5Utils;
import com.thinkme.sys.user.entity.User;
import com.thinkme.sys.user.exception.UserPasswordNotMatchException;
import com.thinkme.sys.user.exception.UserPasswordRetryLimitExceedException;
import com.thinkme.sys.user.utils.UserLogUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/09 上午11:28
 */
@Service
@Slf4j
public class PasswordService {

    @Autowired
    private net.sf.ehcache.CacheManager ehcacheManager;

    private Cache loginRecordCache;

    private int maxRetryCount = 10;

    @PostConstruct
    public void init() {
        loginRecordCache = ehcacheManager.getCache("loginRecordCache");
    }


    public void validate(User user, String password) {
        String username = user.getUsername();

        int retryCount = 0;

        Element cacheElement = loginRecordCache.get(username);
        if (cacheElement != null) {
            retryCount = (Integer) cacheElement.getObjectValue();
            if (retryCount >= maxRetryCount) {
                UserLogUtils.log(
                        username,
                        "passwordError",
                        "password error, retry limit exceed! password: {},max retry count {}",
                        password, maxRetryCount);
                throw new UserPasswordRetryLimitExceedException(maxRetryCount);
            }
        }
        if (!matches(user, password)) {
            loginRecordCache.put(new Element(username, ++retryCount));
            UserLogUtils.log(
                    username,
                    "passwordError",
                    "password error! password: {} retry count: {}",
                    password, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(username);
        }


    }

    public boolean matches(User user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUsername(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String username) {
        loginRecordCache.remove(username);
    }


    public String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }


}
