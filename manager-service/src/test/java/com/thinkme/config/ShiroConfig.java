package com.thinkme.config;

import com.thinkme.shiro.realm.UserRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
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
    private ShiroProperties shiroProperties;

    /**
     * 在此重点说明这个方法，如果不设置为静态方法会导致bean对象无法注入进来，
     * http://blog.csdn.net/wuxuyang_7788/article/details/70141812
     * http://blog.csdn.net/hengyunabc/article/details/75453307
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, FormAuthenticationFilter formAuthenticationFilter/*,
                                              SysUserFilter sysUserFilter*/) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl(shiroProperties.getLoginUrl());

        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", formAuthenticationFilter);
//        filters.put("sysUser", sysUserFilter);
        shiroFilter.setFilters(filters);
        shiroFilter.setFilterChainDefinitionMap(shiroProperties.getFilterChainDefinitionMap());
        return shiroFilter;

    }

//    @Bean(name = "credentialsMatcher")
//    public RetryLimitHashedCredentialsMatcher credentialsMatcher(EhCacheManagerWrapper cacheManagerWrapper) {
//        RetryLimitHashedCredentialsMatcher credentialsMatcher = new RetryLimitHashedCredentialsMatcher(cacheManagerWrapper);
//        credentialsMatcher.setHashAlgorithmName("md5");
//        credentialsMatcher.setHashIterations(2);
//        credentialsMatcher.setStoredCredentialsHexEncoded(true);
//        return credentialsMatcher;
//    }

    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
//        userRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher);
//        TODO: 2018/1/9  用切面缓存代理了 此处就不缓存
        userRealm.setAuthenticationCachingEnabled(false);
        userRealm.setAuthorizationCachingEnabled(false);
        return userRealm;
    }
//
//    @Bean(name = "sessionFactory")
//    public OnlineSessionFactory sessionFactory() {
//        return new OnlineSessionFactory();
//    }

    @Bean(name = "shiroIdGenerator")
    public JavaUuidSessionIdGenerator idGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    //    会话Cookie模板
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

    @Bean(name = "sessionDAO")
    public EnterpriseCacheSessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
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
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(shiroProperties.getSessionGlobalSessionTimeout());
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie());


        //todo 缓存 调度

        return sessionManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(UserRealm userRealm,
                                                     DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

//    @Bean(name = "sysUserFilter")
//    public SysUserFilter sysUserFilter() {
//        return new SysUserFilter();
//    }

////    退出登录过滤器
//    @Bean
//    public LogoutFilter logoutFilter(){
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setRedirectUrl(env.getProperty("shiro.logoutUrl"));
//        return logoutFilter;
//    }

    //    替换默认的form 验证过滤器
    @Bean
    public FormAuthenticationFilter formAuthenticationFilter() {
        FormAuthenticationFilter authenticationFilter = new FormAuthenticationFilter();
//        表单上的用户名/密码 下次自动登录的参数名
        authenticationFilter.setUsernameParam("username");
        authenticationFilter.setPasswordParam("password");
        authenticationFilter.setRememberMeParam("rememberMe");
        authenticationFilter.setLoginUrl("/login");
        return authenticationFilter;
    }

    /*    aop and other
        For simplest integration, so that all SecurityUtils.* methods work in all cases,
        make the securityManager bean a static singleton.  DO NOT do this in web
        applications - see the 'Web Applications' section below instead.*/
    @Bean
    public MethodInvokingBean methodInvokingBean(SecurityManager securityManager) {
        MethodInvokingBean mBean = new MethodInvokingBean();
        mBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        mBean.setArguments(securityManager);
        return mBean;
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
