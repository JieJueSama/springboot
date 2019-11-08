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
import com.how2java.springboot.service.CategoryService;
import com.how2java.springboot.util.Page4Navigator;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/categories")
	public String listCategory(Model m,
			@RequestParam(value = "start", defaultValue = "0") int start,
			@RequestParam(value = "size", defaultValue = "5") int size) throws Exception
	{
		start = start < 0 ? 0 : start;
		
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size, sort);
		Page4Navigator<Category> page = categoryService.list(pageable);
		
//		System.out.println(page.getNumber());
//		System.out.println(page.getNumberOfElements());
//		System.out.println(page.getSize());
//		System.out.println(page.getTotalElements());
//		System.out.println(page.getTotalPages());
		
		m.addAttribute("page", page);
		
		
		return "listCategory";
	}
	
	@PostMapping("/categories")
	public String addCategory(Category c) throws Exception{
		categoryService.save(c);
		return "redirect:/categories";
	}
	
	@DeleteMapping("/categories/{id}")
	public String deleteCategory(Category c) throws Exception{
		categoryService.delete(c.getId());
		return "redirect:/categories";
	}
	
	@PutMapping("/categories/{id}")
	public String updateCategory(Category c) throws Exception{
		categoryService.save(c);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/{id}")
	public String editCategory(@PathVariable("id")int id, Model m) throws Exception{
		Category c = categoryService.get(id);
		m.addAttribute("c", c);
		return "editCategory";
	}

}
