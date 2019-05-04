package com.example.demo.service;

import java.util.List;

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
	
}
