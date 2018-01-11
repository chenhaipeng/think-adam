package com.thinkme.plugin.service;

import com.google.common.collect.Sets;
import com.thinkme.framework.base.service.BaseServiceImpl;
import com.thinkme.plugin.entity.Treeable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 操作树基础类
 *
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/10 下午6:14
 */
@Service
@Slf4j
public class BaseTreeAbleService<T extends Treeable> extends BaseServiceImpl<T> {


    public Set<Long> findAncestorIds(Iterable<Long> currentIds) {
        Set parents = Sets.newHashSet();
        for (Long currentId : currentIds) {
            parents.addAll(findAncestorIds(currentId));
        }

        return parents;
    }

    private Set<Long> findAncestorIds(Long currentId) {
        Set ids = Sets.newHashSet();
        T t = fetch(currentId);
        if (t == null) {
            return ids;
        }
        for (String idStr : StringUtils.split(t.getParentIds(), "/")) {
            if (StringUtils.isNotEmpty(idStr)) {
                ids.add(Long.valueOf(idStr));
            }

        }

        return ids;
    }

    public boolean isHasChildren(Long id) {
        int count = count(Cnd.where("parent_id", "=", id));
        if (count > 0) {
            return true;
        }
        return false;

    }
}
