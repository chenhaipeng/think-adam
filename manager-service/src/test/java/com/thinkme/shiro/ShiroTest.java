package com.thinkme.shiro;

import com.thinkme.BaseTest;
import com.thinkme.sys.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/25 下午4:59
 */
public class ShiroTest extends BaseTest {

    @Autowired
    UserService userService;

    @Test
    public void test() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        subject.login(token);

        assertThat(subject.isAuthenticated()).isTrue();
        subject.checkRole("admin");
        subject.checkPermission("sys:*");
//
//        userService.changePassword(u1.getId(), password + "1");
//        userRealm.clearCache(subject.getPrincipals());
//
//        token = new UsernamePasswordToken(u1.getUsername(), password + "1");
//        subject.login(token);




    }
}
