package com.thinkme.sys.permission.service.impl;

import com.thinkme.BaseTest;
import com.thinkme.sys.permission.entity.Role;
import com.thinkme.sys.permission.service.RoleService;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/25 上午11:00
 */
public class RoleServiceImplTest extends BaseTest{

    @Autowired
    RoleService roleService;

    @Autowired
    Dao dao;

    @Test
    public void testManyToMany(){

        Role role = dao.fetch(Role.class,1);
        System.out.println(role);
        assertThat(role.getId()).isEqualTo(1);



    }

}