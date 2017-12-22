package com.thinkme.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = DruidProperties.DRUID_PREFIX)
public class DruidProperties {

    public static final String DRUID_PREFIX = "druid";

    private String filters;

    private String loginUsername;

    private String loginPassword;

    private String allow;

    private String sessionStatEnable;

    private String sessionStatMaxCount;

    private String profileEnable;

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getSessionStatEnable() {
        return sessionStatEnable;
    }

    public void setSessionStatEnable(String sessionStatEnable) {
        this.sessionStatEnable = sessionStatEnable;
    }

    public String getSessionStatMaxCount() {
        return sessionStatMaxCount;
    }

    public void setSessionStatMaxCount(String sessionStatMaxCount) {
        this.sessionStatMaxCount = sessionStatMaxCount;
    }

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    public String getProfileEnable() {
        return profileEnable;
    }

    public void setProfileEnable(String profileEnable) {
        this.profileEnable = profileEnable;
    }


}
