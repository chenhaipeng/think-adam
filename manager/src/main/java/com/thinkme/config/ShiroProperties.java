package com.thinkme.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/08 下午3:18
 */
@Component
@ConfigurationProperties(prefix = "shiro")
@Data
public class ShiroProperties {

    private String loginUrl;

    private String logoutUrl;

    private String unauthorizedUrl;

    private String defaultSuccessUrl;

    private String adminSuccessUrl;

    private Map<String, String> filterChainDefinitionMap = new HashMap<>();

    private Long sessionGlobalSessionTimeout;

    private Cookie cookie;

    private RememberMe rememberMe;

    @Data
    public static class Cookie {
        private String name;
        private String domain;
        private String path;
        private boolean httpOnly;
        private int maxAge;

    }

    @Data
    public static class RememberMe {
        private String name;
        private String maxAge;

    }


}
