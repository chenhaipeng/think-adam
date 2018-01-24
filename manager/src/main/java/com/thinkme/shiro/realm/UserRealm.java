package com.thinkme.shiro.realm;

import com.thinkme.sys.user.entity.User;
import com.thinkme.sys.user.exception.*;
import com.thinkme.sys.user.service.impl.UserAuthServiceImpl;
import com.thinkme.sys.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/08 下午5:56
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthServiceImpl userAuthServiceImpl;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        User user = userService.findByUsername(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userAuthServiceImpl.findStringRoles(user));
        authorizationInfo.setStringPermissions(userAuthServiceImpl.findStringPermissions(user));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername().trim();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        User user;
        try {
            user = userService.login(username, password);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserPasswordNotMatchException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserPasswordRetryLimitExceedException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (UserBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            log.error("login error", e);
            throw new AuthenticationException(new UserException("user.unknown.error", null));
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), password.toCharArray(), getName());
        return info;
    }
}
