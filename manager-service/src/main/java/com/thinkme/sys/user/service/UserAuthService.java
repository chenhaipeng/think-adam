package com.thinkme.sys.user.service;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.thinkme.sys.group.service.GroupService;
import com.thinkme.sys.organization.service.JobService;
import com.thinkme.sys.organization.service.OrganizationService;
import com.thinkme.sys.permisstion.entity.Role;
import com.thinkme.sys.user.entity.User;
import com.thinkme.sys.user.entity.UserOrganizationJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/10 上午11:57
 */
@Service
@Slf4j
public class UserAuthService {

    @Autowired
    UserOrganizationJobService userOrganizationJobService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    JobService jobService;

    @Autowired
    GroupService groupService;


    // TODO: 2018/1/11 这个类需要优化
    public Set<Role> findRoles(User user) {

        if (user == null) {
            return Sets.newHashSet();
        }
        Long userId = user.getId();

        Set<Long[]> organizationJobIds = Sets.newHashSet();
        Set<Long> organizationIds = Sets.newHashSet();
        Set<Long> jobIds = Sets.newHashSet();

        //查询用户关联组织机构
        List<UserOrganizationJob> userOrganizationJobs = userOrganizationJobService.findByUserId(userId);

        for (UserOrganizationJob o : userOrganizationJobs) {
            Long organizationId = o.getOrganizationId();
            Long jobId = o.getJobId();

            if (organizationId != null && jobId != null && organizationId != 0L && jobId != 0L) {
                organizationJobIds.add(new Long[]{organizationId, jobId});
            }
            organizationIds.add(organizationId);
            jobIds.add(jobId);
        }

        //找组织机构祖先
        organizationIds.addAll(organizationService.findAncestorIds(organizationIds));

        //找工作职务的祖先
        jobIds.addAll(jobService.findAncestorIds(jobIds));

//        //过滤组织机构 仅获取目前可用的组织机构数据
        organizationService.filterForCanShow(organizationIds, organizationJobIds);
        jobService.filterForCanShow(jobIds, organizationJobIds);

        //默认分组 + 根据用户编号 和 组织编号 找 分组
        Set<Long> groupIds = groupService.findShowGroupIds(userId, organizationIds);


        return null;
    }

    public Set<String> findStringRoles(User user) {
        Set<Role> roles = findRoles(user);

        return Sets.newHashSet(Collections2.transform(roles, new Function<Role, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Role role) {
                return role.getRole();
            }
        }));
    }


    public Set<String> findStringPermissions(User user) {
        return null;
    }
}
