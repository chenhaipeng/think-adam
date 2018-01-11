package com.thinkme.sys.organization.service;

import com.thinkme.plugin.service.BaseTreeAbleService;
import com.thinkme.sys.organization.entity.Organization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/10 下午5:21
 */
@Slf4j
@Service
public class OrganizationService extends BaseTreeAbleService<Organization> {


    public void filterForCanShow(Set<Long> organizationIds, Set<Long[]> organizationJobIds) {
        Iterator<Long> iter1 = organizationIds.iterator();

        while (iter1.hasNext()) {
            Long id = iter1.next();
            Organization o = fetch(id);
            if (o == null || Boolean.FALSE.equals(o.getShow())) {
                iter1.remove();
            }
        }

        Iterator<Long[]> iter2 = organizationJobIds.iterator();

        while (iter2.hasNext()) {
            Long id = iter2.next()[0];
            Organization o = fetch(id);
            if (o == null || Boolean.FALSE.equals(o.getShow())) {
                iter2.remove();
            }
        }

    }
}
