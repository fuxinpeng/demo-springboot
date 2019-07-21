package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Book implements Serializable{
	private Integer id;
    private String name;
    private String author;
    private Date createTime;
    @Pattern(regexp = "^1\\d{10}$",message="请输入正确的电话")
    private String phone;
}