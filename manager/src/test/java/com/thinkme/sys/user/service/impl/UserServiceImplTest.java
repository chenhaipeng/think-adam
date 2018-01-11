package com.thinkme.sys.user.service.impl;

import com.thinkme.Application;
import com.thinkme.sys.user.entity.User;
import com.thinkme.sys.user.entity.UserStatus;
import com.thinkme.sys.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/09 上午11:02
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void findByUsername() throws Exception {
        User user = userService.findByUsername("admin");
        System.out.println(user);

        user.setStatus(UserStatus.blocked);
        userService.updateIgnoreNull(user);
        Assert.assertNotNull(user);

    }

}