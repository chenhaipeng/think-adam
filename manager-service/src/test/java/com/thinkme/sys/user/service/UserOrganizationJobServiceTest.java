package com.thinkme.sys.user.service;

import com.thinkme.TestApplication;
import com.thinkme.sys.user.entity.UserOrganizationJob;
import com.thinkme.utils.json.FastJsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/10 下午5:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class UserOrganizationJobServiceTest {

    @Autowired
    UserOrganizationJobService userOrganizationJobService;

    @Test
    public void findByUserId() throws Exception {
        List<UserOrganizationJob> list = userOrganizationJobService.findByUserId(1L);
        System.out.println(FastJsonUtil.object2String(list));
        assertThat(list.get(0).getId()).isEqualTo(1L);


    }

}