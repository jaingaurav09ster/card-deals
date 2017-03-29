package com.portal.deals.model.dao;

import java.util.List;

import com.portal.deals.model.CardCategory;

/**
 * This interface will provide the methods to manipulate CARD category entity in
 * the database
 * 
 * @author Gaurav Jain
 *
 */
public interface CardCategoryDAO {

	/**
	 * This method will return list of all the CARD Category entity from the
	 * database
	 * 
	 * @return List of CARD Category entity
	 */
	List<CardCategory> listAllCategories();

	/**
	 * This method will return the Card Category based on the cardCategory id
	 * passed.
	 * 
	 * @param id
	 *            card category id
	 * @return CARD Category entity
	 */
	CardCategory getCategoryById(Integer id);

	/**
	 * This method will save the CARD Category entity to the database
	 * 
	 * @param card
	 *            category
	 */
	void saveCategory(CardCategory category);

	/**
	 * This method will update the CARD Category entity in the database
	 * 
	 * @param card
	 *            category
	 * 
	 */
	void updateCategory(CardCategory category);

	/**
	 * This method will delete the card category passed as parameter
	 * 
	 * @param card
	 *            category CARD Category entity object to be deleted
	 */
	void deleteCategory(CardCategory category);

	/**
	 * This method will delete the card category w.r.t the card category id
	 * passed as parameter
	 * 
	 * @param id
	 *            card category id
	 */
	void deleteCategoryById(Integer id);
}