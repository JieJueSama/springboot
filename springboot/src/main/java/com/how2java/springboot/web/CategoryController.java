package com.how2java.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@GetMapping("/categories")
	public String listCategory(Model m,
			@RequestParam(value = "start", defaultValue = "0") int start,
			@RequestParam(value = "size", defaultValue = "5") int size) throws Exception
	{
		start = start < 0 ? 0 : start;
		
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size, sort);
		Page<Category> page = categoryDAO.findAll(pageable);
		
		System.out.println(page.getNumber());
		System.out.println(page.getNumberOfElements());
		System.out.println(page.getSize());
		System.out.println(page.getTotalElements());
		System.out.println(page.getTotalPages());
		
		m.addAttribute("page", page);
		
		
		return "listCategory";
	}
	
	@PostMapping("/categories")
	public String addCategory(Category c) throws Exception{
		categoryDAO.save(c);
		return "redirect:/categories";
	}
	
	@DeleteMapping("/categories/{id}")
	public String deleteCategory(Category c) throws Exception{
		categoryDAO.delete(c);
		return "redirect:/categories";
	}
	
	@PutMapping("/categories/{id}")
	public String updateCategory(Category c) throws Exception{
		categoryDAO.save(c);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/{id}")
	public String editCategory(@PathVariable("id")int id, Model m) throws Exception{
		Category c = categoryDAO.getOne(id);
		m.addAttribute("c", c);
		return "editCategory";
	}

}
