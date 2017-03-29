package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.Category;
import com.portal.deals.model.dao.CategoryDAO;
import com.portal.deals.service.CategoryService;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public List<Category> listAllCategories() {
		return categoryDAO.listAllCategories();
	}

	@Override
	public Category getCategoryById(Integer id) {
		return categoryDAO.getCategoryById(id);
	}

	@Override
	public void deleteCategoryById(Integer id) {
		categoryDAO.deleteCategoryById(id);
	}

	@Override
	public void saveCategory(Category Category) {
		categoryDAO.saveCategory(Category);
	}

	@Override
	public void updateCategory(Category category) {
		Category entity = categoryDAO.getCategoryById(category.getId());
		if (entity != null) {
			entity.setDescription(category.getDescription());
			entity.setName(category.getName());
		}
	}

	@Override
	public void deleteCategory(Category category) {
		categoryDAO.deleteCategory(category);
	}
}