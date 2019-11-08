package com.how2java.springboot.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.how2java.springboot.Application;
import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestJPA {
	
	
	@Autowired
	CategoryDAO dao;
	
	
	//将Category表里的数据都删除了并新增10条
	@Before
	public void before() {
		List<Category> cs = dao.findAll();
		for(Category c : cs) {
			dao.delete(c);
		}
		
		for(int i = 0; i < 10; i++) {
			Category c = new Category();
			c.setName("category " + i);
			dao.save(c);
		}
	}
	
	
	@Test
	public void test() {
		List<Category> cs = dao.findAll();
		for(Category c : cs) {
			System.out.println("c.getName():" + c.getName());
		}
	}
	
	@Test
	public void test1() {
		List<Category> cs = dao.findAll();
		System.out.println("所有的分类信息：");
		for(Category c : cs) {
			System.out.println(c.getName());
		}
		System.out.println();
	}
	
	@Test
	public void test2() {
		System.out.println("查询的名称是 \" category 1\"的分类：");
		List<Category> cs = dao.findByName("category 1");
		for(Category c : cs) {
			System.out.println("c.getName():" + c.getName());
		}
		System.out.println();
	}
	
	@Test
	public void test3() {
		System.out.println("根据名称模糊查询， id大于5，并且名称正排序查询");
		List<Category> cs = dao.findByNameLikeAndIdGreaterThanOrderByNameAsc("%3%", 5);
		for(Category c : cs) {
			System.out.println(c);
		}
		System.out.println();
	}
	
	
	
	
	
	
	
	
	
	

}
