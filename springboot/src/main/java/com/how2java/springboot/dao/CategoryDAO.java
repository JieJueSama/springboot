package com.how2java.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.springboot.pojo.Category;


//泛型<Category,Integer>表示这个是针对Category类的DAO，Integer表示主键是Integer类型
public interface CategoryDAO extends JpaRepository<Category, Integer> {
	
	public List<Category> findByName(String name);
	
	public List<Category> findByNameLikeAndIdGreaterThanOrderByNameAsc(String name, int id);

}
