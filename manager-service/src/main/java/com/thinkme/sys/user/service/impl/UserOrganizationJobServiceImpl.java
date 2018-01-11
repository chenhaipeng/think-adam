package com.thinkme.sys.user.service.impl;

import com.thinkme.framework.base.service.BaseServiceImpl;
import com.thinkme.sys.user.entity.UserOrganizationJob;
import com.thinkme.sys.user.service.UserOrganizationJobService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/10 下午5:34
 */
@Service
@Slf4j
public class UserOrganizationJobServiceImpl extends BaseServiceImpl<UserOrganizationJob> implements UserOrganizationJobService {


    @Override
    public List<UserOrganizationJob> findByUserId(Long userId) {
        return query(Cnd.where("user_id", "=", userId));
    }
}
