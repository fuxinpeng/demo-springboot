package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.druid.pool.DruidDataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyBootApplicationTests {

	@Autowired
	private DataSource dataSource;
//	@Autowired
//	private RabbitTemplate rabbitTemplate;
//	@Autowired
//	private AmqpAdmin amqpAdmin;
	
	@Test
	public void getDataSource() {
		System.out.println(dataSource.getClass());
		DruidDataSource dds = (DruidDataSource)dataSource;
		System.out.println(dds.getMaxActive());
	}
	
//	@Test
//	public void testExchangeDirect() {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("msg", "这是第一个消息");
//		map.put("data", Arrays.asList("helloworld",123,true));
//		rabbitTemplate.convertAndSend("exchange.direct", "kingdee",map);
//	}
//	
//	@Test
//	public void receive() {
//		Object o = rabbitTemplate.receiveAndConvert("kingdee");
//		System.out.println(o.getClass());
//		System.out.println(o);
//	}
//	
//	@Test
//	public void createExchange() {
//		amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//		amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
//		amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,
//				"amqpadmin.exchange", "amqpadmin.queue", null));
//	}
//	
//	@Test
//	public void deleteExchange() {
//		amqpAdmin.deleteExchange("amqpadmin.exchange");
//		amqpAdmin.deleteQueue("amqpadmin.queue");
//	}

	@Test
	public void testStr(){
		String a = "a" +"b";
		String b = "ab";
		System.out.println(a==b);
	}

}
