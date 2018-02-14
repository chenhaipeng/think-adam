package com.thinkme.sys.resource.service;

import com.thinkme.framework.base.service.BaseService;
import com.thinkme.sys.resource.entity.Resource;
import com.thinkme.sys.resource.vo.Menu;
import com.thinkme.sys.user.entity.User;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2018-01-25 14:33:21
 */
public interface ResourceService extends BaseService<Resource>{


    String findActualResourceIdentity(Resource resource);

    List<Resource> findAllWithSort();

    List<Menu> findMenus(User user);
}