package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;

@Service
public class BookService{

	@Autowired
	private BookMapper bookMapper;
	
	public List<Book> getList() {
		return bookMapper.getList();
	}
	
	//配置多个队列
	//@RabbitListener(queues= "kingdee")
	//@RabbitListener(queues= {"kingdee","kingdee.zwy"})
	public void receiveRabbitMQ(Map<String,Object> map) {
		System.out.println("收到消息:"+map);
	}
	
}
