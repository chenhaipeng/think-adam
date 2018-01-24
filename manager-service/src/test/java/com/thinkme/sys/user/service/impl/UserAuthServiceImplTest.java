package com.thinkme.sys.user.service.impl;

import com.google.common.collect.Sets;
import com.thinkme.BaseTest;
import com.thinkme.sys.user.service.UserAuthService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/24 下午5:30
 */
public class UserAuthServiceImplTest extends BaseTest {


    @Autowired
    UserAuthService userAuthService;
    @Test
    public void findRoleIds() throws Exception {
        Set set = Sets.newHashSet(new Long[]{1L, 2L},new Long[]{4L,5L});
        System.out.println(set);
        Set list = userAuthService.findRoleIds(80L, Sets.newHashSet(3L,5L),null,null,set);
        System.out.println(list);


    }

}