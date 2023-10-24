package com.gulshan.blog.services;

import com.gulshan.blog.payloads.CategoryDto;
import java.util.List;

public interface CategoryService {

	// interface pe hame public , private lagane ki jarurat nahi h 
	
	//create 
	CategoryDto createCategory(CategoryDto categoryDto);
	//update 
	CategoryDto updateCategory(CategoryDto categoryDto , Integer categoryId);
	//delete 
	void deleteCategory(Integer categoryId);
	//get single 
	CategoryDto getCategory(Integer categoryId);
	// get all
	List<CategoryDto> getCategories();
	
}
// interface  ka concept ham sirf loose coupling ke liye use kar rahe h 