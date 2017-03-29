package com.portal.deals.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.deals.model.CardCategory;
import com.portal.deals.model.dao.CardCategoryDAO;
import com.portal.deals.service.CardCategoryService;

@Service("cardCategoryService")
@Transactional
public class CardCategoryServiceImpl implements CardCategoryService {

	@Autowired
	private CardCategoryDAO cardCategoryDAO;

	@Override
	public List<CardCategory> listAllCardCategories() {
		return cardCategoryDAO.listAllCategories();
	}

	@Override
	public CardCategory getCardCategoryById(Integer id) {
		return cardCategoryDAO.getCategoryById(id);
	}

	@Override
	public void deleteCardCategoryById(Integer id) {
		cardCategoryDAO.deleteCategoryById(id);
	}

	@Override
	public void saveCardCategory(CardCategory cardCategory) {
		cardCategoryDAO.saveCategory(cardCategory);
	}

	@Override
	public void updateCardCategory(CardCategory cardCategory) {
		CardCategory entity = cardCategoryDAO.getCategoryById(cardCategory.getId());
		if (entity != null) {
			entity.setDescription(cardCategory.getDescription());
			entity.setName(cardCategory.getName());
		}
	}

	@Override
	public void deleteCardCategory(CardCategory cardCategory) {
		cardCategoryDAO.deleteCategory(cardCategory);
	}
}