package com.thinkme.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2017/11/30 下午11:48
 */
@Configuration
public class CorsFilterConfig {

	@Bean(name = "corsFilterRegistrationBean")
	public FilterRegistrationBean filterRegistrationBean() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(),
				HttpMethod.OPTIONS.name()));
		corsConfig.setAllowedHeaders(Arrays.asList(CorsConfiguration.ALL));
		corsConfig.setExposedHeaders(Arrays.asList(HttpHeaders.SET_COOKIE));

		UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
		corsConfigSource.registerCorsConfiguration("/**", corsConfig);
		CorsFilter corsFilter = new CorsFilter(corsConfigSource);
		return new FilterRegistrationBean(corsFilter);
	}
}
