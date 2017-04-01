package com.portal.deals.service;

import java.util.List;

import com.portal.deals.model.Deal;

public interface DealService {

	List<Deal> listAllDeals();

	Deal getDealById(Integer id);

	void deleteDealById(Integer id);

	void saveDeal(Deal deal);

	void updateDeal(Deal deal);

	void deleteDeal(Deal deal);
	
	List<Deal> getDealsByCardId(Integer cardId);
}