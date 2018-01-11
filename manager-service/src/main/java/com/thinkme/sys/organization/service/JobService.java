package com.thinkme.sys.organization.service;

import com.thinkme.plugin.service.BaseTreeAbleService;
import com.thinkme.sys.organization.entity.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/11 下午12:50
 */
@Service
@Slf4j
public class JobService extends BaseTreeAbleService<Job> {


    public void filterForCanShow(Set<Long> jobIds, Set<Long[]> organizationJobIds) {
        Iterator<Long> iter1 = jobIds.iterator();

        while (iter1.hasNext()) {
            Long id = iter1.next();
            Job o = fetch(id);
            if (o == null || Boolean.FALSE.equals(o.getShow())) {
                iter1.remove();
            }
        }

        Iterator<Long[]> iter2 = organizationJobIds.iterator();

        while (iter2.hasNext()) {
            Long id = iter2.next()[1];
            Job o = fetch(id);
            if (o == null || Boolean.FALSE.equals(o.getShow())) {
                iter2.remove();
            }
        }

    }
}
