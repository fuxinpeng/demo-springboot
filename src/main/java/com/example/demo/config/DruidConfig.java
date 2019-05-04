package com.example.demo.config;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.assertj.core.util.Arrays;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {
	
	@ConfigurationProperties(prefix="spring.datasource.druid")
	@Bean
	public DataSource getDruidDataSource() {
		return new DruidDataSource();
	}
	
	//配置Druid的监控
	//1、配置一个管理后台的servlet
//	@Bean
//	public ServletRegistrationBean statViewServlet() {
//		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
//		Map<String,String> intiParams = new HashMap<String,String>();
//		intiParams.put("loginUsername", "admin");
//		intiParams.put("loginPassword", "123456");
//		intiParams.put("allow", "");
//		bean.setInitParameters(intiParams);
// 		return bean;
//	}
	
	@Bean
	public ServletRegistrationBean<StatViewServlet> statViewServlet() {
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(),"/druid/*");
		Map<String,String> intiParams = new HashMap<String,String>();
		intiParams.put("loginUsername", "admin");
		intiParams.put("loginPassword", "123456");
		intiParams.put("allow", "");
		bean.setInitParameters(intiParams);
 		return bean;
	}
	
	//2、配置一个监控的filter
	@Bean
	public FilterRegistrationBean<WebStatFilter> WebStatFilter(){
		FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<WebStatFilter>();	
		bean.setFilter(new WebStatFilter());
		Map<String,String> intiParams = new HashMap<String,String>();
		intiParams.put("exclusions", "*.js,*.css,/druid/*");
		bean.setInitParameters(intiParams);
		List<String> list = new ArrayList<String>();
		list.add("/*");
		bean.setUrlPatterns(list);
		return bean;
	}
}
