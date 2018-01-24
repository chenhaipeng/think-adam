package com.thinkme.sys.user.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.thinkme.framework.base.service.BaseServiceImpl;
import com.thinkme.sys.group.service.GroupService;
import com.thinkme.sys.organization.service.JobService;
import com.thinkme.sys.organization.service.OrganizationService;
import com.thinkme.sys.permisstion.entity.Role;
import com.thinkme.sys.user.entity.Auth;
import com.thinkme.sys.user.entity.User;
import com.thinkme.sys.user.entity.UserOrganizationJob;
import com.thinkme.sys.user.service.UserAuthService;
import com.thinkme.sys.user.service.UserOrganizationJobService;
import com.thinkme.utils.text.MoreStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Iterator;
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
public class UserAuthServiceImpl extends BaseServiceImpl<Auth> implements UserAuthService {

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

        this.findRoleIds(userId, groupIds, organizationIds, jobIds, organizationJobIds);


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


    /**
     * 根据用户信息获取 角色
     * 1.1、用户  根据用户绝对匹配
     * 1.2、组织机构 根据组织机构绝对匹配 此处需要注意 祖先需要自己获取
     * 1.3、工作职务 根据工作职务绝对匹配 此处需要注意 祖先需要自己获取
     * 1.4、组织机构和工作职务  根据组织机构和工作职务绝对匹配 此处不匹配祖先
     * 1.5、组  根据组绝对匹配
     *
     * @param userId
     * @param groupIds
     * @param organizationIds
     * @param jobIds
     * @param organizationJobIds
     * @return
     */
    @Override
    public Set<Long> findRoleIds(Long userId, Set<Long> groupIds, Set<Long> organizationIds, Set<Long> jobIds,
                                 Set<Long[]> organizationJobIds) {

        boolean hasGroupIds = groupIds != null && groupIds.size() > 0;
        boolean hasOrganizationIds = organizationIds != null && organizationIds.size() > 0;
        boolean hasJobIds = jobIds != null && jobIds.size() > 0;
        boolean hasOrganizationJobIds = organizationJobIds != null && organizationJobIds.size() > 0;
        StringBuilder sb = new StringBuilder("select role_ids from sys_auth where user_id = @userId ");
        if (hasGroupIds) {
            sb.append("or (group_id in (@groupStr))");
        }
        if (hasOrganizationIds) {
            sb.append("or (organization_id in (@organizationIdStr))");
        }
        if (hasJobIds) {
            sb.append("or (job_id in (@jobIdStr))");
        }
        if (hasOrganizationJobIds) {
            for (int i = 0; i < organizationJobIds.size(); i++) {
                sb.append(" or ");
                sb.append("( organization_id = @organizationId_").append(i).append(" and job_id= @jobId_").append(i).append(" )");
            }
        }
        Sql sql = Sqls.create(sb.toString());


        sql.setParam("userId", userId);
        if (hasGroupIds) {
            String groupStr = MoreStringUtil.dbInString(groupIds);
            sql.setParam("groupStr", groupStr);
        }
        if (hasOrganizationIds) {
            String organizationIdStr = MoreStringUtil.dbInString(organizationIds);
            sql.setParam("organizationIdStr", organizationIdStr);
        }
        if (hasJobIds) {
            String jobIdStr = MoreStringUtil.dbInString(jobIds);
            sql.setParam("jobIdStr", jobIdStr);
        }

        int i = 0;
        Iterator<Long[]> iterator = organizationJobIds.iterator();
        while (iterator.hasNext()) {
            Long[] longs = iterator.next();
            sql.setParam("organizationId_" + i, longs[0]).setParam("jobId_" + i, longs[1]);
            i++;
        }

        List<Auth> list = query(sql);

        Set<Long> roleIds = Sets.newHashSet();
        for (Auth auth : list) {
            List authList = Arrays.asList(auth.getRoleIds().split(","));
            roleIds.addAll(authList);
        }

        return roleIds;

    }
}
