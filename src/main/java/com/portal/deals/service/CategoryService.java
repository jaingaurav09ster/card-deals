package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Category;

public interface CategoryService {

	List<Category> listAllCategories();
	
	List<Category> listAllRootCategories();

	Category getCategoryById(Integer id);

	void deleteCategoryById(Integer id);

	void saveCategory(Category category);

	void updateCategory(Category category);

	void deleteCategory(Category category);
}