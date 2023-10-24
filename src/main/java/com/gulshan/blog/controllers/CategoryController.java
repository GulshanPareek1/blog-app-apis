package com.gulshan.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gulshan.blog.payloads.ApiResponse;
import com.gulshan.blog.payloads.CategoryDto;
import com.gulshan.blog.services.CategoryService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	// controller hi expose karta h hamare resources ko 
	@Autowired
	private CategoryService categoryService;
	
	// create 
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto categoryDto2 = this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.CREATED);
	}
	 
	// update 
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto ,@PathVariable Integer categoryId)
	{
		CategoryDto categoryDto2 = this.categoryService.updateCategory(categoryDto, categoryId);
		
		return new ResponseEntity<CategoryDto>(categoryDto2 , HttpStatus.OK);
	}
	// delete 
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category successfully deleted!!" , true) , HttpStatus.OK);
	}
	
	
	// get 
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId)
	{
//		CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
//		return new ResponseEntity<CategoryDto>(categoryDto , HttpStatus.OK);
		return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
	}
	
	
	// get all 
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories()
	{
		List<CategoryDto> categories = this.categoryService.getCategories();
		return ResponseEntity.ok(categories);
	}
	
}
