package com.how2java.springboot.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//表明实体类
@Entity
//表明这个类对应的表名是category_
@Table(name = "category_")
public class Category {
	
	//表明是主键
	@Id
	//表明自增长方式
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//表明对应的数据库字段名
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
