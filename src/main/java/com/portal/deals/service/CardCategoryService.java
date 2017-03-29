package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.CardCategory;

public interface CardCategoryService {

	List<CardCategory> listAllCardCategories();

	CardCategory getCardCategoryById(Integer id);

	void deleteCardCategoryById(Integer id);

	void saveCardCategory(CardCategory cardCategory);

	void updateCardCategory(CardCategory cardCategory);

	void deleteCardCategory(CardCategory cardCategory);
}