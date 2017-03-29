package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.Category;

/**
 * This interface will provide the methods to manipulate category entity in
 * the database
 * 
 * @author Gaurav Jain
 *
 */
public interface CategoryDAO {

	/**
	 * This method will return list of all the Category entity from the
	 * database
	 * 
	 * @return List of Category entity
	 */
	List<Category> listAllCategories();

	/**
	 * This method will return the Category based on the Category id
	 * passed.
	 * 
	 * @param id
	 *            category id
	 * @return Category entity
	 */
	Category getCategoryById(Integer id);

	/**
	 * This method will save the Category entity to the database
	 * 
	 * @param category
	 */
	void saveCategory(Category category);

	/**
	 * This method will update the Category entity in the database
	 * 
	 * @param category
	 *            category
	 */
	void updateCategory(Category category);

	/**
	 * This method will delete the Category passed as parameter
	 * 
	 * @param category
	 *            Category entity object to be deleted
	 */
	void deleteCategory(Category category);

	/**
	 * This method will delete the Category w.r.t the Category id
	 * passed as parameter
	 * 
	 * @param id
	 *            Category id
	 */
	void deleteCategoryById(Integer id);
}