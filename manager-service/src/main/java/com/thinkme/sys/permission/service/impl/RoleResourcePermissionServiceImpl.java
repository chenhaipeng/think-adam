package com.thinkme.sys.permission.service.impl;

import com.thinkme.framework.base.service.BaseServiceImpl;
import com.thinkme.sys.permission.entity.RoleResourcePermission;
import com.thinkme.sys.permission.service.RoleResourcePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2018-01-24 22:24:42
 */
@Slf4j
@Service
public class RoleResourcePermissionServiceImpl extends BaseServiceImpl<RoleResourcePermission> implements RoleResourcePermissionService {

    @Override
    public List<RoleResourcePermission> findByRoleId(Long roleId){
        return query(Cnd.where("role_id","=",roleId));

    }


}