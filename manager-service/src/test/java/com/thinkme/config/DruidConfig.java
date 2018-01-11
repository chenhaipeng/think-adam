package com.thinkme.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Map;

@Configuration
@ConditionalOnClass({javax.servlet.http.HttpServlet.class, DruidDataSource.class})
@Import(DruidProperties.class)
public class DruidConfig {

    @Autowired
    private DruidProperties properties;

    @Bean
    public ServletRegistrationBean druidServlet() {
        StatViewServlet servlet = new StatViewServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername", properties.getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword", properties.getLoginPassword());
        servletRegistrationBean.addInitParameter("allow", properties.getAllow());
        return servletRegistrationBean;
    }

    @Bean(name = "druidFilterRegistrationBean")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        Map<String, String> initParameters = Maps.newHashMap();
        initParameters.put("sessionStatMaxCount", properties.getSessionStatMaxCount());
        initParameters.put("profileEnable", properties.getProfileEnable());
        initParameters.put("sessionStatEnable", properties.getSessionStatEnable());
        WebStatFilter filter = new WebStatFilter();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setInitParameters(initParameters);
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
