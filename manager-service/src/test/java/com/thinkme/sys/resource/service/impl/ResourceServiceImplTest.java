package com.thinkme.sys.resource.service.impl;

import com.thinkme.BaseTest;
import com.thinkme.sys.resource.entity.Resource;
import com.thinkme.sys.resource.service.ResourceService;
import com.thinkme.sys.resource.vo.Menu;
import com.thinkme.sys.user.entity.User;
import com.thinkme.sys.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/26 下午7:58
 */
public class ResourceServiceImplTest extends BaseTest{
    @Autowired
    ResourceService resourceService;

    @Autowired
    UserService userService;

    @Test
    public void findMenus() throws Exception {
        User user = userService.findByUsername("admin");
        List<Menu> menus = resourceService.findMenus(user);
        System.out.println(menus);
        assertThat(menus).isNotEmpty();

    }

    @Test
    public void findAllWithSort() throws Exception {
        List<Resource> list = resourceService.findAllWithSort();
        System.out.println(list);
        assertThat(list.get(0).getParentId()).isGreaterThanOrEqualTo(list.get(1).getParentId());

    }

}