package com.example.demo;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.druid.pool.DruidDataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyBootApplicationTests {

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void getDataSource() {
		System.out.println(dataSource.getClass());
		DruidDataSource dds = (DruidDataSource)dataSource;
		System.out.println(dds.getMaxActive());
	}

}
