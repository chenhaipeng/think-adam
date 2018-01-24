package com.thinkme.sys.group.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.thinkme.framework.base.service.BaseServiceImpl;
import com.thinkme.sys.group.entity.Group;
import com.thinkme.sys.group.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/11 下午5:04
 */
@Service
@Slf4j
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService {


    /**
     * 获取默认分组ids
     *
     * @return
     */
    @Override
    public Set<Long> findDefaultGroupIds() {
        List<Group> list = query("id", Cnd.where("default_group", "=", 1).and("is_show","=",1));
        return Sets.newHashSet(Collections2.transform(list, new Function<Group, Long>() {
            @Nullable
            @Override
            public Long apply(@Nullable Group group) {
                return group.getId();
            }
        }));
    }


    /**
     * 获取可用的分组编号列表
     *
     * @param userId
     * @param organizationIds
     * @return
     */
    @Override
    public Set<Long> findShowGroupIds(Long userId, Set<Long> organizationIds) {
        Set<Long> groupIds = Sets.newHashSet();
        groupIds.addAll(findDefaultGroupIds());
        // TODO: 2018/1/24 不明白sys_group_relation 是否有start_user_id？

        return groupIds;
    }
}
