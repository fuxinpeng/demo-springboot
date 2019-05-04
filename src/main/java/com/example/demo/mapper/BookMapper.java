package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.Book;

public interface BookMapper {
    int insert(Book record);
    
    List<Book> getList();
}