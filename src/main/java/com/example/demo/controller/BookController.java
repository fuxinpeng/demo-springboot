package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/list")
	public List<Book> getLis(){
		return bookService.getList();
	}
	
}
