package com.how2java.springboot.service;

import org.springframework.data.domain.Pageable;

import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.util.Page4Navigator;

public interface CategoryService {
	
	public Page4Navigator<Category> list(Pageable pageable);
	
	public void save(Category category);
	
	public void delete(int id);
	
	public Category get(int id);

}
