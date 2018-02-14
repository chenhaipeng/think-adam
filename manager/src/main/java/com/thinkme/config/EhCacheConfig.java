package com.thinkme.config;

import com.thinkme.base.cache.EhCacheManagerWrapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/06 下午5:42
 */
@Configuration
@EnableCaching
public class EhCacheConfig {

    /*
     * 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     */
    @Bean(name = "ehCacheManagerFactoryBean")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache/ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    /*
 * ehcache 主要的管理器
 */
    @Bean(name = "springEhCacheManager")
    public EhCacheCacheManager ehCacheCacheManager() {
        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }

    @Bean(name = "ehCacheManager")
    public EhCacheManagerWrapper springCacheManagerWrapper() {
        EhCacheManagerWrapper springCacheManagerWrapper = new EhCacheManagerWrapper();
        springCacheManagerWrapper.setCacheManager(ehCacheCacheManager());
        return springCacheManagerWrapper;
    }

}
