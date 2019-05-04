package com.example.demo.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;

public class MybatisConfig {
	
	public ConfigurationCustomizer configurationCustomizer() {
		return new ConfigurationCustomizer() {
			@Override
			public void customize(Configuration configuration) {
				//设置驼峰命名法,_a会自动转换成A
				configuration.setMapUnderscoreToCamelCase(true);
			}
		};
	}
	
}
