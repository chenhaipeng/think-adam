package com.thinkme.sys.group.service.impl;

import com.thinkme.BaseTest;
import com.thinkme.sys.group.service.GroupService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/11 下午6:52
 */
public class GroupServiceImplTest extends BaseTest {

    @Autowired
    GroupService groupService;

    @Test
    public void findDefaultGroupIds() throws Exception {
        Set set = groupService.findDefaultGroupIds();
        System.out.println(set);
        assertThat(set).isNotEmpty();

    }

}