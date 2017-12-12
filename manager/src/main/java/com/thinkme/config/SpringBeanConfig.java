package com.thinkme.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

	@Bean(name="captchaProducer")
	public DefaultKaptcha getKaptchaBean(){
		DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
		Properties properties=new Properties();
		properties.setProperty("kaptcha.border", "yes");
		properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
		properties.setProperty("kaptcha.border.color", "105,179,90");
		properties.setProperty("kaptcha.textproducer.font.color", "blue");
		properties.setProperty("kaptcha.image.width", "125");
		properties.setProperty("kaptcha.image.height", "45");
		properties.setProperty("kaptcha.session.key", "code");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		Config config=new Config(properties);
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}

	@Bean(name = "dataSource")
	@Qualifier("dataSource")
	@ConfigurationProperties(prefix="spring.datasource")//加载所有以spring.datasource开头的参数来初始化连接池
	public DataSource dataSource() {
		return new DruidDataSource();
	}

}
