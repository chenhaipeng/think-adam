package com.thinkme.sys.resource.service;

import com.thinkme.BaseTest;
import com.thinkme.sys.resource.entity.Resource;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/25 下午3:57
 */
public class ResourceServiceTest extends BaseTest{

    @Autowired
    Dao dao;

    @Test
    public void testBoolean(){
        Resource resource = dao.fetch(Resource.class,1);
        System.out.println(resource);

    }

}