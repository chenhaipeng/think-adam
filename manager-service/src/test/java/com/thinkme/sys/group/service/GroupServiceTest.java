package com.thinkme.sys.group.service;

import com.thinkme.TestApplication;
import com.thinkme.sys.group.entity.Group;
import com.thinkme.sys.group.entity.GroupType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/11 下午5:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class GroupServiceTest {

    @Autowired
    GroupService groupService;

    @Test
    public void testSave() {
        Group group = new Group();
        group.setName("test");
        group.setDefaultGroup(true);
        group.setShow(true);
        group.setType(GroupType.organization);
        groupService.fastInsert(group);

    }

}