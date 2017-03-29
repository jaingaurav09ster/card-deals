package com.portal.deals.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.CardCategory;
import com.portal.deals.model.dao.CardCategoryDAO;

/**
 * This class provides implementation to <code>CardCategoryDAO</code> interface.
 * It will access the Database and it will perform CRUD operations on
 * CARD_CATEGORY table, this class will be accessible through
 * CardCategoryService
 * 
 * @author Gaurav Jain
 *
 */
@Transactional
@Repository("cardCategoryDAO")
public class CardCategoryDAOImpl extends AbstractDao<Integer, CardCategory> implements CardCategoryDAO {

	@Override
	public void saveCategory(CardCategory cardCategory) {
		this.persist(cardCategory);
	}

	@Override
	public void updateCategory(CardCategory cardCategory) {
		this.update(cardCategory);
	}

	@Override
	public void deleteCategory(CardCategory cardCategory) {
		this.delete(cardCategory);
	}

	@Override
	public List<CardCategory> listAllCategories() {
		List<CardCategory> list = this.loadAll();
		return (List<CardCategory>) list;
	}

	@Override
	public CardCategory getCategoryById(Integer id) {
		return this.get(id);
	}

	@Override
	public void deleteCategoryById(Integer id) {
		this.deleteById(id);
	}
}