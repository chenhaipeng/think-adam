package com.thinkme.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.thinkme.base.nutz.SpringDaoRunner;
import com.thinkme.framework.utils.SpringUtils;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.DaoRunner;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2017/11/30 下午11:50
 */
@Configuration
public class SpringBeanConfig {

    @Autowired
    Environment env;

    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "125");
        properties.setProperty("kaptcha.image.height", "45");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "springDaoRunner")
    public DaoRunner runner() {
        return new SpringDaoRunner();
    }

    @Bean(name = "dao")
    public Dao dao() {
        NutDao dao = new NutDao();
        dao.setRunner(runner());
        dao.setDataSource(dataSource());
        return dao;
    }

    @Bean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }




}
