package com.thinkme.config;

import com.thinkme.shiro.realm.UserRealm;
import com.thinkme.shiro.session.mgt.OnlineSessionDAO;
import com.thinkme.shiro.session.mgt.OnlineSessionFactory;
import com.thinkme.shiro.session.mgt.OnlineWebSessionManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * shiro 权限过滤
 *
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/02 下午12:40
 */
@Configuration
public class ShiroConfig {
    public static final String sessionCacheName = "shiro-activeSessionCache";
    public static final String uidCookieName = "uid";
    public static final String uidCookieDomain = "";
    public static final String uidCookiePath = "/";
    public static final boolean uidCookieHttpOnly = true;
    public static final int uidCookieMaxAge = -1;

    public static final String cipherKey = "adam@2018";

    public static final String rememberMeCookie = "rememberMe";

    @Autowired
    Environment env;

    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
//        用切面缓存代理了 此处就不缓存
        userRealm.setAuthenticationCachingEnabled(false);
        userRealm.setAuthorizationCachingEnabled(false);
        return userRealm;
    }

    @Bean(name = "sessionFactory")
    public OnlineSessionFactory sessionFactory() {
        return new OnlineSessionFactory();
    }

    @Bean(name = "shiroIdGenerator")
    public JavaUuidSessionIdGenerator idGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie = getSimpleCookie(uidCookieName, uidCookieDomain, uidCookiePath,
                uidCookieHttpOnly, uidCookieMaxAge);
        return simpleCookie;
    }

    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        return getSimpleCookie(rememberMeCookie, uidCookieDomain, uidCookiePath, uidCookieHttpOnly, uidCookieMaxAge);
    }

    @Bean(name = "onlineSessionDAO")
    public OnlineSessionDAO onlineSessionDAO() {
        OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
        sessionDAO.setSessionIdGenerator(idGenerator());
        sessionDAO.setActiveSessionsCacheName(sessionCacheName);
        return sessionDAO;
    }

    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode(cipherKey));
        rememberMeManager.setCookie(rememberMeCookie());
        return rememberMeManager;
    }

    //会话管理器
    @Bean(name = "sessionManager")
    public OnlineWebSessionManager sessionManager() {
        OnlineWebSessionManager sessionManager = new OnlineWebSessionManager();
        sessionManager.setGlobalSessionTimeout(Long.parseLong(env.getProperty("shiro.session.globalSessionTimeout")));
        sessionManager.setSessionFactory(sessionFactory());
        sessionManager.setSessionDAO(onlineSessionDAO());
        sessionManager.setDeleteInvalidSessions(false);

        //todo 缓存 调度

        return sessionManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setLoginUrl(env.getProperty("shiro.loginUrl"));
        shiroFilter.setUnauthorizedUrl(env.getProperty("shiro.unauthorizedUrl"));

        //todo filters

        shiroFilter.setFilterChainDefinitionMap(env.getProperty("shiro.filterChainDefinitionMap", Map.class));
        return shiroFilter;

    }

    /*    aop and other
        For simplest integration, so that all SecurityUtils.* methods work in all cases,
        make the securityManager bean a static singleton.  DO NOT do this in web
        applications - see the 'Web Applications' section below instead.*/
    @PostConstruct
    public void setSecurityManager() {
        SecurityUtils.setSecurityManager(securityManager());
    }


    private SimpleCookie getSimpleCookie(String rememberMeCookie, String uidCookieDomain, String uidCookiePath,
                                         boolean uidCookieHttpOnly, int uidCookieMaxAge) {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(rememberMeCookie);
        simpleCookie.setDomain(uidCookieDomain);
        simpleCookie.setPath(uidCookiePath);
        simpleCookie.setHttpOnly(uidCookieHttpOnly);
        simpleCookie.setMaxAge(uidCookieMaxAge);
        return simpleCookie;
    }

}
