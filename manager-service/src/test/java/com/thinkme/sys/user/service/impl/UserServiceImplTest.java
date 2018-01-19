package com.thinkme.sys.user.service.impl;

import com.thinkme.TestApplication;
import com.thinkme.sys.user.entity.User;
import com.thinkme.sys.user.entity.UserStatus;
import com.thinkme.sys.user.entity.UserVo;
import com.thinkme.sys.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/09 上午11:02
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    Dao dao;

    @Test
    public void findByUsername() throws Exception {
        User user = userService.findByUsername("admin");
        System.out.println(user);

        user.setStatus(UserStatus.blocked);
        userService.updateIgnoreNull(user);
        Assert.assertNotNull(user);

    }

    @Test
    public void testFindVo(){
        Sql sql = Sqls.create("SELECT * FROM sys_user $condition");
        sql.setCallback(Sqls.callback.entities());
        Entity<UserVo> entity = dao.getEntity(UserVo.class);
        sql.setEntity(entity);
        dao.execute(sql);
        List<UserVo> list = sql.getList(UserVo.class);
        System.out.println(list);
    }

}