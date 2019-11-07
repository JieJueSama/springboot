package com.how2java.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@RequestMapping("/listCategory")
	public String listCategory(Model m) throws Exception{
		List<Category> cs = categoryDAO.findAll();
		m.addAttribute("cs", cs);
		
		return "listCategory";
	}
	
	@RequestMapping("/addCategory")
	public String addCategory(Category c) throws Exception{
		categoryDAO.save(c);
		return "redirect:listCategory";
	}
	
	@RequestMapping("/deleteCategory")
	public String deleteCategory(Category c) throws Exception{
		categoryDAO.delete(c);
		return "redirect:listCategory";
	}
	
	@RequestMapping("/updateCategory")
	public String updateCategory(Category c) throws Exception{
		categoryDAO.save(c);
		return "redirect:listCategory";
	}
	
	@RequestMapping("/editCategory")
	public String editCategory(int id, Model m) throws Exception{
		Category c = categoryDAO.getOne(id);
		m.addAttribute("c", c);
		return "editCategory";
	}

}
