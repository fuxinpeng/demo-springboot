package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync//开启异步注解
@MapperScan(value="com.example.demo.mapper")//mybaits扫描映射器
@SpringBootApplication
public class StudyBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyBootApplication.class, args);
	}

}
