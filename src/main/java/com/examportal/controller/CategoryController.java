package com.examportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.model.exam.Category;

import com.examportal.service.CategoryService;
@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// add Category
	@PostMapping("/")
	public ResponseEntity<?> addCategory(@RequestBody Category category) {
		Category addCategory = this.categoryService.addCategory(category);
		return ResponseEntity.ok(addCategory);

	}
	
	// get Category
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable("categoryId") Long categoryId){
		return this.categoryService.getCategory(categoryId);
		
	}
	
	// get All Category
	@GetMapping("/")
	public ResponseEntity<?> getCategories(){
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	// update Category
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryService.updateCategory(category);
		
	}
	
	// delete Category
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}

}
