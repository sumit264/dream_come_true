package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.payloads.CategoryDto;

public interface CategoryService {

	//insert
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	//update
	

	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	//delete
	

	public void deleteCategory(Integer categoryId);
	//get
	

	public CategoryDto getCategory(Integer categoryId);
	//get All
	

	public List<CategoryDto> getcategories();
}
